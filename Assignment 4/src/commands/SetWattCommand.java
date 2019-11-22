package commands;

import devices.Device;
import devices.Microwave_Device.Microwave;

import java.util.Scanner;


public class SetWattCommand implements Command {
    private int heat;
    private Microwave microwave;
    private boolean inputCheck;

    public SetWattCommand(Microwave microwave){
        this.microwave = microwave;
        this.inputCheck = false;
    }

    @Override
    public Device execute() {
        while (!inputCheck) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter temperature in Watt: ");
            String input = scanner.nextLine();
            if (input.matches("^[0-9]*$") && Integer.parseInt(input) >= 150 && Integer.parseInt(input) <= 700) {
                inputCheck = true;
                heat = Integer.parseInt(input);
            } else {
                System.out.println("Only degrees between 150 and 700 are allowed!");
            }
        }
        microwave.setWatt(heat);
        return null;
    }
    @Override
    public Device undo() {
        heat = -1;
        microwave.setWatt(heat); //for all Devices
        return null;
    }

    @Override
    public void updateDevice(Device device) {

    }
}
