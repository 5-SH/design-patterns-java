package composite;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Composite pattern : 단일 객체와 복합 객체를 동일하게 컨트롤 가능하도록 도와주는 패턴 객체를 트리 구조로 구성
 * component : composition 을 위한 인터페이스 leaf : component 를 구현하는 클래스 composite :
 * 다수의 leaf 클래스를 컨트롤 할 수 있는 클래스
 **/

class GraphicObject {
  public String name = "Group";
  public String color;
  public ArrayList<GraphicObject> children = new ArrayList<>();

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getColor() {
    return this.color;
  }

  private void print(StringBuilder stringBuilder, int depth) {
    stringBuilder.append(String.join("", Collections.nCopies(depth, "*"))).append(depth > 0 ? " " : "")
        .append((color == null || color.isEmpty()) ? "" : color + " ").append(getName()).append(System.lineSeparator());

    for (GraphicObject child : children) {
      child.print(stringBuilder, depth + 1);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    print(sb, 0);
    return sb.toString();
  }
}

class Circle extends GraphicObject {
  public Circle(String color) {
    super.name = "Circle";
    this.color = color;
  }
}

class Square extends GraphicObject {
  public Square(String color) {
    super.name = "Circle";
    this.color = color;
  }
}

public class GeometricShapes {
  public static void main(String[] args) {
    GraphicObject drawing = new GraphicObject();
    drawing.setName("My Drawing");
    drawing.children.add(new Square("Blue"));
    drawing.children.add(new Circle("Yellow"));

    GraphicObject group = new GraphicObject();
    group.children.add(new Circle("Blue"));
    group.children.add(new Square("Blue"));
    drawing.children.add(group);

    System.out.println(drawing);

  }
}