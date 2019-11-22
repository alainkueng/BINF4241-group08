package devices.Microwave_Device;

import commands.*;
import devices.Device;

import java.util.ArrayList;

public class StartedMicrowave implements Microwave {
    private ArrayList commandList;
    private int timer = -1;
    private int watt = -1;

    public StartedMicrowave(int timer, int watt) {
        this.timer = timer;
        this.watt = watt;
    }

    @Override
    public void setProgram() {
        System.out.println("You can't set a program for a microwave");
    }

    @Override
    public void setWatt(int Watt) {
        System.out.println("You can't watt while the microwave is running");

    }

    @Override
    public Integer getWatt() {
        return timer;
    }

    @Override
    public Device interrupt() {
        return null;
    }//here

    @Override
    public Device start() {
        System.out.println("You can't start the machine when it's already running");
        return this;
    }

    @Override
    public Device switchOn() {
        System.out.println("It's already switched on");
        return this;
    }

    @Override
    public Device switchOff() { //here so you switch off?
        System.out.println("You can't switch off while the microwave is running");
        return this;
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set while the microwave is running");
    }

    @Override
    public Long checkTimer() {// here
        if(timer == -1)
            System.out.println("No timer has been set yet");
        else
            System.out.println("Timer: " + timer);
        return null;
    }

    @Override
    public ArrayList getCommandList() {//here
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new SetTimerCommand(this));
        placeholder.add(new SetWattCommand(this));
        placeholder.add(new InterruptCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        placeholder.add(new SwitchOnCommand(this));
        placeholder.add(new CheckTimerCommand(this));
        return placeholder;
    }

    @Override
    public String printState() {
        return "Microwave";
    }

    @Override
    public ArrayList getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("- Interrupt\n- Check Timer \n- Set timer\n- Switch off \n- Set watt");
        return availableCommands;
    }
}