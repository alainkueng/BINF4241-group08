package devices;

import java.util.ArrayList;

public class InitializedCleaningRobot implements CleaningRobot {
    @Override
    public Device start() {
        return null;
    }

    @Override
    public void setTimer(int time) {

    }

    @Override
    public ArrayList getCommandList() {
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
