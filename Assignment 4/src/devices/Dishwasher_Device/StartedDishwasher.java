package devices.Dishwasher_Device;

import commands.*;
import devices.Device;
//import devices.Oven_Device.SwitchedOnOven;
import devices.TimeThread;

import java.util.ArrayList;


public class StartedDishwasher implements Dishwasher {

    private ArrayList commandList;
    private ArrayList<String> programs;
    int timer = -1;
    int heat = -1;
    private String setting;
    TimeThread timeT;
    Thread thread;
    long elapsedT;

    public StartedDishwasher(ArrayList commandList, int timer, int heat, String setting){
        this.commandList = commandList;
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


    public StartedDishwasher(ArrayList commandList){
        this.commandList = commandList;
    }

    @Override
    public void setProgram() {
        System.out.println("You can't set a program while the machine is running");
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
    public Device interrupt() {//Thread anpassen
        if (timeT.isRunning()){
            timeT = null;
            float time = System.currentTimeMillis() - elapsedT;
            System.out.println("Dishwasher was stopped\nElapsed time: " + time/1000);
        }
        return new SwitchedOnDishwasher(commandList);
    }

    @Override
    public Device start() {
        this.timeT = new TimeThread(timer);
        this.thread = new Thread(timeT);
        elapsedT = System.currentTimeMillis();
        thread.start(); //start thread


        if (!timeT.isRunning()) {
            System.out.println("Dishwasher is running");
            return new SwitchedOnDishwasher(commandList);
        } else {
            System.out.println("Dishwasher has finished");
            return this;
        }
    }


    @Override
    public Device switchOn() {
        System.out.println("The machine is already on");
        return this;
    }

    @Override
    public Device switchOff() {//Thread? anpassen
        System.out.println("Dishwasher is turning off");
        interrupt();
        return new SwitchedOffDishwasher(this.commandList);
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set a timer for the dishwasher");
    }

    @Override
    public Long checkTimer() {
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
    public ArrayList getCommandList() {
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new SetTimerCommand(this));
        placeholder.add(new SetProgramCommand(this));
        placeholder.add(new InterruptCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        placeholder.add(new CheckTimerCommand(this));
        return placeholder;
    }

    @Override
    public String printState() {
        return "Dishwasher";
    }
}
