package devices;

public interface WashingMachine {
    public WashingMachine switchOn();
    public void setTemperature();
    public void setProgram();
    public WashingMachine start();
    public WashingMachine switchOff();
}
