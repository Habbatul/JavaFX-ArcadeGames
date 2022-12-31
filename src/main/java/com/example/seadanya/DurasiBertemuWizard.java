package com.example.seadanya;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DurasiBertemuWizard extends timer{
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        static int secondsPassedw=0;
        Timer myTimerx = new Timer();
        TimerTask taskx = new TimerTask() {
            @Override
            public void run() {
                if(secondsPassedw<=4)
                    secondsPassedw++;
            }
        };
        public void startx(){
            myTimerx.scheduleAtFixedRate(taskx,1000,1000);
        }
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(secondsPassedw<=8)
                    secondsPassedw++;
            }
        };

        public void stopx() throws InterruptedException {
            myTimerx.cancel();
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(r, 1L, 1L, TimeUnit.SECONDS);
            Thread.sleep(500L);
            scheduledFuture.cancel(false);
        }

        public void pause(){
            myTimerx.cancel();
        }

        public static int getSecondsPassedWx() {
            return secondsPassedw;
        }

        public static void setSecondsPassedWx(int secondsPassedw) {
            com.example.seadanya.waktuTimingWizard.secondsPassedw = secondsPassedw;
        }
    }

