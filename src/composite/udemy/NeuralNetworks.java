package composite.udemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

interface SomeNeurons extends Iterable<Neuron> {
  default void connectTo(SomeNeurons other) {
    if (this == other)
      return;
    for (Neuron from : this)
      for (Neuron to : other) {
        from.out.add(to);
        to.in.add(from);
      }
  }
}

class Neuron implements SomeNeurons {
  public ArrayList<Neuron> in = new ArrayList<>();
  public ArrayList<Neuron> out = new ArrayList<>();

  @Override
  public Iterator<Neuron> iterator() {
    return Collections.singleton(this).iterator();
  }

  @Override
  public void forEach(Consumer<? super Neuron> action) {
    action.accept(this);
  }

  @Override
  public Spliterator<Neuron> spliterator() {
    return Collections.singleton(this).spliterator();
  }
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons {
}

public class NeuralNetworks {
  public static void main(String[] args) {
    Neuron neuron = new Neuron();
    Neuron neuron2 = new Neuron();
    NeuronLayer layer = new NeuronLayer();
    NeuronLayer layer2 = new NeuronLayer();

    // 단일 객체(Neuron)와 복합 객체(NeuronLayer)를 동일하게 컨트롤(uniform fashion) 하고 싶음 ->
    // interface 이용
    neuron.connectTo(neuron2);
    neuron.connectTo(layer);
    layer.connectTo(neuron);
    layer.connectTo(layer2);
  }
}