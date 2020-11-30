package singleton.udemy;

// lazy initialization
// static을 통해 객체를 생성하는 것이 아니라 객체가 필요할 때 생성한다.
class LazySingleton {
  private static LazySingleton instance;

  private LazySingleton() {
    System.out.println("initializing a lazy singleton");
  }

  // 여러 thread 에서 getInstance() 를 동시에 부르면 race condition 이 생길 수 있다. -> 객체가 두 개 만들어
  // 질 수 있음
  // 1. synchronized 로 해결
  // public static synchronized LazySingleton getInstance() {
  // if (instance == null) {
  // instance = new LazySingleton();
  // }
  // return instance;
  // }

  // 2. double-checked locking
  public static LazySingleton getInstance() {
    if (instance == null) {
      synchronized (LazySingleton.class) {
        if (instance == null) {
          instance = new LazySingleton();
        }
      }
    }
    return instance;
  }
}

public class LazyInitialAndThreadSaftySingleton {
  public static void main(String[] args) {

  }
}