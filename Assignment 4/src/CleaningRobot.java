import java.util.ArrayList;

public interface CleaningRobot {
    public void switchOn();
    public void setTimer(Integer time);
    public Integer checkTimer();
    public void setProgram(String program);
    public ArrayList getPrograms();
    public void startBaking();
    public void interrupt();
    public void switchOff();
}
