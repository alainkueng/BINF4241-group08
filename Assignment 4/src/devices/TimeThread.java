package devices;

public class TimeThread implements Runnable {
    private boolean running;
    private int time;

    public TimeThread(int timer){
        time = timer*1000;
        running = false;
    }

    public boolean isRunning(){
        return running;
    }

    @Override
    public void run(){
        try{
            running = true;
            Thread.sleep(time);
            running = false;
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
}
