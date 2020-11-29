package factory.headfirst.store;

import factory.headfirst.pizza.Pizza;

// Method Factory Pattern
// Creator
public abstract class PizzaStore {
  public Pizza orderPizza(String type) {
    Pizza pizza = createPizza(type);

    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();

    return pizza;
  }

  protected abstract Pizza createPizza(String type);
}
