package commands;

import devices.Device;
import devices.Oven_Device.Oven;

import java.util.Scanner;

public class SetHeatCommandOven implements Command {
    private Oven oven;
    private int heat;

    public SetHeatCommandOven(Oven oven){
        this.oven = oven;
    }

    @Override
    public Device execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter temperature in Celcius: ");
        String input = scanner.nextLine();
        while (input.matches("^[0-9]*$") || !input.matches("^[0-9]*$") ) {
            if(input.matches("^[0-9]*$")){
                heat = Integer.parseInt(input);
                if(0 < heat && heat <= 350) {
                    break;
                }
            } else {
                System.out.println("Only degrees between 1 and 350 are allowed!\n Try again: ");
                input = scanner.nextLine();
            }
        }
        int heat = Integer.parseInt(input);
        oven.setHeat(heat);
        return null;
    }

    @Override
    public Device undo() {
        heat = -1;
        oven.setHeat(heat);
        return null;
    }

    @Override
    public String toString() {
        return "Set Heat";
    }
    @Override
    public void updateDevice(Device device) {
        this.oven = (Oven)device;
    }
}
