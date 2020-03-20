package singleton;

class ChiefExecutiveOfficer {
  // Singleton 이 필요하면 클래스의 인자에 static 을 선언해 간단히 singleton 객체를 얻을 수 있다.
  private static String name;
  private static int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "ChiefExecutiveOfficer [name=" + name + ", age=" + age + "]";
  }
}

public class Monostate {
  public static void main(String[] args) {
    /**
     * 이미 클래스가 구현되어 있고 클래스를 instance 화 하는 코드가 개발되어 있다면 클래스의 field 를 static 으로 바꿔주는 것
     * 만으로 singleton 을 얻을 수 있다. 하지만 singleton 한 객체임을 명시하지 못한다.
     **/
    ChiefExecutiveOfficer ceo = new ChiefExecutiveOfficer();
    ceo.setAge(55);
    ceo.setName("Adam Smith");

    ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
    System.out.println(ceo2.toString());
  }
}