package facade.hometheather;

public class Light {
  public void on() {
    System.out.println("Light On");
  }

  public void off() {
    System.out.println("Light Off");
  }

  public void dim(int dim) {
    System.out.println("Light Dim " + dim);
  }
}
