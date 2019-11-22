package devices;

import commands.Command;

import java.util.ArrayList;

public interface Device {
    public Device start();
    public Device switchOn();
    public Device switchOff();
    public void setTimer(int time);
    public long checkTimer();
    public ArrayList getCommandList();
    public String printState();
    public ArrayList<String> getAvailableCommands();
    public Device interrupt();
    public void setProgram();

}
