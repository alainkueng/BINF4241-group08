package WashingMachine;

import commands.Command;
import commands.SwitchOnCommand;
import devices.Device;

import java.util.ArrayList;

public class SwitchedOffWashingMachine implements WashingMachine{
    private ArrayList commandList;

    public SwitchedOffWashingMachine(ArrayList commands){
        this.commandList = commands;
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Switch on");
        return availableCommands;
    }

    @Override
    public Device start() {
        System.out.println("You can't start washing if the Washing Machine isn't switched on.");
        return this;
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set a timer for the Washing Machine");
    }

    @Override
    public ArrayList getCommandList() {
        ArrayList<Command> placeholder = new ArrayList<>();
        placeholder.add(new SwitchOnCommand(this));
        return placeholder;
    }

    @Override
    public String printState() {
        return "Washing Machine";
    }

    public Device switchOn(){
        return new SwitchedOnWashingMachine(this.commandList);
    }

    public Long checkTimer(){
        System.out.println("You can't check the timer if the Washing Machine isn't switched on.");
        return null;
    }

    public void setProgram(){
        System.out.println("You can't set a program if the Washing Machine isn't switched on.");
    }

    public ArrayList getPrograms(){
        System.out.println("You can't get the programs if the Washing Machine isn't switched on.");
        return null;
    }
    public Device interrupt(){
        System.out.println("You can't interrupt the Washing Machine if it isn't even switched on.");
        return null;
    }

    public Device switchOff(){
        System.out.println("Thank you for caring about the environment, but it is already switched off.");
        return null;
    }
}
