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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter timer in seconds, max cap 350s: ");
        String input = scanner.nextLine();
        while (!input.matches("^[0-9]*$")) {
            time = Integer.parseInt(input);
            if(0 < time && time <= 350) {
                break;
            } else{
                System.out.println("Timer has to be between 1 and 350 are allowed!\n Try again: ");
                input = scanner.nextLine();
            }
        }
        time = Integer.parseInt(input);
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
        return "Set Timer";
    }
}
