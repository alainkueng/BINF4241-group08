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
        programs.add("Intensive"); //55 C, 150 minutes
        programs.add("Heavy"); // 65 C, 135 minutes
        programs.add("Normal");// 50 C, 113 minutes
        programs.add("Rinse");// 70 C, 67 minutes
        programs.add("Rapid");// 50 C, 50 minutes
    }

    @Override
    public void setProgram() {
        getPrograms();
        Scanner scanner = new Scanner(System.in);
        String program = scanner.nextLine();
        while(program.equals("") || !program.matches("^[a-zA-Z]*$")){
            program = scanner.nextLine();
            for (String p : programs) {
                if (p.toLowerCase().equals(program.toLowerCase())) {
                    this.setting = p;
                    if (p == "Intensive"){ timer = 150; heat = 55;
                    }
                    if (p == "Heavy"){ timer = 135; heat = 65;
                    }
                    if (p == "Normal"){ timer = 113; heat = 50;
                    }
                    if (p == "Rinse"){ timer = 67; heat = 70;
                    }
                    if (p == "Rapid"){ timer =50; heat = 50;
                    }
                    return;

                } else {
                    System.out.println("This doesn't exist in the menu, please choose something from the menu.");
                    program = scanner.nextLine();
                }
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
    public Device start() {//anpassen Thread
        return null;
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
