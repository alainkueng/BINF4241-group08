package devices.cleaningrobot;
import devices.Device;
import java.util.ArrayList;

public class InitializedCleaningRobot implements CleaningRobot {

    private int timer = -1;
    private int batteryStatus = 100;
    private int completion = 0;
    private ArrayList commandList;

    @Override
    public Device start() {
        return new cleaningCleaningRobot(timer, batteryStatus, completion, commandList);
    }

    @Override
    public Device switchOn() {
        System.out.println("The cleaning robot is already switched on.");
        return null;
    }

    @Override
    public Device switchOff() {
        return new powerSaveCleaningRobot();
    }

    @Override
    public void setTimer(int time) {
        this.timer = time;
    }

    @Override
    public Integer checkTimer() {
        System.out.format("\nThe timer is set for %d", this.timer);
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        return this.commandList;
    }

    @Override
    public String printState() {
        return "Cleaning Robot";
    }

    @Override
    public ArrayList<String> getAvailableCommands(){
        ArrayList<String> availableCommands = new ArrayList<>();
        availableCommands.add("Start");
        availableCommands.add("Check timer");
        availableCommands.add("Check battery");
        availableCommands.add("Check completion");
        availableCommands.add("Switch off");
        return availableCommands;
    }

    @Override
    public Device interrupt() {
        System.out.println("You can't interrupt, since the cleaning Robot isn't cleaning.");
        return null;
    }

    public void addCommandList(ArrayList commands){
        this.commandList = commands;
    }
}
