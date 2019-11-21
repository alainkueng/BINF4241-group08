package commands;

import devices.*;

public class SwitchOnCommand implements Command {
    private Device device;

    public SwitchOnCommand(Device device){
        this.device = device;
    }

    @Override
    public void execute() {
        device.switchOn();
    }

    @Override
    public void undo() {
        device.switchOff();
    }
    @Override
    public String toString() {
        System.out.println("Switch on");
        return "Switch on";
    }
}
