package WashingMachine;

import commands.*;
import devices.Device;


import java.util.ArrayList;
import java.util.Scanner;

public class SwitchedOnWashingMachine implements WashingMachine{
    private ArrayList commandList;
    private ArrayList<String> programs;
    int timer = -1;
    int heat = -1;
    private String setting = "";

    public SwitchedOnWashingMachine(ArrayList commandList){
        this.commandList = getCommandList();
        programs = new ArrayList<>();
        programs.add("Double Rinse"); //55 C,
        programs.add("Intense"); // 65 C,
        programs.add("Quick");// 50 C,
        programs.add("Spin");// 70 C,
    }

    @Override
    public void setProgram() {
        getPrograms();
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        String program = "";
        while(b){
            program = scanner.nextLine();
            if (programs.get(0).toLowerCase().equals(program.toLowerCase())) {
                timer = 200;
                heat = 55;
                this.setting = programs.get(0).toLowerCase();
                break;
            }
            else if (programs.get(1).toLowerCase().equals(program.toLowerCase())){
                timer = 150;
                heat = 80;
                this.setting = programs.get(1).toLowerCase();
                break;
            }
            else if (programs.get(2).toLowerCase().equals(program.toLowerCase())){
                timer = 20;
                heat = 30;
                this.setting = programs.get(2).toLowerCase();
                break;
            }
            else if (programs.get(3).toLowerCase().equals(program.toLowerCase())) {
                timer = 70;
                heat = 40;
                this.setting = programs.get(3).toLowerCase();
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
        System.out.println("Washing Machine is not running");
        return null;

    }

    @Override
    public Device start() {
        if (timer > 0 && heat > 0) {
            StartedWashingMachine sM = new StartedWashingMachine(commandList, timer, heat, setting);
            sM.start();
            return sM;
        }
        else{
            System.out.println("Choose a program first");
            return this;
        }
    }

    @Override
    public Device switchOn() {
        System.out.println("Washing Machine is already switched on");
        return this;
    }

    @Override
    public Device switchOff() {
        System.out.println("Washing Machine is turning off");
        return new SwitchedOffWashingMachine(this.commandList);
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set a timer for the Washing Machine");
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
        commandList = placeholder;
        return commandList;
    }

    @Override
    public String printState() {
        return "Washing Machine";
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
