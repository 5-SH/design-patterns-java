package factory.headfirst;

import factory.headfirst.pizza.Pizza;
import factory.headfirst.store.ChicagoPizzaStore;
import factory.headfirst.store.NYPizzaStore;
import factory.headfirst.store.PizzaStore;

public class PizzaTestDrive {
  public static void main(String[] args) {
    PizzaStore nyPizzaStore = new NYPizzaStore();
    PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

    Pizza pizza1 = nyPizzaStore.orderPizza("cheese");
    System.out.println("Ethan ordered a " + pizza1.getName() + "\n");

    Pizza pizza2 = chicagoPizzaStore.orderPizza("veggie");
    System.out.println("Joel ordered a " + pizza2.getName() + "\n");
  }
}
