package chainofresponsibility;

/*
class Creature {
	private String name;
	private int attack;
	private int defense;

	public Creature(String name, int attack, int defense) {
		this.name = name;
		this.attack = attack;
		this.defense = defense;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	@Override
	public String toString() {
		return "Creature [name=" + name + ", attack=" + attack + ", defense=" + defense + "]";
	}
}

class CreatureModifier {
	protected Creature creature;
	protected CreatureModifier next;

	public CreatureModifier(Creature creature) {
		this.creature = creature;
	}

	public void add(CreatureModifier cm) {
		if (this.next != null) {
			this.next.add(cm);
		} else {
			this.next = cm;
		}
	}

	public void handle() {
		if (this.next != null) {
			next.handle();
		}
	}
}

class DoubleAttackModifier extends CreatureModifier {
	public DoubleAttackModifier(Creature creature) {
		super(creature);
	}
	
	@Override
	public void handle() {
		System.out.println("Doubling " + super.creature.getName() + "'s attack");
		int attack = super.creature.getAttack() * 2;
		super.creature.setAttack(attack);
		super.handle();
	}
}

class IncreaseDefenseModifier extends CreatureModifier {
	public IncreaseDefenseModifier(Creature creature) {
		super(creature);
	}
	
	@Override
	public void handle() {
		System.out.println("Increase " + super.creature.getName() + "'s defense");
		int defense = super.creature.getDefense() + 3;
		super.creature.setDefense(defense);
		super.handle();
	}
}

class NoBonusesModifier extends CreatureModifier {
	public NoBonusesModifier(Creature creature) {
		super(creature);
	}
	
	@Override
	public void handle() {
		System.out.println("No bonuses for you!");
	}
}
*/
// Chain Of Responsibility
// command 나 query 를 실행할 수 있는 요소들의 chain 들은
// 기본 프로세스 구현과 프로세스 chain 을 종료하는 기능을 가진다.
public class MethodChain {

	public static void main(String[] args) {
		Creature goblin = new Creature("Goblin", 2, 2);
		CreatureModifier root = new CreatureModifier(goblin);
		System.out.println(goblin.toString());
		
		root.add(new DoubleAttackModifier(goblin));
		root.add(new NoBonusesModifier(goblin));
		root.add(new IncreaseDefenseModifier(goblin));

		root.handle();
		System.out.println(goblin.toString());
	}

}
