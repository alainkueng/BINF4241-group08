package devices;

public interface Device {
    public Device start();
    public Device switchOn();
    public Device switchOff();
    public Device setTimer();
    public int getTimer();
}
