package commands;

import devices.*;

public class SwitchOffCommand implements Command {
    private Device device;

    public SwitchOffCommand(Device device){
        this.device = device;
    }

    @Override
    public void execute() {
        device.switchOff();
    }

    @Override
    public void undo() {
        device.switchOn();
    }

    @Override
    public String toString() {
        System.out.println("Switch off");
        return "Switch off";
    }
}
