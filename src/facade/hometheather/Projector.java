package facade.hometheather;

public class Projector {
  private DvdPlayer dvdPlayer;

  public void setInput(DvdPlayer dvdPlayer) {
    this.dvdPlayer = dvdPlayer;
    System.out.println("Projector Set DVD Player");
  }

  public void on() {
    System.out.println("Projector On");
  }

  public void off() {
    System.out.println("Projector Off");
  }

  public void tvMode() {
    System.out.println("Projector tvMode");
  }

  public void wideScreenMode() {
    System.out.println("Projector Wide Screen Mode");
  }
}
