package bridge;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

// ��ɰ� ������ ������ Ŭ������ �����ؼ� ���θ� �и��ϴ� ���
// ����� �߰��� ����, ������ ������ ����
// ���� Ŭ������ ��� Ŭ������ ������ ���� Ŭ������ method�� ��� Ŭ������ �����ؼ� ���
// Cartesian product complexity explosion �� ���´�
// entity explosion �� ���Ѵ�
// implementation ���� interface decouple �ϴ� ��Ŀ����

// (��� Ŭ����) Shape -> Circle, Square
// (���� Ŭ����) Rendering -> Vector, Raster

// bridge ���� �����ϸ� 4���� Ŭ������ �ʿ�
// VectorCircleRenderer, VectorSquareRenderer....
// Rendering �� �߰��Ǹ� 8�� �ʿ� -> Bridge ������ �ʿ�

interface Renderer {
  void renderCircle(float radius);
}

class VectorRenderer implements Renderer {
  @Override
  public void renderCircle(float radius) {
    System.out.println("Drawing a circle of radius " + radius);
  }
}

class RasterRenderer implements Renderer {
  @Override
  public void renderCircle(float radius) {
    System.out.println("Drawing pixels for a circle of radius " + radius);
  }
}

abstract class Shape {
  // Bridge
  protected Renderer renderer;

  public Shape(Renderer renderer) {
    this.renderer = renderer;
  }

  public abstract void draw();

  public abstract void resize(float factor);
}

class Circle extends Shape {

  public float radius;

  @Inject
  public Circle(Renderer renderer, float radius) {
    super(renderer);
    this.radius = radius;
  }

  @Override
  public void draw() {
    renderer.renderCircle(radius);
  }

  @Override
  public void resize(float factor) {
    radius *= factor;
  }

}

// Google Guice �� �̿��ؼ� protected Renderer renderer bridge ��ſ�
// Dependency Injection ���� ó���ϴ� ���
class ShapeModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Renderer.class).to(VectorRenderer.class);
  }
}

public class Bridge {

  public static void main(String[] args) {

    RasterRenderer raster = new RasterRenderer();
    VectorRenderer vector = new VectorRenderer();

    Circle circle = new Circle(vector, 5);

    circle.draw();
    circle.resize(2);
    circle.draw();

    Injector injector = Guice.createInjector(new ShapeModule());
    Circle instance = injector.getInstance(Circle.class);
    instance.radius = 3;
    instance.draw();
    instance.resize(2);
    instance.draw();
  }
}
