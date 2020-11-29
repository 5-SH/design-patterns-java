package factory.headfirst.store;

import factory.headfirst.ingredients.NYPizzaIngredientFactory;
import factory.headfirst.ingredients.PizzaIngredientFactory;
import factory.headfirst.pizza.*;

// Concrete Creator
public class NYPizzaStore extends PizzaStore {
  @Override
  protected Pizza createPizza(String type) {
    Pizza pizza = null;
    PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

    if (type.equals("cheese")) {
      pizza = new CheesePizza(ingredientFactory);
      pizza.setName("New York Style Cheese Pizza");
    } else if (type.equals("veggie")) {
      pizza = new VeggiPizza(ingredientFactory);
      pizza.setName("New York Style Veggie Pizza");
    } else if (type.equals("clam")) {
      pizza = new ClamPizza(ingredientFactory);
      pizza.setName("New York Style Clam Pizza");
    } else if (type.equals("pepperoni")) {
      pizza = new PepperoniPizza(ingredientFactory);
      pizza.setName("New York Style Pepperoni Pizza");
    }

    return pizza;
  }
}
