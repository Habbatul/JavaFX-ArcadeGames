package com.example.seadanya;
import java.util.concurrent.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerSprint extends timer{
    static int seconds=0;
    static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    Timer myTimer = new Timer();
    TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(seconds!=0)
                    seconds--;}
        };

        public void start(){
            myTimer.schedule(task,100,100);
        }
        public void stop(){myTimer.cancel();}

    public static void setSeconds(int seconds) {
        TimerSprint.seconds = seconds;
    }

    public static int getSeconds() {
        return seconds;
    }


}

