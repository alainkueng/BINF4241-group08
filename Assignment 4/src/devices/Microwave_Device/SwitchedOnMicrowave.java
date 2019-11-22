package devices.Microwave_Device;

import commands.*;
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
    public Long checkTimer() {
        long tim = timer;
        return tim;
    }//Here

    @Override
    public ArrayList getCommandList() {
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new SetTimerCommand(this));
        placeholder.add(new SetWattCommand(this));
        placeholder.add(new InterruptCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        placeholder.add(new CheckTimerCommand(this));
        return placeholder;
    }//Here

    @Override
    public String printState() {
        return "Microwave";
    }

    @Override
    public ArrayList getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Set timer");
        availableCommands.add("Set watt");
        availableCommands.add("Start");
        availableCommands.add("Switch off");
        return availableCommands;
    }//Here
}
