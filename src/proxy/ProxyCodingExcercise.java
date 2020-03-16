package proxy;

class Person {
	private int age;

	public Person(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String drink() {
		return "drinking";
	}

	public String drive() {
		return "driving";
	}

	public String drinkAndDrive() {
		return "driving while drunk";
	}
}

class ResponsiblePerson {
	private Person person;
	private boolean canDrink = false;
	private boolean canDrive = false;
	
	public ResponsiblePerson(Person person) {
		// todo
		this.person = person;
		if (person.getAge() >= 16) {
			canDrive = true;
		}
		if (person.getAge() >= 18) {
			canDrink = true;
		}
	}

	public String drink() {
		if (canDrink) {
			return "too young";
		} else {
			return person.drink();
		}
	}

	public String drive() {
		if (canDrive) {
			return "too young";
		} else {
			return person.drive();
		}
	}

	public String drinkAndDrive() {
		return "dead";
		
	}
}
public class ProxyCodingExcercise {
	public static void main(String[] args) {
		Person p = new Person(19);
		ResponsiblePerson rp = new ResponsiblePerson(p);
		System.out.println(rp.drink());
		System.out.println(rp.drive());
		System.out.println(rp.drinkAndDrive());
	}

}
