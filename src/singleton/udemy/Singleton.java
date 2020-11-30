package singleton.udemy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class BasicSingleton implements Serializable {
  private BasicSingleton() {
  }

  private static final BasicSingleton INSTANCE = new BasicSingleton();

  public static BasicSingleton getInstance() {
    return INSTANCE;
  }

  private int value = 0;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  /**
   * readResolve method 는 readObject 메소드에서 생성한 인스턴스를 다른 인스턴스로 바꿔준다. 만일 역직렬화 되는 객체의
   * 클래스에서 readResolve 메소드를 올바륵 정의하면 그 객체가 역직렬화 된 후 그 결과로 새롭게 생성된 객체에 대해 이 메소드가
   * 자동으로 호출되며, 이 메소드에서 반환하는 객체 참조가 역직렬화로 새롭게 생성된 객체 대신 반환된다.
   **/
  protected Object readResolve() {
    return INSTANCE;
  }
}

public class Singleton {
  static void saveToFile(BasicSingleton singleton, String filename) throws Exception {
    try (FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(singleton);
    }
  }

  static BasicSingleton readFromFile(String filename) throws Exception {
    try (FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream in = new ObjectInputStream(fileIn)) {
      return (BasicSingleton) in.readObject();
    }
  }

  public static void main(String[] args) throws Exception {
    /**
     * BasicSingleton singleton = BasicSingleton.getInstance();
     * singleton.setValue(123); System.out.println(singleton.getValue()));
     **/

    /**
     * static을 활용한 singletone 문제점 1. reflection 2. serialization
     **/
    BasicSingleton singleton = BasicSingleton.getInstance();
    singleton.setValue(111);

    String filename = "singleton.bin";
    saveToFile(singleton, filename);
    singleton.setValue(222);

    BasicSingleton singleton2 = readFromFile(filename);

    System.out.println(singleton == singleton2);
    System.out.println(singleton.getValue());
    // serialize -> deserialize 한 값을 받아오면 singleton 이 안됨
    // readResolve() method 로 해결
    System.out.println(singleton2.getValue());
  }
}