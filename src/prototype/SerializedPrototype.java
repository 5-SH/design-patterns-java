package prototype;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

class Foo implements Serializable {
  public int stuff;
  public String whatever;

  public Foo(int stuff, String whatever) {
    this.stuff = stuff;
    this.whatever = whatever;
  }

  @Override
  public String toString() {
    return "Foo [stuff=" + stuff + ", whatever=" + whatever + "]";
  }
}

public class SerializedPrototype {
  public static void main(String[] args) {
    Foo foo = new Foo(42, "life");
    // deserialize -> serialize 해 객체 내 레퍼런스 타입의 멤버까지 깊은 복사
    Foo foo2 = SerializationUtils.roundtrip(foo);

    foo2.whatever = "xyz";
    System.out.println(foo);
    System.out.println(foo2);
  }
}