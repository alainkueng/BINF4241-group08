package devices;

import java.util.ArrayList;

public class InitializedMicrowave implements Microwave {
    public void switchOn(){}
    public void setTimer(Integer time){}
    public Integer checkTimer(){return 1;}
    public void setProgram(String program){}
    public ArrayList getPrograms(){return new ArrayList();}
    public void startBaking(){}
    public void interrupt(){}
    public void switchOff(){}
}
