package commands;

import devices.*;

public class StartCommand implements Command {
    private Device device;

    public StartCommand(Device device){
        this.device = device;
    }

    @Override
    public void execute() {
        device.start();
    }

    @Override
    public void undo() {

    }

    @Override
    public String toString() {
        System.out.println("Start");
        return "Start";
    }
}
