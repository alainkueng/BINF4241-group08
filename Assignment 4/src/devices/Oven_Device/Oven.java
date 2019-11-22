package devices.Oven_Device;

import devices.Device;

import java.util.ArrayList;

public interface Oven extends Device {

    public void setProgram();
    public ArrayList getPrograms();
    public void setHeat(int heat);
}
