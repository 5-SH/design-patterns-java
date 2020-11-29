package factory.headfirst.ingredients;

import factory.headfirst.ingredients.itfc.*;

// Abstract Factory Pattern
// Abstract Factory
public interface PizzaIngredientFactory {
  public Dough createDough();
  public Sauce createSauce();
  public Cheese createCheese();
  public Veggies[] createVeggies();
  public Pepperoni createPepperoni();
  public Clams createClam();
}
