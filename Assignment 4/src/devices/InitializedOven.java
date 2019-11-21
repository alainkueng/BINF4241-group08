package devices;

import java.util.ArrayList;

public class InitializedOven implements Oven {
    private ArrayList commandList;

    public InitializedOven(ArrayList commandList){
        this.commandList = commandList;
    }

    public Device switchOn(){
        return new SwitchedOnOven(this.commandList);
    }
    public void setTimer(int time){
        System.out.println("You can't set a timer if the oven isn't switched on.");
    }
    public Integer checkTimer(){
        System.out.println("You can't check the timer if the oven isn't switched on.");
        return null;
    }
    public void setProgram(String program){
        System.out.println("You can't set a program if the oven isn't switched on.");
    }
    public ArrayList getPrograms(){
        System.out.println("You can't get the programs if the oven isn't switched on.");
        return null;
    }
    public Device start(){
        System.out.println("You can't start baking if the oven isn't switched on.");
        return null;
    }
    public void interrupt(){
        System.out.println("You can't interrupt the oven if it isn't even switched on.");
    };

    public Device switchOff(){
        System.out.println("Thank you for caring about the environment, but it is already switched off.");
        return null;
    };
    public ArrayList getCommandList(){
        return this.commandList;
    }
}
