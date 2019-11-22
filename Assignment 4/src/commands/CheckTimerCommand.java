package commands;

import devices.Device;
import devices.Oven_Device.Oven;

public class CheckTimerCommand implements Command {
    private Device device;

    public CheckTimerCommand(Device device){
        this.device = device;
    }
    @Override
    public Device execute() {
        device.checkTimer();
        return device;
    }

    @Override
    public Device undo() {
        System.out.println("");
        return device;
    }
    @Override
    public String toString() {
        return "Check timer";
    }

    @Override
    public void updateDevice(Device device) {
    }
}
