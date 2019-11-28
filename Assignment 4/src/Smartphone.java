import commands.*;
import devices.*;
import devices.Microwave_Device.*;
import devices.Oven_Device.*;
import devices.Dishwasher_Device.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Smartphone{
    private ArrayList<Device> devices;
    private ArrayList<Command> commands;
    private boolean appOpen;
    InitializedMicrowave initializedMicrowave;
    InitializedOven initializedOven;
    InitializedDishwasher initializedDishwasher;


    public Smartphone(){
        this.appOpen = true;
        this.devices = new ArrayList<>();
        this.commands = new ArrayList<>();
    }

    private void mainMenu(){
        while(this.appOpen){
            String input;

            System.out.println("Which one of the following devices you'd like to modify?\n");
            for(Device device:devices){
                System.out.format("- %s\n", device.printState());
            }
            System.out.println("\nClose app\n");
            input = inputCheck().toLowerCase();
            if(input.equals("close app") || input.equals("close")){
                this.appOpen = false;
                break;
            }
            for(int i = 0; i < devices.size(); i++){
                Device device = devices.get(i);
                if(device.printState().toLowerCase().equals(input)){
                    subMenu(device);
                    break;
                }
                if(i + 1 == devices.size()){
                    System.out.println("\nThis doesn't exist in the menu, please choose something from the menu.\n");
                    input = inputCheck().toLowerCase();
                    i = 0;
                }
            }
        }
    }

    private void subMenu(Device device){
        while(true){
            String input;
            Device statusChanged;
            System.out.format("What do you want to do with the %s?\n\n", device.printState());
            for (String command : device.getAvailableCommands()){
                System.out.format("- %s\n", command);
            }
            System.out.println("\nBack to devices menu\n");
            input = inputCheck().toLowerCase();
            if(input.equals("Back to devices menu")){
                break;
            }
            commands = device.getCommandList();
            for(int i = 0; i < commands.size(); i++){
                Command command = commands.get(i);
                if(command.toString().toLowerCase().equals(input)){
//                    updateCommand(device);
                    statusChanged = command.execute();
                    if(statusChanged != null){
                        for(int j = 0; j < devices.size(); j++){
                            if(devices.get(j).printState().equals(statusChanged.printState())){
                                devices.set(j,statusChanged);
                                device = statusChanged;
                                commands = device.getCommandList();
//                                updateCommand(device);
                                break;
                            }
                        }
                    }
                    break;
                }
                if(i + 1 == commands.size()){
                    System.out.println("This doesn't exist in the menu, please choose something from the menu.\n");
                    input = inputCheck().toLowerCase();
                    i = 0;
                }
            }
        }
    }

    public void addDevice(Device device){
        this.devices.add(device);
    }

    private String inputCheck(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(input.equals("") || !input.matches("^[a-z A-Z]*$")){
            System.out.println("This doesn't exist in the menu, please choose something from the menu.\n");
            input = scanner.nextLine();
        }
        return input;
    }

    public void addCommand(Command command){
        this.commands.add(command);
    }

    private void updateCommand(Device device){
        ArrayList<String>availableCommands = device.getAvailableCommands();
        for(String availableCommand:availableCommands){
            for(Command command:this.commands){
                if(command.toString().equals(availableCommand)){
                    command.updateDevice(device);
                }
            }
        }
    }

    public void start(){
        mainMenu();
    }
}
