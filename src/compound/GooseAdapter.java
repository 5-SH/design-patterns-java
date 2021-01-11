package compound;

public class GooseAdapter implements Quackable {
  Observable observable;
  Goose goose;

  public GooseAdapter(Goose goose) {
    observable = new Observable(this);
    this.goose = goose;
  }

  @Override
  public void quack() {
    goose.honk();
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
