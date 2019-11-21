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
    public void execute() {
        boolean flag = false;
        while (!flag) {
            Scanner scanner = new Scanner(System.in);
            int time = scanner.nextInt();
            if(0 < time && time <= 300)
                flag = true;

        }
        device.setTimer(time);
    }

    @Override
    public void undo() {
        time = 0;
        device.setTimer(time);
    }
    @Override
    public String toString() {
        System.out.println("Set Timer");
        return "Set Timer";
    }
}
