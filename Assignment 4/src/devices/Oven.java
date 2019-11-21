package devices;

import java.util.ArrayList;

public interface Oven extends Device{
    public void setProgram(String program);
    public ArrayList getPrograms();
    public void interrupt();
}
