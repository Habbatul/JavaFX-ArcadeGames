package com.example.seadanya;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TimerDash {
        static int xseconds=0;
        static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(xseconds!=0)
                    xseconds--;
                if(xseconds==0){
                    scheduler.shutdown();
                }
            }
        };

        public void xstart(){
            myTimer.schedule(task,1000,1000);
        }

    public static void setXseconds(int xseconds) {
        TimerDash.xseconds = xseconds;
    }

    public static int getXseconds() {
            return xseconds;
        }


    }


