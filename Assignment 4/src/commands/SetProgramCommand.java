package commands;

import devices.Device;
import devices.Oven_Device.Oven;

public class SetProgramCommand implements Command {
    Device device;
    String program;

    public SetProgramCommand(Device device){
        this.device = device;
    }
    @Override
    public Device execute() {
        device.setProgram();
        return null;
    }

    @Override
    public Device undo() {
        device.interrupt();
        return null;
    }

    @Override
    public String toString() {
        return "Set program";
    }

    @Override
    public void updateDevice(Device device) {
        this.device = (Oven)device;
    }
}
