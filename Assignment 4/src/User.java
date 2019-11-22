import commands.*;
import devices.Oven_Device.*;

import java.util.ArrayList;

public class User {
    public static void main(String args[]){
        Smartphone smartphone = new Smartphone();
        InitializedOven oven = new InitializedOven();

        smartphone.addCommand(new SwitchOnCommand(oven));
        smartphone.addCommand(new SwitchOffCommand(oven));
        smartphone.addCommand(new StartCommand(oven));
        smartphone.addCommand(new SetHeatCommand(oven));
        smartphone.addCommand(new SetProgramCommand(oven));
        smartphone.addCommand(new SetTimerCommand(oven));
        
        smartphone.addDevice(oven);
        smartphone.start();
    }

}
