package singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

// 내부에 선언된 모든 field 들이 직렬화, 역직렬화 되지 않는다
// 상속할 수 없다.
enum EnumBasedSingleton {
  INSTANCE;

  EnumBasedSingleton() {
    value = 42;
  }

  private int value;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}

public class EnumSingleton {
  static void saveToFile(EnumBasedSingleton singleton, String filename) throws Exception {
    try (FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(singleton);
    }
  }

  static EnumBasedSingleton readFromFile(String filename) throws Exception {
    try (FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream in = new ObjectInputStream(fileIn)) {
      return (EnumBasedSingleton) in.readObject();
    }
  }

  public static void main(String[] args) throws Exception {
    String filename = "myfile.bin";
    EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
    singleton.setValue(111);
    saveToFile(singleton, filename);

    // enum singleton 에서 직렬화, 역직렬화 할 경우 대상이 되는 것은 INSTANCE 이다.
    // value 정보는 직렬화, 역직렬화 대상이 아니다.
    // 그래서 42가 나옴
    EnumBasedSingleton singleton2 = readFromFile(filename);
    System.out.println(singleton2.getValue());
  }
}