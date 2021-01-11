package compound;

public class CountingDuckFactory extends AbstractDuckFactory {
  @Override
  public Quackable createMallardDuck() {
    return new QuackCounter(new MallardDuck());
  }

  @Override
  public Quackable createReadheadDuck() {
    return new QuackCounter(new ReadHeadDuck());
  }

  @Override
  public Quackable createDuckCall() {
    return new QuackCounter(new DuckCall());
  }

  @Override
  public Quackable createRubberDuck() {
    return new QuackCounter(new RubberDuck());
  }

  @Override
  public Quackable createGooseDuck() {
    return new GooseAdapter(new Goose());
  }
}
