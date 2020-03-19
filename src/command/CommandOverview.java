package command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BankAccount {
  private int balance;
  private int overdraftLimit = -500;

  public void deposit(int amount) {
    balance += amount;
    System.out.println("Deposited " + amount + ", balance is now " + balance);
  }

  public boolean withdraw(int amount) {
    if (balance - amount >= overdraftLimit) {
      balance -= amount;
      System.out.println("Withdraw " + amount + ", balance is now " + balance);
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return "BankAccount [balance=" + balance + "]";
  }
}

interface Command {
  void call();

  void undo();
}

class BankAccountCommand implements Command {
  private BankAccount account;

  public enum Action {
    DEPOSIT, WITHDRAW
  }

  private Action action;
  private int amount;
  private boolean succeeded;

  @Override
  public void call() {
    switch (action) {
      case DEPOSIT:
        succeeded = true;
        account.deposit(amount);
        break;
      case WITHDRAW:
        succeeded = account.withdraw(amount);
        break;
    }
  }

  public BankAccountCommand(BankAccount account, Action action, int amount) {
    super();
    this.account = account;
    this.action = action;
    this.amount = amount;
  }

  @Override
  public void undo() {
    if (!succeeded)
      return;
    switch (action) {
      case DEPOSIT:
        account.withdraw(amount);
        break;
      case WITHDRAW:
        account.deposit(amount);
        break;
    }
  }
}

public class CommandOverview {
  public static void main(String[] args) {
    BankAccount ba = new BankAccount();
    System.out.println(ba);

    List<BankAccountCommand> commands = Arrays.asList(
        new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100),
        new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000));

    for (BankAccountCommand c : commands) {
      c.call();
      System.out.println(ba);
    }

    Collections.reverse(commands);
    for (Command c : commands) {
      c.undo();
      System.out.println(ba);
    }
  }
}