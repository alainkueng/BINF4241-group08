package devices.cleaningrobot;

import devices.Device;

import java.util.ArrayList;

public class cleaningCleaningRobot implements CleaningRobot {
    private int timer;
    private int batteryStatus;
    private int completion;
    private ArrayList commandList;

    public cleaningCleaningRobot(int timer, int batteryStatus, int completion, ArrayList commandList){
        this.timer = timer;
        this.batteryStatus = batteryStatus;
        this.completion = completion;
        this.commandList = commandList;
    }

    @Override
    public Device start() {
        return null;
    }

    @Override
    public Device switchOn() {
        System.out.println("The cleaning robot is already cleaning, you can't switch it on again.");
        return null;
    }

    @Override
    public Device switchOff() {
        return null;
    }

    @Override
    public void setTimer(int time){
        System.out.println("You can't change the timer, while the cleaning robot is cleaning.");
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
    public String printState() {
        return "Cleaning Robot";
    }

    @Override
    public ArrayList<String> getAvailableCommands(){
        ArrayList availableCommands = new ArrayList();
        availableCommands.add("Interrupt");
        availableCommands.add("Check timer");
        availableCommands.add("Check battery");
        availableCommands.add("Check completion");
        availableCommands.add("Switch off");
        return availableCommands;
    }

    @Override
    public Device interrupt() {
        return null;
    }
}
