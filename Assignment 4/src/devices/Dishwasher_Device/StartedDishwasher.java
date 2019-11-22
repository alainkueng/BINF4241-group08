package devices.Dishwasher_Device;

import commands.*;
import devices.Device;

import java.util.ArrayList;


public class StartedDishwasher implements Dishwasher {

    private ArrayList commandList;
    private ArrayList<String> programs;
    int timer;
    int heat;
    private String setting;

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
        availableCommands.add("Set program");
        availableCommands.add("Start");
        availableCommands.add("Switch off");
        availableCommands.add("Interrupt");
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
        return null;
    }

    @Override
    public Device start() {
        System.out.println("The machine is already running");
        return this;
    }

    @Override
    public Device switchOn() {
        System.out.println("The machine is already on");
        return this;
    }

    @Override
    public Device switchOff() {//Thread? anpassen
        System.out.println("Dishwasher is turning off");
        return new SwitchedOffDishwasher(this.commandList);
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set a timer for the dishwasher");
    }

    @Override
    public Long checkTimer() {
        if(timer == -1)
            System.out.println("No timer has been set yet");
        else
            System.out.println("Timer: " + timer);
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new SetTimerCommand(this));
        placeholder.add(new SetProgramCommand(this));
        placeholder.add(new InterruptCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        placeholder.add(new SwitchOnCommand(this));
        placeholder.add(new CheckTimerCommand(this));
        return placeholder;
    }

    @Override
    public String printState() {
        return "Dishwasher";
    }
}
