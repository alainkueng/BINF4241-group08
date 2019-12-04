package devices;

import WashingMachine.WashingMachine;

public class TimeThread implements Runnable {
    private boolean running;
    private int time;
    private Device d;
    public boolean a;

    public TimeThread(int timer){
        time = timer*1000;
        running = false;
        a = true;
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
            if(a) {
                System.out.printf("\n%s is done", d.getClass().getCanonicalName());
                d.interrupt();
            }

        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
    public void setDevice(Device d){
        this.d = d;
    }


}
