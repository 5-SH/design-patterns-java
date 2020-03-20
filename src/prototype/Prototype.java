package prototype;

import java.util.Arrays;

class Address implements Cloneable {
  public String streetName;
  public int houseNumber;

  public Address(String streetName, int houseNumber) {
    this.streetName = streetName;
    this.houseNumber = houseNumber;
  }

  @Override
  public String toString() {
    return "Address [streetName=" + streetName + ", houseNumber=" + houseNumber + "]";
  }

  @Override
  public Address clone() throws CloneNotSupportedException {
    return new Address(streetName, houseNumber);
  }
}

class Person implements Cloneable {
  public String[] names;
  public Address address;

  public Person(String[] names, Address address) {
    this.names = names;
    this.address = address;
  }

  @Override
  public String toString() {
    return "Person [names=" + Arrays.toString(names) + ", address=" + address + "]";
  }

  @Override
  protected Person clone() throws CloneNotSupportedException {
    return new Person(names.clone(), address.clone());
  }

}

public class Prototype {
  public static void main(String[] args) throws Exception {
    Person john = new Person(new String[] { "John", "Smith" }, new Address("London Rad", 123));
    Person jane = john.clone();
    jane.names[0] = "Jane";
    jane.address.houseNumber = 124;

    System.out.println(john);
    System.out.println(jane);

  }
}