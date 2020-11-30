package singleton.headfirst;

public class Singleton {
  // volatile keyword
  // JVM 이 모든 읽기 연산으로 메인 메모리를 사용한다.
  // CPU 캐쉬 말고 메인 메모리에 값을 저장해 멀티 스레드 환경에서
  // 하나의 스레드만 wirte 할 때 가장 최신값을 보장한다.
  // 여러 스레드에서 wirte 할 때는 최신값을 보장 못하기 때문에
  // Synchronized keyword 를 사용해 원시성을 보장해야 한다
  private volatile static Singleton uniqueInstance;

  private Singleton() {}

  public static Singleton getInstance() {
    // 객체를 생성하고 나면 멀티 스레드에서 read 만 한다.
    // 객체를 생성할 때만 synchronized 하면 된다.
    if (uniqueInstance == null) {
      synchronized (Singleton.class) {
        if (uniqueInstance == null) {
          uniqueInstance = new Singleton();
        }
      }
    }

    return uniqueInstance;
  }
}
