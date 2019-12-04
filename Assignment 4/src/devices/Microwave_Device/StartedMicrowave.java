package devices.Microwave_Device;

import commands.*;
import devices.Device;
import devices.TimeThread;

import java.util.ArrayList;

public class StartedMicrowave implements Microwave {
    private ArrayList commandList;
    private int timer = -1;
    private int watt = -1;
    TimeThread timeT;
    Thread thread;
    long elapsedT;
    private boolean running;

    public StartedMicrowave(ArrayList commandList,int timer, int watt) {
        this.timer = timer;
        this.watt = watt;
        this.running = false;
        this.commandList = commandList;
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
        if (timeT.isRunning()){
            timeT.a = false;
            timeT = null;
            float time = System.currentTimeMillis() - elapsedT;
            System.out.println("Microwave was stopped\nElapsed time: " + time/1000);
        }
        return new SwitchedOnMicrowave();
    }

    @Override
    public Device start() {
        this.timeT = new TimeThread(timer);
        this.thread = new Thread(timeT);
        elapsedT = System.currentTimeMillis();
        thread.start(); //start thread

        if (!timeT.isRunning()) {
            System.out.println("Microwave is running");
            return new SwitchedOnMicrowave();
        } else {
            System.out.println("Microwave is done");
            return this;
        }
    }

    @Override
    public Device switchOn() {
        System.out.println("It's already switched on");
        return this;
    }

    @Override
    public Device switchOff() { //here so you switch off?
        System.out.println("Microwave is turning off");
        interrupt();
        return new SwitchedOffMicrowave();
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set while the microwave is running");
    }

    @Override
    public Long checkTimer() {// here
        long time = (System.currentTimeMillis() -  elapsedT)/1000;
        long t2 = (timer) - time;
        if(t2 <= 0){
            t2 = 0;
            System.out.println("Timer : " + t2 + "s remaining");
        } else {
            System.out.println("Timer : " + t2 + "s remaining");
        }
        return null;
    }

    @Override
    public ArrayList getCommandList() {//here
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new SetTimerCommand(this));
        placeholder.add(new CheckTimerCommand(this));
        placeholder.add(new SetWattCommand(this));
        placeholder.add(new InterruptCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        placeholder.add(new SwitchOnCommand(this));
        return placeholder;
    }

    @Override
    public String printState() {
        return "Microwave";
    }

    @Override
    public ArrayList getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Interrupt");
        availableCommands.add("Check Timer");
        availableCommands.add("Set timer");
        availableCommands.add("Switch off");
        availableCommands.add("Set watt");
        return availableCommands;
    }
}