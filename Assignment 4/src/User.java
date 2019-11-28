import commands.*;
import devices.Dishwasher_Device.InitializedDishwasher;
import devices.Microwave_Device.InitializedMicrowave;
import devices.Oven_Device.*;

import java.util.ArrayList;

public class User {
    public static void main(String args[]){
        Smartphone smartphone = new Smartphone();
        InitializedOven oven = new InitializedOven();
        InitializedMicrowave microwave = new InitializedMicrowave();
        InitializedDishwasher dish = new InitializedDishwasher();
        //InitializedCleaningRobot cleaningRobot = new InitializedCleaningRobot();

//        smartphone.addCommand(new SwitchOnCommand(oven));
//        smartphone.addCommand(new SwitchOffCommand(oven));
//        smartphone.addCommand(new StartCommand(oven));
//        smartphone.addCommand(new SetHeatCommandOven(oven));
//        smartphone.addCommand(new SetProgramCommand(oven));
//        smartphone.addCommand(new SetTimerCommand(oven));
//
//        smartphone.addCommand(new SwitchOnCommand(microwave));
//        smartphone.addCommand(new SwitchOffCommand(microwave));
//        smartphone.addCommand(new StartCommand(microwave));
//        smartphone.addCommand(new SetWattCommand(microwave));
//        smartphone.addCommand(new SetTimerCommand(microwave));
//
//        smartphone.addCommand(new SwitchOnCommand(dish));
//        smartphone.addCommand(new SwitchOffCommand(dish));
//        smartphone.addCommand(new StartCommand(dish));
//        smartphone.addCommand(new SetProgramCommand(dish));
//        smartphone.addCommand(new SetTimerCommand(dish));
//

        
        smartphone.addDevice(oven);
        smartphone.addDevice(microwave);
        smartphone.addDevice(dish);
        smartphone.start();
    }

}
