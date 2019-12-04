package devices.Dishwasher_Device;

import commands.*;
import devices.Device;

import java.util.ArrayList;
import java.util.Scanner;

// maybe a Checkheat function?
public class SwitchedOnDishwasher implements Dishwasher{

    private ArrayList commandList;
    private ArrayList<String> programs;
    int timer = -1;
    int heat = -1;
    private String setting = "";

    public SwitchedOnDishwasher(ArrayList commandList){
        this.commandList = commandList;
        programs = new ArrayList<>();
        programs.add("Intensive"); //55 C,
        programs.add("Heavy"); // 65 C,
        programs.add("Normal");// 50 C,
        programs.add("Rinse");// 70 C,
        programs.add("Rapid");// 50 C,
    }

    @Override
    public void setProgram() {
        getPrograms();
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        String program = "";
        int i = 0;
        while(b){
            program = scanner.nextLine();
            if (programs.get(0).toLowerCase().equals(program.toLowerCase())) {
                timer = 400;
                heat = 100;
                this.setting = programs.get(0).toLowerCase();
                break;
            }
            else if (programs.get(1).toLowerCase().equals(program.toLowerCase())){
                timer = 300;
                heat = 55;
                this.setting = programs.get(1).toLowerCase();
                break;
            }
            else if (programs.get(2).toLowerCase().equals(program.toLowerCase())){
                timer = 200;
                heat = 65;
                this.setting = programs.get(2).toLowerCase();
                break;
            }
            else if (programs.get(3).toLowerCase().equals(program.toLowerCase())){
                timer = 150;
                heat = 50;
                this.setting = programs.get(3).toLowerCase();
                break;
            }
            else if (programs.get(4).toLowerCase().equals(program.toLowerCase())){
                timer = 67;
                heat = 70;
                this.setting = programs.get(4).toLowerCase();
                break;
            } else {
                System.out.println("This doesn't exist in the menu, please choose something from the menu.");
            }
        }
    }

    @Override
    public ArrayList getPrograms() {
        System.out.println("Choose a program:\n");
        for(String p: programs){
            System.out.printf("- %s\n",p);
        }
        return programs;
    }

    @Override
    public Device interrupt() {
        System.out.println("Dishwasher_Device is not running");
        return null;

    }

    @Override
    public Device start() {
        if (timer > 0 && heat > 0) {
            StartedDishwasher sD = new StartedDishwasher(commandList, timer, heat, setting);
            sD.start();
            return sD;
        }
        else{
            System.out.println("Choose a program first");
            return this;
        }
    }

    @Override
    public Device switchOn() {
        System.out.println("Dishwasher is already switched on");
        return this;
    }

    @Override
    public Device switchOff() {
        System.out.println("Dishwasher is turning off");
        return new SwitchedOffDishwasher(this.commandList);
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set a timer for the dishwasher");
    }

    @Override
    public Long checkTimer() {
        if (timer == -1){
            System.out.println("No program has been chosen yet");
        }
        else{System.out.printf("\nTimer is set to %d", timer);
    }
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new SetProgramCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        placeholder.add(new StartCommand(this));
        return placeholder;
    }

    @Override
    public String printState() {
        System.out.println("Dishwasher");
        return "Dishwasher";
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Set program");
        availableCommands.add("Start");
        availableCommands.add("Switch off");
        return availableCommands;
    }
}
