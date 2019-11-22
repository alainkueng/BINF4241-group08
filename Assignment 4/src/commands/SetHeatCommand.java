package commands;

import devices.Device;
import devices.Oven_Device.Oven;

import java.util.Scanner;

public class SetHeatCommand implements Command {
    private Oven oven;
    private int time;

    public SetHeatCommand(Oven oven){
        this.oven = oven;
    }

    @Override
    public Device execute() {
        boolean flag = false;
        while (!flag) {
            Scanner scanner = new Scanner(System.in);
            int time = scanner.nextInt();
            if(0 < time && time <= 350)
                flag = true;
        }
        oven.setHeat(time);
        return null;
    }

    @Override
    public Device undo() {
        time = 0;
        oven.setHeat(time);
        return null;
    }

    @Override
    public String toString() {
        System.out.println("Set Heat");
        return "Set Heat";
    }
}
