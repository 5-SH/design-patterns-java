package state;

import java.util.Random;

public class HasQuarterState implements State {
  GumballMachine gumballMachine;
  Random randomWinner = new Random(System.currentTimeMillis());

  public HasQuarterState(GumballMachine gumballMachine) {
    this.gumballMachine = gumballMachine;
  }

  @Override
  public void insertQuarter() {
    System.out.println("동전은 한 개만 넣어주세요.");
  }

  @Override
  public void ejectQuater() {
    System.out.println("동전이 반환됩니다.");
    gumballMachine.setState(gumballMachine.getNoQuarterState());
  }

  @Override
  public void turnCrank() {
    System.out.println("손잡이를 돌리셨습니다.");
    int winner = randomWinner.nextInt();
    if ((winner < 4) && (gumballMachine.getCount() > 1)) {
      gumballMachine.setState(gumballMachine.getWinnerState());
    } else {
      gumballMachine.setState(gumballMachine.getSoldState());
    }
  }

  @Override
  public void dispense() {
    System.out.println("알맹이가 나갈 수 없습니다.");
  }
}
