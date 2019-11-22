package devices.Microwave_Device;

import devices.Device;

import java.util.ArrayList;

public class SwitchedOnMicrowave implements Microwave {


    private ArrayList commandList;
    private int timer = -1;
    private int watt = -1;

    @Override
    public void setProgram() {
        System.out.println("You can't set a program for a microwave");
    }
    @Override
    public void setWatt(int watt) {
        this.watt = watt;
    }

    @Override
    public Integer getWatt() {
        return this.watt;
    }

    @Override
    public Device interrupt() {
        System.out.println("A program was not chosen");
        return this;
    }

    @Override
    public Device start() {
        return null;
    }//Thread here

    @Override
    public Device switchOn() {
        System.out.println("Microwave is already switched on");
        return this;
    }

    @Override
    public Device switchOff() {
        return new SwitchedOffMicrowave();
    }

    @Override
    public void setTimer(int time) {
        this.timer = time;
    }

    @Override
    public Integer checkTimer() {
        return timer;
    }//Here

    @Override
    public ArrayList getCommandList() {
        return null;
    }//Here

    @Override
    public String printState() {
        return "Microwave";
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        return null;
    }//Here
}
