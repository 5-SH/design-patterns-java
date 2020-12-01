package command.headfirst;

import command.headfirst.command.*;
import command.headfirst.receiver.CeilingFan;
import command.headfirst.receiver.Light;

// Client
public class RemoteLoader {
  public static void main(String[] args) {
    RemoteController remoteController = new RemoteController();

    Light livingRoomLight = new Light("Living Room");
    Light kitchenLight = new Light("kitchen");
    CeilingFan ceilingFan = new CeilingFan("Living Room");

    LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
    LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

    LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
    LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

    CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
    CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
    CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

    remoteController.setCommand(0, livingRoomLightOn, livingRoomLightOff);
    remoteController.setCommand(1, kitchenLightOn, kitchenLightOff);
    remoteController.setCommand(2, ceilingFanHigh, ceilingFanOff);
    remoteController.setCommand(3, ceilingFanMedium, ceilingFanOff);

    System.out.println(remoteController);
    remoteController.onButtonWasPushed(0);
    remoteController.offButtonWasPushed(0);
    remoteController.undoButtonWasPushed();

    remoteController.onButtonWasPushed(1);
    remoteController.offButtonWasPushed(1);
    remoteController.undoButtonWasPushed();

    remoteController.onButtonWasPushed(2);
    remoteController.offButtonWasPushed(2);
    remoteController.undoButtonWasPushed();

    remoteController.onButtonWasPushed(3);
    remoteController.offButtonWasPushed(3);
    remoteController.undoButtonWasPushed();

  }
}
