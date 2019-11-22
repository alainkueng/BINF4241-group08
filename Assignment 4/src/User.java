import commands.*;
import devices.Oven_Device.*;

import java.util.ArrayList;

public class User {
    public static void main(String args[]){
        Smartphone smartphone = new Smartphone();
        InitializedOven oven = new InitializedOven();
        ArrayList<Command> ovenCommands = new ArrayList();

        ovenCommands.add(new SwitchOnCommand(oven));
        ovenCommands.add(new SwitchOffCommand(oven));
        ovenCommands.add(new StartCommand(oven));
        ovenCommands.add(new SetHeatCommand(oven));
        ovenCommands.add(new SetProgramCommand(oven));
        ovenCommands.add(new SetTimerCommand(oven));

        oven.addCommandList(ovenCommands);

        smartphone.addDevice(oven);
        smartphone.start();
    }

}
