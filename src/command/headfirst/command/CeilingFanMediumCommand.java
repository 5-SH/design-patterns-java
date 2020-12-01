package command.headfirst.command;

import command.headfirst.receiver.CeilingFan;

public class CeilingFanMediumCommand implements Command {
  private CeilingFan ceilingFan;
  int prevSpeed;

  public CeilingFanMediumCommand(CeilingFan ceilingFan) {
    this.ceilingFan = ceilingFan;
  }

  @Override
  public void execute() {
    prevSpeed = ceilingFan.getSpeed();
    ceilingFan.medium();
  }

  @Override
  public void undo() {
    if (prevSpeed == CeilingFan.HIGH) {
      ceilingFan.high();
    } else if (prevSpeed == CeilingFan.MEIDUM) {
      ceilingFan.medium();
    } else if (prevSpeed == CeilingFan.LOW) {
      ceilingFan.low();
    } else if (prevSpeed == CeilingFan.OFF) {
      ceilingFan.off();
    }

  }
}
