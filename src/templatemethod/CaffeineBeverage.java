package templatemethod;

public abstract class CaffeineBeverage {
  final void prepareRecipe() {
    boilWater();
    brew();
    pourInCup();
    if (customerWantsCondiments()) {
      addCondiments();
    }
  }

  public void boilWater() {
    System.out.println("물 끊이는 중");
  }

  public void pourInCup() {
    System.out.println("컵에 따르는 중");
  }

  boolean customerWantsCondiments() {
    return true;
  }

  abstract void brew();
  abstract void addCondiments();
}
