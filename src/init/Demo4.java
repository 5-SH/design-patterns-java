package init;

// Interface Segregation Principle = how to split interfae into small interface

class Document {
}

interface Machine {
  void print(Document d);

  void fax(Document d);

  void scan(Document d) throws Exception;
}

class MultiFunctionPrinter implements Machine {
  @Override
  public void print(Document d) {
  }

  @Override
  public void fax(Document d) {
  }

  @Override
  public void scan(Document d) {
  }
}

class OldFunctionPrinter implements Machine {
  @Override
  public void print(Document d) {
  }

  @Override
  public void fax(Document d) {
  }

  @Override
  public void scan(Document d) {
  }
}

// 제공하지 않는 fax, scan 기능에 대해 interface 하게 되면 fax, scan 기능이 왜 동작하지 않는지 혼란이 올 수 있음.
// interface 를 범주 마다 나눠야 함

interface Printer {
  void print(Document d);
}

interface Scanner {
  void scan(Document d);
}

class JustAPrinter implements Printer {
  @Override
  public void print(Document d) {
  }
}

class Photocopier implements Printer, Scanner {
  @Override
  public void scan(Document d) {
  }

  @Override
  public void print(Document d) {
  }
}

// 범주마다 interface 를 나눴는데 여러가지 기능을 필요로 하면 implements 를 계속 추가해야 하는가
interface MultiFunctionDevice extends Printer, Scanner {
}

class MultiFunctionMachine implements MultiFunctionDevice {
  private Printer printer;
  private Scanner scanner;

  public void MultiFunctionMachine(Printer printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
  }

  @Override
  public void print(Document d) {
    printer.print(d);
  }

  @Override
  public void scan(Document d) {
    scanner.scan(d);
  }
}

public class Demo4 {

}
