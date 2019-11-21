package devices;

import java.util.ArrayList;

public interface Dishwasher extends Device{

    public void setProgram(String program);
    public ArrayList getPrograms();
    public void startBaking();
    public void interrupt();
}
