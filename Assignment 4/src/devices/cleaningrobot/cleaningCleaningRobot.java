package devices.cleaningrobot;

import devices.Device;
import devices.TimeThread;

import java.util.ArrayList;

public class cleaningCleaningRobot implements CleaningRobot {
    private int timer;
    private int batteryStatus;
    private int completion;
    private ArrayList commandList;
    private TimeThread cleaningThread;
    private Thread ct;
    private long timeStamp;


    public cleaningCleaningRobot(int timer, int batteryStatus, int completion, ArrayList commandList){
        this.timer = timer;
        this.batteryStatus = batteryStatus;
        this.completion = completion;
        this.commandList = commandList;
        start();
    }

    @Override
    public Device start() {
        this.cleaningThread = new TimeThread(timer);
        this.ct = new Thread(cleaningThread, "cleaningThread");
        if(cleaningThread.isRunning()){
            System.out.println("The cleaning robot is still cleaning, please interrupt it if needed.");
        }
        else{
            timeStamp = System.currentTimeMillis();
            ct.start();
        }
        return null;
    }

    @Override
    public Device switchOn() {
        System.out.println("The cleaning robot is already cleaning, you can't switch it on again.");
        return null;
    }

    @Override
    public Device switchOff() {
        return new powerSaveCleaningRobot();
    }

    @Override
    public void setTimer(int time){
        System.out.println("You can't change the timer, while the cleaning robot is cleaning.");
    }

    @Override
    public Long checkTimer(){
        Long elapsed = (this.timer - (System.currentTimeMillis() - this.timeStamp))/1000;
        System.out.format("\nThere are %ds left\n", elapsed);
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        return this.commandList;
    }

    @Override
    public String printState() {
        return "Cleaning Robot";
    }

    @Override
    public ArrayList<String> getAvailableCommands(){
        ArrayList<String> availableCommands = new ArrayList();
        availableCommands.add("Interrupt");
        availableCommands.add("Check timer");
        availableCommands.add("Check battery");
        availableCommands.add("Check completion");
        availableCommands.add("Switch off");
        return availableCommands;
    }

    @Override
    public Device interrupt() {
        cleaningThread = null;
        ct = null;
        Long elapsed = (System.currentTimeMillis() - this.timeStamp)/1000;
        System.out.format("\nElapsed time: %d\n", elapsed);
        this.timer = (int)(this.timer - (System.currentTimeMillis() - this.timeStamp))/1000;
        return null;
    }

    @Override
    public void setProgram() {
        System.out.println("The cleaning robot has no programs.");
    }
}
