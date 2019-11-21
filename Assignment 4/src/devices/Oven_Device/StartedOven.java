package devices.Oven_Device;

import devices.Device;
import devices.Oven_Device.Oven;

import java.util.ArrayList;

public class StartedOven implements Oven {

    private ArrayList commandList;

    StartedOven(ArrayList commandList, int timer, String program, int heat){
        this.commandList = commandList;
    }

    @Override
    public void setProgram() {

    }

    @Override
    public ArrayList getPrograms() {
        return null;
    }

    @Override
    public void interrupt() {

    }

    @Override
    public void setHeat(int heat) {

    }

    @Override
    public Device start() {
        return null;
    }

    @Override
    public Device switchOn() {
        return null;
    }

    @Override
    public Device switchOff() {
        return null;
    }

    @Override
    public void setTimer(int time) {

    }

    @Override
    public Integer checkTimer() {
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        return this.commandList;
    }
}
