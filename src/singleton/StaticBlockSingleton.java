package singleton;

import java.io.File;
import java.io.IOException;

public class StaticBlockSingleton {
  private StaticBlockSingleton() throws IOException {
    System.out.println("Singleton is initializing");
    File.createTempFile(".", ".");
  }

  private static StaticBlockSingleton instance;

  static {
    try {
      // singleton 객체를 생성하기 전 작업이 필요하면
      // static block 을 통한 singleton 객체 생성을 하면 된다.
      instance = new StaticBlockSingleton();
    } catch (Exception e) {

    }
  }

  public static StaticBlockSingleton getInstance() {
    return instance;
  }

  public static void main(String[] args) {

  }
}