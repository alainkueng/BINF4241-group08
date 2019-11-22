package devices.Dishwasher_Device;

import commands.Command;
import devices.Device;

import java.util.ArrayList;


public class InitializedDishwasher implements Dishwasher {
    private ArrayList commandList;

    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Start");
        return availableCommands;
    }

    @Override
    public Device start() {
        System.out.println("You can't start washing if the dishwasher isn't switched on.");
        return this;
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set a timer for the dishwasher");
    }

    @Override
    public ArrayList getCommandList() {
        return this.commandList;
    }

    @Override
    public String printState() {
        return "Dishwasher";
    }

    public Device switchOn(){
        return new StartedDishwasher(this.commandList);
    }

    public Integer checkTimer(){
        System.out.println("You can't check the timer if the dishwasher isn't switched on.");
        return null;
    }

    public void setProgram(){
        System.out.println("You can't set a program if the dishwasher isn't switched on.");
    }

    public ArrayList getPrograms(){
        System.out.println("You can't get the programs if the dishwasher isn't switched on.");
        return null;
    }
    public Device interrupt(){
        System.out.println("You can't interrupt the dishwasher if it isn't even switched on.");
        return this;
    }

    public Device switchOff(){
        System.out.println("Thank you for caring about the environment, but it is already switched off.");
        return this;
    }
}
