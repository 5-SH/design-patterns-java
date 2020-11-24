package decorator.udemy;

import com.google.common.base.Supplier;

// Component Object
interface Shape {
  String info();
}

class Circle implements Shape {
  private float radius;

  public Circle(float radius) {
    this.radius = radius;
  }

  public void resize(float factor) {
    this.radius *= factor;
  }

  @Override
  public String info() {
    return "A circle of radius " + radius;
  }
}

class Square implements Shape {
  private float side;

  public Square(float side) {
    this.side = side;
  }

  @Override
  public String info() {
    return "A Square of side " + side;
  }
}

// dynamic decorator : 런타임 때 새로운 Decorator마다 빌드하기 때문
/*
 * class ColoredShape implements Shape { private Shape shape; private String
 * color;
 * 
 * public ColoredShape() {}
 * 
 * public ColoredShape(Shape shape, String color) { this.shape = shape;
 * this.color = color; }
 * 
 * @Override public String info() { return shape.info() + " has the color " +
 * color; } }
 */
// static decorator : compile 타임 때 형 지정
class ColoredShape<T extends Shape> implements Shape {
  private Shape shape;
  private String color;

  public ColoredShape(Supplier<? extends T> ctor, String color) {
    this.shape = ctor.get();
    this.color = color;
  }

  @Override
  public String info() {
    return shape.info() + " has the color " + color;
  }
}

/*
 * class TransparentShape implements Shape { private Shape shape; private float
 * transparency;
 * 
 * public TransparentShape() {} public TransparentShape(Shape shape, float
 * transparency) { this.shape = shape; this.transparency = transparency; }
 * 
 * @Override public String info() { return shape.info() + " has " + transparency
 * + "% transparency"; } }
 */

class TransparentShape<T extends Shape> implements Shape {
  private Shape shape;
  private float transparency;

  public TransparentShape(Supplier<? extends T> ctor, float transparency) {
    this.shape = ctor.get();
    this.transparency = transparency;
  }

  @Override
  public String info() {
    return shape.info() + " has " + transparency + "% transparency";
  }
}

public class Decorator {
  public static void main(String[] args) {
    Circle circle = new Circle(10);
    System.out.println(circle.info());

    // ColoredShape blueSquare = new ColoredShape(new Square(20), "blue");
    ColoredShape<Square> blueSquare = new ColoredShape(() -> new Square(20), "blue");
    System.out.println(blueSquare.info());

    // TransparentShape myCircle = new TransparentShape(new ColoredShape(new
    // Circle(5), "green"), 50);
    TransparentShape<Circle> myCircle = new TransparentShape(() -> new ColoredShape(() -> new Circle(5), "green"), 50);
    System.out.println(myCircle.info());

    // cannot call myCircle.resize()

  }
}
