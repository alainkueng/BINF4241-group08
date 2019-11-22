package commands;

import devices.*;

import java.util.Scanner;

public class SetTimerCommand implements Command {
    private Device device;
    private int time;

    public SetTimerCommand(Device device){
        this.device = device;
    }
    @Override
    public Device execute() {
        boolean flag = false;
        while (!flag) {
            Scanner scanner = new Scanner(System.in);
            int time = scanner.nextInt();
            if(0 < time && time <= 300)
                flag = true;

        }
        device.setTimer(time);
        return null;
    }

    @Override
    public Device undo() {
        time = 0;
        device.setTimer(time);
        return null;
    }
    @Override
    public String toString() {
        System.out.println("Set Timer");
        return "Set Timer";
    }
}
