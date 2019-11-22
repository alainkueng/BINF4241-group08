package devices.Microwave_Device;

import devices.Device;

import java.util.ArrayList;

public class InitializedMicrowave implements Microwave {
    @Override
    public void setProgram() {
        System.out.println("You can't set the program to a microwave");
    }

    private ArrayList commandList;

    @Override
    public void setWatt(int watt) {
            System.out.println("You can't set the watt if the microwave isn't switched on.");
        }

    @Override
    public Integer getWatt() {
        System.out.println("You can't get the watt if the microwave isn't switched on.");
        return null;
    }


    @Override
    public Device interrupt() {
        System.out.println("You can't interrupt the microwave if it isn't even switched on.");
        return this;
    }

    @Override
    public Device start() {
        System.out.println("You can't start the microwave if the oven isn't switched on.");
        return this;
    }

    @Override
    public Device switchOn() {
        return new SwitchedOnMicrowave();
    }

    @Override
    public Device switchOff() {
        System.out.println("Thank you for caring about the environment, but it is already switched off.");
        return this;
    }

    @Override
    public void setTimer(int time) {
        System.out.println("You can't set a timer if the microwave isn't switched on.");
    }

    @Override
    public Integer checkTimer() {
        System.out.println("You can't check the timer if the oven isn't switched on.");
        return null;
    }

    @Override
    public ArrayList getCommandList() {
        return this.commandList;
    }

    @Override
    public String printState() {
        return "Microwave";
    }

    @Override
    public ArrayList<String> getAvailableCommands() {
            ArrayList<String> availableCommands = new ArrayList<>();
            availableCommands.add("Switch on");
            return availableCommands;
    }
}
