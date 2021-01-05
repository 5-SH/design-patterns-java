package proxy.udemy;

class Property<T> {
	private T value;
	
	public Property(T value) {
		this.value = value; 
	}
	public T getValue() {
		return this.value;
	}
	public void setValue(T value) {
		System.out.println("value set " + value);
		this.value = value;
	}
}

class Creature {
	private Property<Integer> agility = new Property<>(10);
	
	public void setAgility(int value) {
		this.agility.setValue(value);
	}
	public int getAgility() {
		return this.agility.getValue();
	}
}

public class PropertyProxy {
	public static void main(String[] args) {
		Creature c = new Creature();
		c.setAgility(12);
	}
}
