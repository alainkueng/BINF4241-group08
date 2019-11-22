package devices.Oven_Device;
import java.util.Scanner;

import devices.Device;
import devices.TimeThread;
import java.util.ArrayList;
import commands.*;

public class StartedOven implements Oven {
    private boolean running;
    private int time;
    private ArrayList commandList;
    TimeThread timeT;
    Thread thread;
    long elapsedT;

    StartedOven(ArrayList commandList, int timer, String program, int heat){
        this.commandList = commandList;
        this.running = false;
        this.time = timer;

    }

    @Override
    public void setProgram() {

    }

    @Override
    public ArrayList getPrograms() {
        return null;
    }

    @Override
    public Device interrupt() {
        if (timeT.isRunning()){
            timeT = null;
            float time = System.currentTimeMillis() - elapsedT;
            System.out.println("Action was stopped\nElapsed time: " + time);
        }
        return new SwitchedOnOven(commandList);
    }

    @Override
    public void setHeat(int heat) {
        System.out.println("Oven heat was changed to " + heat);
    }

    @Override
    public Device start() {
        this.timeT = new TimeThread(time);
        this.thread = new Thread(timeT);
        elapsedT = System.currentTimeMillis();
        thread.start(); //start thread
        while (timeT.isRunning()) {
            System.out.println("Running");
        }
        System.out.println("Available commands:\n");
        for (String s : getAvailableCommands()) {
            System.out.println("- " + s);
        }

        if (!timeT.isRunning()) {
            System.out.println("Action has finished");
            return new SwitchedOnOven(commandList);
        } else {
            System.out.println("Action is still running");
            return this;
        }
    }


    @Override
    public Device switchOn() {
        System.out.println("Oven is already switched on and running");
        return null;
    }

    @Override
    public Device switchOff() {
        if(timeT.isRunning()) {
            interrupt();
        }
        return new SwitchedOffOven(commandList);
    }

    @Override
    public void setTimer(int time) {
        if(timeT.isRunning()){
            System.out.printf("Timer was adjusted to %d seconds", time);
            timeT = new TimeThread(time);
            thread = new Thread(timeT);
            thread.start();
        }
    }

    @Override
    public Long checkTimer() {
        long time = System.currentTimeMillis() - elapsedT;
        System.out.println("Timer : " + time);
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new SetProgramCommand(this));
        placeholder.add(new SetTimerCommand(this));
        placeholder.add(new SetHeatCommandOven(this));
        placeholder.add(new InterruptCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        placeholder.add(new CheckTimerCommand(this));
        return placeholder;
    }





    @Override
    public String printState() {
        return "Oven";
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("- Interrupt\n- Check Timer \n- Set timer\n- Switch off \n- Set heat\n- Set program");
        return availableCommands;
    }

}
