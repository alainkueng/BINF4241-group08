package commands;

public class StartCommand implements Command {
    Object device;

    public StartCommand(Object device){
        this.device = ;
    }

    @Override
    public void execute() {
        device.switchOn();
    }

    @Override
    public void undo() {

    }
}
