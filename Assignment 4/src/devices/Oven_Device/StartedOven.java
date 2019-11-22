package devices.Oven_Device;
import java.util.Scanner;

import devices.Device;
import devices.Oven_Device.Oven;

import java.util.ArrayList;

public class StartedOven implements Oven, Runnable {
    private boolean running;
    private int time;
    private ArrayList commandList;

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
    public void interrupt() {
        System.out.println("Action was stopped");
    }
    public void interruptAdapter(Thread thread, long elapsedTime){
        if (isRunning()) {
            thread = null;
            float time = System.currentTimeMillis() - elapsedTime;
            interrupt();
            System.out.println("Elapsed time: " + time);
        }
    }


    @Override
    public void setHeat(int heat) {
        System.out.println("Oven is ");
    }

    @Override
    public Device start() {
        return null;
    }

    @Override
    public Device switchOn() {
        return null;
    }

    @Override
    public Device switchOff() {
        return null;
    }

    @Override
    public void setTimer(int time) {

    }

    @Override
    public Integer checkTimer() {
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        return this.commandList;
    }

    @Override
    public void run() {
        try {
            running = true;
            time = time*1000; // from seconds to milliseconds
            Thread.sleep(time);
            running = false;
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public boolean isRunning(){
        return running;
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
