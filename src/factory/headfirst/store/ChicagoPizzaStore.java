package factory.headfirst.store;

import factory.headfirst.ingredients.ChicagoIngredientFactory;
import factory.headfirst.ingredients.PizzaIngredientFactory;
import factory.headfirst.pizza.*;

public class ChicagoPizzaStore extends PizzaStore {
  @Override
  protected Pizza createPizza(String type) {
    Pizza pizza = null;
    PizzaIngredientFactory ingredientFactory = new ChicagoIngredientFactory();

    if (type.equals("cheese")) {
      pizza = new CheesePizza(ingredientFactory);
      pizza.setName("Chicago Style Cheese Pizza");
    } else if (type.equals("veggie")) {
      pizza = new VeggiPizza(ingredientFactory);
      pizza.setName("Chicago Style Veggie Pizza");
    } else if (type.equals("clam")) {
      pizza = new ClamPizza(ingredientFactory);
      pizza.setName("Chicago Style Clam Pizza");
    } else if (type.equals("pepperoni")) {
      pizza = new PepperoniPizza(ingredientFactory);
      pizza.setName("Chicago Style Pepperoni Pizza");
    }

    return pizza;
  }
}
