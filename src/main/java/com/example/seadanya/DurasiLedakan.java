package com.example.seadanya;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DurasiLedakan extends timer{
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    static int secondsPassedh=0;
    Timer myTimerh = new Timer();
    TimerTask taskh = new TimerTask() {
        @Override
        public void run() {
            if(secondsPassedh<=3)
                secondsPassedh++;
        }
    };
    public void starth(){
        myTimerh.scheduleAtFixedRate(taskh,0,500);
    }
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if(secondsPassedh<=3)
                secondsPassedh++;
        }
    };

    public void stoph() throws InterruptedException {
        myTimerh.cancel();
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(r, 1L, 1L, TimeUnit.SECONDS);
        Thread.sleep(1L);
        scheduledFuture.cancel(false);
    }

    public void pause(){
        myTimerh.cancel();
    }

    public static int getSecondsPassedh() {
        return secondsPassedh;
    }

    public static void setSecondsPassedh(int secondsPassedh) {
        DurasiLedakan.secondsPassedh = secondsPassedh;
    }
}
