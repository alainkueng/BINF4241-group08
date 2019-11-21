package devices;

import java.util.ArrayList;

public interface WashingMachine extends Device {

    void setTemperature();
    void setProgram(String program);
    void interrupt();
    ArrayList getPrograms();

}