package factory.headfirst.pizza;

import factory.headfirst.ingredients.PizzaIngredientFactory;
import factory.headfirst.ingredients.itfc.Veggies;

public class VeggiPizza extends Pizza {
  PizzaIngredientFactory ingredientFactory;

  public VeggiPizza(PizzaIngredientFactory ingredientFactory) {
    this.ingredientFactory = ingredientFactory;
  }

  @Override
  public void prepare() {
    System.out.println("Preparing " + name);
    dough = ingredientFactory.createDough();
    sauce = ingredientFactory.createSauce();
    cheese = ingredientFactory.createCheese();
    veggies = ingredientFactory.createVeggies();

    String gather = "";
    for (Veggies each : veggies) {
      gather += (each.getName() + " ");
    }
    System.out.println("Ingredients " +
            dough.getName() + ", " +
            sauce.getName() + ", " +
            cheese.getName() + ", " +
            "veggies : [ " + gather + "]");
  }
}
