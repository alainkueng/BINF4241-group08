package devices.Oven_Device;
import java.util.Scanner;

import devices.Device;

import devices.TimeThread;

import java.util.ArrayList;

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
        timeT = new TimeThread(time);
        thread = new Thread(timeT);
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
        elapsedT = System.currentTimeMillis();
        thread.start(); //start thread

        System.out.println("Available commands:\n");
        for(String s : getAvailableCommands()){
            System.out.println("- " + s);
        }
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (command.equals("") && timeT.isRunning() || !command.matches("^[a-zA-Z]*$") && timeT.isRunning()
                || !(command.equals("interrupt")) && timeT.isRunning()) {
            command = scanner.nextLine();
            if (command.toLowerCase().equals("Interrupt".toLowerCase()) && timeT.isRunning()) {
                //interrupt thread, go back to SwitchedOn state
                return interrupt();
            } else {
                command = scanner.nextLine();
            }
        } if(!timeT.isRunning()) {
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
    public long checkTimer() {
        long time = System.currentTimeMillis() - elapsedT;
        return time;
    }

    @Override
    public ArrayList getCommandList() {
        return commandList;
    }





    @Override
    public String printState() {
        return "Oven";
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Interrupt");
        return availableCommands;
    }

}
