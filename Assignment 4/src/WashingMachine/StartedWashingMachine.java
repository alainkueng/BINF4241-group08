package WashingMachine;

import commands.*;
import devices.Device;
import devices.TimeThread;

import java.util.ArrayList;

public class StartedWashingMachine implements WashingMachine{
    private ArrayList commandList;
    private ArrayList<String> programs;
    int timer = -1;
    int heat = -1;
    private String setting;
    TimeThread timeT;
    Thread thread;
    long elapsedT;

    public StartedWashingMachine(ArrayList commandList, int timer, int heat, String setting){
        this.commandList = getCommandList();
        this.timer = timer;
        this.heat = heat;
        this.setting = setting;
        programs = new ArrayList<>();
        programs.add("Intensive"); //55 C, 150 minutes
        programs.add("Heavy"); // 65 C, 135 minutes
        programs.add("Normal");// 50 C, 113 minutes
        programs.add("Rinse");// 70 C, 67 minutes
        programs.add("Rapid");// 50 C, 50 minutes

    }
    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Start");
        availableCommands.add("Switch off");
        availableCommands.add("Interrupt");
        availableCommands.add("Check timer");
        return availableCommands;
    }


    public StartedWashingMachine(ArrayList commandList){
        this.commandList = commandList;
    }

    @Override
    public void setProgram() {
        System.out.println("You can't set a program while the  Washing Machine is running");
    }

    @Override
    public ArrayList getPrograms() {
        System.out.println("Choose a program:\n");
        for(String p: programs){
            System.out.printf("- %s\n",p);
        }
        return this.programs;
    }

    @Override
    public Device interrupt() {
        if(timeT != null) {
            if (timeT.isRunning()) {
                timeT.a = false;
                timeT = null;
                float time = System.currentTimeMillis() - elapsedT;
                System.out.println("Action was stopped\nElapsed time: " + time / 1000);
            }
        }
        return new SwitchedOnWashingMachine(this.commandList);
    }

    @Override
    public Device start() {
        this.timeT = new TimeThread(timer);
        this.thread = new Thread(timeT);
        timeT.setDevice(this);
        elapsedT = System.currentTimeMillis();
        thread.start(); //start thread
        long time = (System.currentTimeMillis() -  elapsedT)/1000;


        if (!timeT.isRunning()) {
            System.out.println("Washing machine is running");
            return new SwitchedOnWashingMachine(commandList);
        } else {
            System.out.println("Washing machine has finished");
            return this;
        }
    }


    @Override
    public Device switchOn() {
        System.out.println("The Washing Machine is already on");
        return this;
    }

    @Override
    public Device switchOff() {//Thread? anpassen
        System.out.println("Washing Machine is turning off");
        return new SwitchedOffWashingMachine(this.commandList);
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set a timer for the Washing Machine");
    }

    @Override
    public Long checkTimer() {
        long time = (System.currentTimeMillis() -  elapsedT)/1000;
        long t2 = (timer) - time;
        if(t2 <= 0){
            t2 = 0;
            System.out.println("Timer : " + t2 + "s remaining");
            interrupt();
        } else {
            System.out.println("Timer : " + t2 + "s remaining");
        }
        return t2;
    }

    @Override
    public ArrayList getCommandList() {
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new InterruptCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        placeholder.add(new CheckTimerCommand(this));
        commandList = placeholder;
        return commandList;
    }

    @Override
    public String printState() {
        return "Washing Machine";
    }

}
