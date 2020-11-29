package factory.headfirst.pizza;

import factory.headfirst.ingredients.PizzaIngredientFactory;

public class CheesePizza extends Pizza {
  PizzaIngredientFactory ingredientFactory;

  public CheesePizza(PizzaIngredientFactory ingredientFactory) {
    this.ingredientFactory = ingredientFactory;
  }

  @Override
  public void prepare() {
    System.out.println("Preparing " + name);
    dough = ingredientFactory.createDough();
    sauce = ingredientFactory.createSauce();
    cheese = ingredientFactory.createCheese();

    System.out.println("Ingredients " +
            dough.getName() + ", " +
            sauce.getName() + ", " +
            cheese.getName());
  }
}
