package devices;

import java.util.ArrayList;

public class SwitchedOnOven implements Oven {

    private ArrayList commandList;

    SwitchedOnOven(ArrayList commandList){
        this.commandList = commandList;
    }

    @Override
    public void setProgram(String program) {

    }

    @Override
    public ArrayList getPrograms() {
        return null;
    }

    @Override
    public void interrupt() {

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
