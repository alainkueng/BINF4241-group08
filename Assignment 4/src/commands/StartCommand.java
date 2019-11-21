package commands;

import devices.*;

public class StartCommand implements Command {
    private Device device;

    public StartCommand(Device device){
        this.device = device;
    }

    @Override
    public void execute() {
        device.switchOn();
    }

    @Override
    public void undo() {

    }
}
