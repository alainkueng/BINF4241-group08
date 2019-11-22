package devices.cleaningrobot;

import devices.Device;
import devices.cleaningrobot.CleaningRobot;

import java.util.ArrayList;

public class InitializedCleaningRobot implements CleaningRobot {
    @Override
    public Device start() {
        System.out.println("The cleaning robot is already running, you don't need to start again.");
        return null;
    }

    @Override
    public void setTimer(int time) {
    }

    @Override
    public ArrayList getCommandList() {
        return null;
    }

    @Override
    public String printState() {
        return null;
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        return null;
    }

    public Device switchOn(){return null;}
    public void setTimer(Integer time){}
    public Integer checkTimer(){return 1;}
    public void setProgram(String program){}
    public ArrayList getPrograms(){return new ArrayList();}
    public void startBaking(){}
    public void interrupt(){}
    public Device switchOff(){return null;}
}
