package devices;

import java.util.ArrayList;

public interface CleaningRobot extends Device{

    void setProgram(String program);
    ArrayList getPrograms();
    void startBaking();
    void interrupt();

}
