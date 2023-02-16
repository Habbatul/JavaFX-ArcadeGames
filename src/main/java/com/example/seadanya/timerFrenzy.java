package com.example.seadanya;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class timerFrenzy extends timer{
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        static int secondsPassed=50;
        Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(secondsPassed!=0)
                    secondsPassed--;
            }
        };
        public void start(){
            myTimer.scheduleAtFixedRate(task,1000,1000);
        }
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(secondsPassed!=0)
                    secondsPassed--;
            }
        };

        public void stop() throws InterruptedException {
            myTimer.cancel();
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(r, 1L, 1L, TimeUnit.SECONDS);
            Thread.sleep(10L);
            scheduledFuture.cancel(false);
        }
        public void pause(){
            myTimer.cancel();
        }


        static public int getSecondsPassed() {
            return secondsPassed;
        }

        public static void setSecondsPassed(int secondsPassed) {
            com.example.seadanya.TimerSimple.secondsPassed = secondsPassed;
        }

}
