package factory.headfirst.pizza;

import factory.headfirst.ingredients.PizzaIngredientFactory;

public class PepperoniPizza extends Pizza {
  PizzaIngredientFactory ingredientFactory;

  public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
    this.ingredientFactory = ingredientFactory;
  }

  @Override
  public void prepare() {
    System.out.println("Preparing " + name);
    dough = ingredientFactory.createDough();
    sauce = ingredientFactory.createSauce();
    cheese = ingredientFactory.createCheese();
    pepperoni = ingredientFactory.createPepperoni();

    System.out.println("Ingredients " +
            dough.getName() + ", " +
            sauce.getName() + ", " +
            cheese.getName() + ", " +
            pepperoni.getName());
  }
}
