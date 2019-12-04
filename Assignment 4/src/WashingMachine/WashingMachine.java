package WashingMachine;

import devices.Device;

import java.util.ArrayList;

public interface WashingMachine extends Device {


    void setProgram();
    ArrayList getPrograms();

}
