package devices;

import commands.Command;

import java.util.ArrayList;

public interface Device {
    public Device start();
    public Device switchOn();
    public Device switchOff();
    public void setTimer(int time);
    public Integer checkTimer();
    public ArrayList getCommandList();
}