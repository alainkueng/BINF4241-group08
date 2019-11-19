import java.util.ArrayList;
import java.util.Scanner;

public class Smartphone{
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
