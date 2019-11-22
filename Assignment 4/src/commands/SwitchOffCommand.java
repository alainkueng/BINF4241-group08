package commands;

import devices.*;
import devices.Oven_Device.Oven;

public class SwitchOffCommand implements Command {
    private Device device;

    public SwitchOffCommand(Device device){
        this.device = device;
    }

    @Override
    public Device execute() {
        return device.switchOff();
    }

    @Override
    public Device undo() {
        return device.switchOn();
    }

    @Override
    public String toString() {
        return "Switch off";
    }

    public void updateDevice(Device device) {
        this.device = device;
    }
}
