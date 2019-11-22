package devices.Oven_Device;

import commands.Command;
import commands.SwitchOnCommand;
import devices.Device;
import devices.Oven_Device.Oven;

import java.util.ArrayList;

public class InitializedOven implements Oven {
    private ArrayList commandList;

    public Device switchOn(){
        return new SwitchedOnOven(this.commandList);
    }
    public void setTimer(int time){
        System.out.println("You can't set a timer if the oven isn't switched on.");
    }
    public long checkTimer(){
        System.out.println("You can't check the timer if the oven isn't switched on.");
        return 0;
    }
    public void setProgram(){
        System.out.println("You can't set a program if the oven isn't switched on.");
    }

    @Override
    public void setHeat(int heat) {
        System.out.println("You can't set heat if the oven isn't switched on");
    }

    public ArrayList getPrograms(){
        System.out.println("You can't get the programs if the oven isn't switched on.");
        return null;
    }
    public Device start(){
        System.out.println("You can't start baking if the oven isn't switched on.");
        return this;
    }
    public Device interrupt(){
        System.out.println("You can't interrupt the oven if it isn't even switched on.");
        return this;
    }

    public Device switchOff(){
        System.out.println("Thank you for caring about the environment, but it is already switched off.");
        return this;
    }
    @Override
    public ArrayList getCommandList() {
        return this.commandList;
    }

    public String printState(){
        return "Oven";
    }

    @Override
    public ArrayList<String> getAvailableCommands(){
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Switch on");
        return availableCommands;
    }

    public void addCommandList(ArrayList commands){
        this.commandList = commands;
    }
}
