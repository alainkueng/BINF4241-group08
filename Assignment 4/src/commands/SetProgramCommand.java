package commands;

import devices.Oven_Device.Oven;

public class SetProgramCommand implements Command {
    Oven oven;
    String program;

    public SetProgramCommand(Oven oven){
        this.oven = oven;
    }
    @Override
    public void execute() {
        oven.setProgram();
    }

    @Override
    public void undo() {
        oven.interrupt();
    }

    @Override
    public String toString() {
        System.out.println("Set program");
        return "Set program";
    }
}
