package singleton.udemy;

// thread safety 를 위한 다른 방법 - synchronize 키워드 필요없음
//  initialization on demand holder idiom
public class InnerStaticSingleton {
  private InnerStaticSingleton() {
  }

  // 외부 class constructor 나 외부 변수를 통해 내부 static instance 생성
  private static class Impl {
    private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
  }

  public InnerStaticSingleton getInstance() {
    return Impl.INSTANCE;
  }

  public static void main(String[] args) {

  }
}