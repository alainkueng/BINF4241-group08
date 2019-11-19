import commands.Command;
import devices.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Smartphone{
    //every device stores an ArrayList with possible commands (implements Command)
    //there exists a getCommandList-function for every device which returns the list
    //then we process the user input with a HashMap "start" gets mapped to startCommand-object
    //returnValue = hashMap.get(input).execute(), execute returns an object if the state gets changed or returns null
    //if nothing is changed (in order to update the devices list accordingly
    //if returnValue not null then
    //  for device : devices
    //      if device.getClass().getInterfaces()[0] == returnValue.getClass().getInterfaces()[0] then
    //          device = returnValue, not sure if this works but it's just exchanging the device at this index basically

    private Oven oven;
    private Microwave microwave;
    private Dishwasher dishwasher;
    private CleaningRobot cleaningRobot;
    private ArrayList<Object> devices;
    private boolean appOpen;
    Command[] deviceCommands;

    public Smartphone(){
        this.oven = new InitializedOven();
        this.microwave = new InitializedMicrowave();
        this.dishwasher = new InitializedDishwasher();
        this.cleaningRobot = new InitializedCleaningRobot();
        this.appOpen = true;
        this.devices = new ArrayList<>();
        addDevice(this.oven);
        addDevice(this.microwave);
        addDevice(this.dishwasher);
        addDevice(this.cleaningRobot);
        mainMenu();
    }

    private void setCommand(Command command){

    }

    private void mainMenu(){
        while(this.appOpen){
            System.out.println("Which one of the following devices you'd like to modify?");
            for(Object device:devices){
                System.out.format("- %s\n", device.getClass().getInterfaces()[0].getSimpleName());
            }
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
        }
    }

    public void addDevice(Object device){
        this.devices.add(device);
    }
}
