package compound;

public class DuckSimulator {
  public static void main(String[] args) {
    DuckSimulator simulator = new DuckSimulator();
    AbstractDuckFactory duckFactory = new CountingDuckFactory();
    simulator.simulate(duckFactory);
  }

  private void simulate(AbstractDuckFactory duckFactory) {
    Quackable redheadDuck = duckFactory.createReadheadDuck();
    Quackable duckCall = duckFactory.createDuckCall();
    Quackable rubberDuck = duckFactory.createRubberDuck();
    Quackable gooseDuck = duckFactory.createGooseDuck();

    System.out.println("\nDuck Simulator");

    Flock flockOfDucks = new Flock();

    flockOfDucks.add(redheadDuck);
    flockOfDucks.add(duckCall);
    flockOfDucks.add(rubberDuck);
    flockOfDucks.add(gooseDuck);

    Flock flockOfMallards = new Flock();

    Quackable mallardOne = duckFactory.createMallardDuck();
    Quackable mallardTwo = duckFactory.createMallardDuck();
    Quackable mallardThree = duckFactory.createMallardDuck();
    Quackable mallardFour = duckFactory.createMallardDuck();

    flockOfMallards.add(mallardOne);
    flockOfMallards.add(mallardTwo);
    flockOfMallards.add(mallardThree);
    flockOfMallards.add(mallardFour);

    flockOfDucks.add(flockOfMallards);

    Quackologist quackologist = new Quackologist();
    flockOfDucks.registerObserver(quackologist);

    System.out.println("\nDuck Simulator: Whole Flock Simulation");
    simulate(flockOfDucks);

    System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
  }

  private void simulate(Quackable duck) {
    duck.quack();
  }
}
