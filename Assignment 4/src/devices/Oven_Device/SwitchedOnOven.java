package devices.Oven_Device;
import commands.*;
import devices.*;


import java.util.ArrayList;
import java.util.Scanner;

public class SwitchedOnOven implements Oven {

    private ArrayList commandList;
    private ArrayList<String> programs;
    int timer = -1;
    int heat = -1;
    private String setting = "";
    private ArrayList<Command> availableCommands;

    SwitchedOnOven(ArrayList commandList){
        this.commandList = commandList;
        programs = new ArrayList<>();
        programs.add("Defrost");
        programs.add("Fan");
        programs.add("Normal");
    }

    @Override
    public void setProgram() {
        getPrograms();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String program = scanner.nextLine();
            if(program instanceof String)
            for (String p : programs) {
                if (p.toLowerCase().equals(program.toLowerCase())) {
                    this.setting = p;
                    return;
                }
            }
        }
    }
    public void setHeat(int heat){
        if(0 < heat && heat <= 350){
            this.heat = heat;
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
    public void interrupt() {
        System.out.println("Oven_Device is not running");
    }

    @Override
    public Device start() {
        if(heat != -1 && timer != -1 && !setting.equals("")) {
            System.out.println("Oven_Device is starting");
            return new StartedOven(this.commandList, timer, setting, heat);
        } else{
            System.out.println("Please set timer, setting and heat to start the oven");
            return null;
        }
    }

    @Override
    public Device switchOn() {
        System.out.println("Oven is already switched on");
        return this;
    }

    @Override
    public Device switchOff() {
        System.out.println("Oven is turning off");
        return new SwitchedOffOven(this.commandList);
    }

    @Override
    public void setTimer(int time) {
        //time in seconds, max cap is 300s = 5min
        if(0 < time && time <= 300){
            timer = time;
        }
    }

    @Override
    public Integer checkTimer() {
        System.out.println("No timer has been set yet");
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
        System.out.println("Oven: Switched On");
        return "Oven: Switched On";
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Set program");
        availableCommands.add("Set timer");
        availableCommands.add("Set temperature");
        availableCommands.add("Start");
        availableCommands.add("Switch off");
        return availableCommands;
    }
}
