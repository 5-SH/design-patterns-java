package command.headfirst;

import command.headfirst.command.Command;
import command.headfirst.command.NoCommand;

// Invoker
public class RemoteController {
  Command[] onCommands;
  Command[] offCommands;
  Command undoCommand;

  public RemoteController() {
    onCommands = new Command[7];
    offCommands = new Command[7];

    Command noCommand = new NoCommand();
    for (int i = 0; i < 7; i++) {
      onCommands[i] = noCommand;
      offCommands[i] = noCommand;
    }
  }

  public void setCommand(int slot, Command onCommand, Command offCommand) {
    onCommands[slot] = onCommand;
    offCommands[slot] = offCommand;
  }

  public void onButtonWasPushed(int slot) {
    onCommands[slot].execute();
    undoCommand = onCommands[slot];
  }

  public void offButtonWasPushed(int slot) {
    offCommands[slot].execute();
    undoCommand = offCommands[slot];
  }

  public void undoButtonWasPushed() {
    undoCommand.undo();
  }

  @Override
  public String toString() {
    StringBuffer strBuf = new StringBuffer();
    strBuf.append("\n------ Remote Control ------\n");
    for (int i = 0; i < onCommands.length; i++) {
      strBuf.append("[slot " + i + "] " + onCommands[i].getClass().getName()
      + "         " + offCommands[i].getClass().getName() + "\n");
    }

    return strBuf.toString();
  }
}
