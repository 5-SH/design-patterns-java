package proxy.headfirst.remote;

import java.io.Serializable;

public interface State extends Serializable {
  public void insertQuarter();
  public void ejectQuater();
  public void turnCrank();
  public void dispense();
}
