package compound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Flock implements Quackable {
  Observable observable;
  ArrayList quackers = new ArrayList();

  public Flock() {
    this.observable = new Observable(this);
  }

  public void add(Quackable quacker) {
    quackers.add(quacker);
  }

  @Override
  public void quack() {
    Iterator iterator = quackers.iterator();
    while(iterator.hasNext()) {
      Quackable quacker = (Quackable)iterator.next();
      quacker.quack();
    }
  }

  @Override
  public void registerObserver(Observer observer) {
    Stack stack = new Stack();
    stack.push(quackers.iterator());
    register(stack, observer);
  }

  @Override
  public void notifyObservers() { }

  private ArrayList getQuackers() { return quackers; }

  private void register(Stack stack, Observer observer) {
    if (!stack.empty()) {
      Iterator iterator = (Iterator) stack.peek();
      if (!iterator.hasNext()) {
        stack.pop();
      } else {
        Quackable quacker = (Quackable) iterator.next();
        if (quacker instanceof Flock) {
          stack.push(((Flock) quacker).getQuackers().iterator());
          register(stack, observer);
        } else {
          quacker.registerObserver(observer);
          register(stack, observer);
        }
      }
    }
  }
}
