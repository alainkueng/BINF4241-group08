package devices;

import java.util.ArrayList;

public interface Oven implements Device{
    public void switchOn();
    public void setTimer(Integer time);
    public Integer checkTimer();
    public void setProgram(String program);
    public ArrayList getPrograms();
    public void start();
    public void interrupt();
    public void switchOff();
}
