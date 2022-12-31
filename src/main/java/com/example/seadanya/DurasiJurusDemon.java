package com.example.seadanya;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DurasiJurusDemon extends timer{
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        static int secondsPassedD=0;
        Timer myTimerD = new Timer();
        TimerTask taskD = new TimerTask() {
            @Override
            public void run() {
                if(secondsPassedD<=2)
                    secondsPassedD++;
            }
        };
        public void startD(){
            myTimerD.scheduleAtFixedRate(taskD,10,1000);
        }
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(secondsPassedD<=2)
                    secondsPassedD++;
            }
        };

        public void stopD() throws InterruptedException {
            myTimerD.cancel();
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(r, 1L, 1L, TimeUnit.SECONDS);
            Thread.sleep(1L);
            scheduledFuture.cancel(false);
        }

        public void pause(){
            myTimerD.cancel();
        }

        public static int getSecondsPassedD() {
            return secondsPassedD;
        }

        public static void setSecondsPassedD(int secondsPassedD) {
            com.example.seadanya.DurasiJurusDemon.secondsPassedD = secondsPassedD;
        }
    }

