package commands;

import devices.*;

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
}
