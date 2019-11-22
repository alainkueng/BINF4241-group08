package devices.Oven_Device;

import commands.*;
import devices.Device;
import java.util.ArrayList;

public class SwitchedOffOven implements Oven {
    private ArrayList commandList;
    private ArrayList<Command> availableCommands;


    SwitchedOffOven(ArrayList commandList){
        this.commandList = commandList;
        availableCommands = new ArrayList<>();
        availableCommands.add(new SwitchOnCommand(null));
    }

    @Override
    public void setProgram() {
        System.out.println("Turn the oven on first");
    }
    public void setHeat(int heat){
        System.out.println("Turn the oven on first");
    }

    @Override
    public ArrayList getPrograms() {
        System.out.println("Turn the oven on first");
        return null;
    }

    @Override
    public void interrupt() {
        System.out.println("The oven is not running");
    }

    @Override
    public Device start() {
        System.out.println("Please turn the oven on first");
        return null;
    }

    @Override
    public Device switchOn() {
        return new SwitchedOnOven(commandList);
    }

    @Override
    public Device switchOff() {
        System.out.println("The oven is already off");
        return null;
    }

    @Override
    public void setTimer(int time) {
        System.out.println("Please turn the oven on first");
    }


    @Override
    public Integer checkTimer() {
        System.out.println("Please turn the oven on first");
        return null;
    }

    @Override
    public ArrayList<Command> getCommandList() {
        for(Command command : availableCommands){
            System.out.println(command.toString());
        }
        return this.availableCommands;
    }

    public String printState(){
        System.out.println("Oven: Switched Off");
        return "Oven: Switched Off";
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Switch on");
        return availableCommands;
    }
}


