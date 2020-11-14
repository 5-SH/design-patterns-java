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

// �������� �ʴ� fax, scan ��ɿ� ���� interface �ϰ� �Ǹ� fax, scan ����� ��
// �������� �ʴ��� ȥ���� �� �� ����.
// interface �� ���� ���� ������ ��

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

// ���ָ��� interface �� �����µ� �������� ����� �ʿ�� �ϸ� implements �� ��� �߰��ؾ�
// �ϴ°�

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
