package devices;

import java.util.ArrayList;

public class InitializedMicrowave implements Microwave {
    @Override
    public ArrayList getCommandList() {
        return null;
    }

    public Microwave switchOn(){return null;}
    public InitializedMicrowave start(){return null;}
    public void setTimer(int time){}
    public Integer checkTimer(){return 1;}
    public ArrayList getPrograms(){return new ArrayList();}
    public void startBaking(){}
    public void interrupt(){}
    public void setProgram(String program){}
    public Microwave switchOff(){return null;}


}
