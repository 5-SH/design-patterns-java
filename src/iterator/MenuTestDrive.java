package iterator;

public class MenuTestDrive {
  public static void main(String[] args) {
    Menu pancakeHouseMenu = new PancakeHouseMenu();
    DinerMenu dinerMenu = new DinerMenu();

    Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);

    waitress.printMenu();
  }
}
