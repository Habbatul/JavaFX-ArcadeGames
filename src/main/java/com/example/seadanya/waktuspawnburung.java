package com.example.seadanya;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class waktuspawnburung extends timer{
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    static int secondsPassedb=0;
    Timer myTimerb = new Timer();
    TimerTask taskb = new TimerTask() {
        @Override
        public void run() {
            secondsPassedb++;
            //System.out.println(secondsPassedb);
        }
    };
    public void startburung(){
        myTimerb.scheduleAtFixedRate(taskb,0,10);
    }
    Runnable r = new Runnable() {
        @Override
        public void run() {
            secondsPassedb++;//System.out.println(secondsPassedb);
        }
    };

    public void stopb() throws InterruptedException {
        myTimerb.cancel();
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(r, 1L, 1L, TimeUnit.SECONDS);
        Thread.sleep(1L);
        scheduledFuture.cancel(false);
    }

    public void pause(){
        myTimerb.cancel();
    }

    public static int getSecondsPassedb() {
        return secondsPassedb;
    }

    public static void setSecondsPassedb(int secondsPassedb) {
        waktuspawnburung.secondsPassedb = secondsPassedb;
    }
}