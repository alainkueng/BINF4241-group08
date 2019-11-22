package commands;

import devices.*;

public class SwitchOnCommand implements Command {
    private Device device;

    public SwitchOnCommand(Device device){
        this.device = device;
    }

    @Override
    public Device execute() {
        return device.switchOn();
    }

    @Override
    public Device undo() {
        return device.switchOff();
    }
    @Override
    public String toString() {
        return "Switch on";
    }

    public void updateDevice(Device device) {
        this.device = device;
    }
}
