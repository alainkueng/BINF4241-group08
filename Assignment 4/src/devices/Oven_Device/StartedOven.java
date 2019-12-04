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
        System.out.println("Cant set a new timer while the oven is running, please interrupt the program first");
    }

    @Override
    public ArrayList getPrograms() {
        return null;
    }

    @Override
    public Device interrupt() {
        if (timeT.isRunning()){
            timeT.a = false;
            timeT = null;
            float time = System.currentTimeMillis() - elapsedT;
            System.out.println("Action was stopped\nElapsed time: " + time/1000);
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


        if (!timeT.isRunning()) {
            System.out.println("Oven is running");
            return new SwitchedOnOven(commandList);
        } else {
            System.out.println("Oven is finished");
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
        System.out.println("Oven is turning off");
        interrupt();
        return new SwitchedOffOven(commandList);
    }

    @Override
    public void setTimer(int time) {
        System.out.println("Cant set a new timer while the oven is running, please interrupt the program first");
    }

    @Override
    public Long checkTimer() {
        long time = (System.currentTimeMillis() -  elapsedT)/1000;
        long t2 = (this.time) - time;
        if(t2 <= 0){
            t2 = 0;
            System.out.println("Timer : " + t2 + "s remaining");
        } else {
            System.out.println("Timer : " + t2 + "s remaining");
        }
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        ArrayList<Command> placeholder = new ArrayList<>();
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
        availableCommands.add("Interrupt");
        availableCommands.add("Check timer");
        availableCommands.add("Switch off");
        availableCommands.add("Set heat");
        return availableCommands;
    }

}
