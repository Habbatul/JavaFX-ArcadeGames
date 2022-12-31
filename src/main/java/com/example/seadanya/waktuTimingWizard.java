package com.example.seadanya;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class waktuTimingWizard extends timer{
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    static int secondsPassedw=0;
    Timer  myTimerw = new Timer();
    TimerTask taskw = new TimerTask() {
        @Override
        public void run() {
            if(secondsPassedw<=62)
                secondsPassedw++;
        }
    };
    public void startW(){
        myTimerw.scheduleAtFixedRate(taskw,0,100);
    }
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if(secondsPassedw<=8)
                secondsPassedw++;
        }
    };

    public void stopW() throws InterruptedException {
        myTimerw.cancel();
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(r, 1L, 1L, TimeUnit.SECONDS);
        Thread.sleep(10L);
        scheduledFuture.cancel(false);
    }

    public void pause(){
        myTimerw.cancel();
    }

    public static int getSecondsPassedW() {
        return secondsPassedw;
    }

    public static void setSecondsPassedW(int secondsPassedw) {
        waktuTimingWizard.secondsPassedw = secondsPassedw;
    }
}
