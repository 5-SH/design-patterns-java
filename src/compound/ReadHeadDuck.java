package compound;

public class ReadHeadDuck implements Quackable {
  Observable observable;

  public ReadHeadDuck() {
    this.observable = new Observable(this);
  }

  @Override
  public void quack() {
    System.out.println("Quack");
    notifyObservers();
  }

  @Override
  public void registerObserver(Observer observer) {
    observable.registerObserver(observer);
  }

  @Override
  public void notifyObservers() {
    observable.notifyObservers();
  }
}
