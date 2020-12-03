package templatemethod;

public class Tea extends CaffeineBeverage {
  @Override
  void brew() {
    System.out.println("차를 우려 내는 중");
  }

  @Override
  void addCondiments() {}

  public boolean customerWantsCondiments() {
    return false;
  }
}
