package commands;

import devices.*;

public class StartCommand implements Command {
    private Device device;

    public StartCommand(Device device){
        this.device = device;
    }

    @Override
    public Device execute() {
        return device.start();
    }

    @Override
    public Device undo() {
        return null;
    }

    @Override
    public String toString() {
        return "Start";
    }
}
