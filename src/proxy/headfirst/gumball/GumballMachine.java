package proxy.headfirst.gumball;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {
  State soldOutState;
  State noQuarterState;
  State hasQuarterState;
  State soldState;
  State winnerState;

  State state = soldOutState;
  int count = 0;
  String location = "";
  
  public GumballMachine(String location, int numberGumballs) throws RemoteException {
    this.soldOutState = new SoldOutState(this);
    this.noQuarterState = new NoQuarterState(this);
    this.hasQuarterState = new HasQuarterState(this);
    this.soldState = new SoldState(this);
    this.winnerState = new WinnerState(this);

    this.location = location;
    this.count = numberGumballs;
    if (numberGumballs > 0) {
      state = noQuarterState;
    }
  }

  public void insertQuarter() {
    state.insertQuarter();
  }

  public void ejectQuarter() {
    state.ejectQuater();
  }

  public void turnCrank() {
    state.turnCrank();
    state.dispense();
  }

  public void setState(State state) {
    this.state = state;
  }

  public void releaseBall() {
    System.out.println("알맹이가 나가고 있습니다.");
    if (count != 0) {
      count = count - 1;
    }
  }

  public int getCount() {
    return count;
  }

  @Override
  public String getLocation() {
    return location;
  }

  @Override
  public State getState() {
    return state;
  }

  public State getSoldOutState() {
    return soldOutState;
  }

  public State getNoQuarterState() {
    return noQuarterState;
  }

  public State getHasQuarterState() {
    return hasQuarterState;
  }

  public State getSoldState() {
    return soldState;
  }

  public State getWinnerState() {
    return winnerState;
  }

  @Override
  public String toString() {
    return "주식회사 왕뽑기\n자바로 돌아가는 뽑기 기계\n남은 개수: " + count + "개\n" +
            (state.getClass().getName() == "state.NoQuarterState" ? "동전 투입 대기중" : "매진") + "\n";
  }
}
