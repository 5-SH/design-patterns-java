package factory.headfirst.ingredients;

import factory.headfirst.ingredients.itfc.*;

public class ChicagoIngredientFactory implements PizzaIngredientFactory {
  @Override
  public Dough createDough() {
    return new ThickCrustDough();
  }

  @Override
  public Sauce createSauce() {
    return new PlumTomatoSauce();
  }

  @Override
  public Cheese createCheese() {
    return new MozzarellaCheese();
  }

  @Override
  public Veggies[] createVeggies() {
    Veggies veggies[] = {
            new Garlic(), new Onion(), new Mushroom(), new RedPepper()
    };

    return veggies;
  }

  @Override
  public Pepperoni createPepperoni() {
    return new SlicedPepperoni();
  }

  @Override
  public Clams createClam() {
    return new FrozenClams();
  }
}
