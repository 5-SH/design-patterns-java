package factory.headfirst.pizza;

import factory.headfirst.ingredients.PizzaIngredientFactory;

public class ClamPizza extends Pizza {
  PizzaIngredientFactory ingredientFactory;

  public ClamPizza(PizzaIngredientFactory ingredientFactory) {
    this.ingredientFactory = ingredientFactory;
  }

  @Override
  public void prepare() {
    System.out.println("Preparing " + name);
    dough = ingredientFactory.createDough();
    sauce = ingredientFactory.createSauce();
    cheese = ingredientFactory.createCheese();
    clam = ingredientFactory.createClam();

    System.out.println("Ingredients " +
            dough.getName() + ", " +
            sauce.getName() + ", " +
            cheese.getName() + ", " +
            clam.getName());
  }
}
