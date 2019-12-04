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
                String name = d.getClass().getName();
                String realName = "";
                for (int i = 0;i < name.length(); i++){
                    if(name.charAt(i)!='.')
                    {realName += String.valueOf(name.charAt(i));
                    }
                    if(name.charAt(i)=='.'){
                        break;
                    }
                }
                System.out.printf("\n%s is done", realName);
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
