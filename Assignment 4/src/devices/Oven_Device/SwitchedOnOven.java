package devices.Oven_Device;
import commands.*;
import devices.*;


import java.util.ArrayList;
import java.util.Scanner;

public class SwitchedOnOven implements Oven {

    private ArrayList commandList;
    private ArrayList<String> programs;
    private int timer = -1;
    private int heat = -1;
    private String setting = "";

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
        Scanner scanner = new Scanner(System.in);
        String program = "";
        boolean b = true;
        int i = 0;
        while(b) {
            program = scanner.nextLine();
            for (String p : programs) {
                i+=1;
                if (p.toLowerCase().equals(program.toLowerCase())) {
                    this.setting = p;
                    b = false;
                    break;
                } else if(i >= program.length()) {
                    System.out.println("This doesn't exist in the menu, please choose something from the menu.");
                    program = scanner.nextLine();
                }
            }
        }
        setting = program;
        System.out.printf("%s was chosen as program. ", setting);
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
    public Device interrupt() {
        setting = "";
        System.out.println("A program was not chosen");
        return this;
    }

    @Override
    public Device start() {
        if(heat != -1 && timer != -1 && !setting.equals("")) {
            StartedOven sO = new StartedOven(commandList,timer,setting,heat);
            sO.start();
            return sO;
        } else {
            System.out.println("Please set timer, setting and heat to start the oven ");
            return this;
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
        placeholder.add(new SetProgramCommand(this));
        placeholder.add(new SetTimerCommand(this));
        placeholder.add(new SetHeatCommandOven(this));
        placeholder.add(new StartCommand(this));
        placeholder.add(new SwitchOffCommand(this));
        return placeholder;
    }


    public String printState(){
        return "Oven";
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Set program");
        availableCommands.add("Set timer");
        availableCommands.add("Set heat");
        availableCommands.add("Start");
        availableCommands.add("Switch off");
        return availableCommands;
    }
}
