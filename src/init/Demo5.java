package init;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

// A. Hight-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

enum Relationship {
	PARENT,
	CHILD,
	SIBLING
}

class Person {
	public String name;
	public Person(String name) {
		this.name = name;
	}
}

interface RelationshipBrowser {
	List<Person> findAllChildrenOf(String name);
}

//low-level module
class Relationships implements RelationshipBrowser {
	// Triplet class requires javatuples
	private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();
		
	public List<Person> findAllChildrenOf(String name) {
		return relations.stream()
				.filter(x -> Objects.equals(x.getValue0().name, name)
								&& x.getValue1() == Relationship.PARENT)
				.map(Triplet::getValue2)
				.collect(Collectors.toList());
	}
	
	public List<Triplet<Person, Relationship, Person>> getRelations() {
		return relations;
	}
	
	public void addParentAndChild(Person parent, Person child) {
		relations.add(new Triplet<>(parent, Relationship.PARENT, child));
		relations.add(new Triplet<>(child, Relationship.CHILD, parent));
		
	}
}

// higher-level module
class Research {
	// higher-level module 이 low-level module 에 dependency 를 가짐
	// violate Dependency Inversion Principle
	// abstraction 을 통해 dependency 를 가져야 한다.
	// search 는 higher-level module 에서 일어나지 않고 low-level module 에서 일어나도록 한다.
	// 이렇게 해야 higher-level module 에서 low-level module 에 dependency 를 가지지 않게 된다.
	public Research(Relationships relationships) {

		// hight-level: find all of John's children
		// Relationships 에서 List를 수정할 수 없다. -> relationship list 를 여기서 받기 때문("List<Triplet<Person, Relationship, Person>>")
		List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
		relations.stream()
			.filter(x -> x.getValue0().name.equals("John")
						&& x.getValue1() == Relationship.PARENT)
			.forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
	}
	
	public Research(RelationshipBrowser browser) {
		List<Person> children = browser.findAllChildrenOf("John");
		for (Person child : children)
			System.out.println("John has achild called " + child.name);
	}
}

public class Demo5 {
	public static void main(String[] args) {
		Person parent = new Person("John");
		Person child1 = new Person("Chris");
		Person child2 = new Person("Matt");
		
		// low-level module
		Relationships relationships = new Relationships();
		relationships.addParentAndChild(parent, child1);
		relationships.addParentAndChild(parent, child2);
		
		new Research(relationships);
	}
}
