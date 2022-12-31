package com.example.seadanya;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("0yulgar.wav");
        soundURL[1] = getClass().getResource("underworld.wav");
        soundURL[2] = getClass().getResource("buwung.wav");
        soundURL[3] = getClass().getResource("BuwungHit.wav");
        soundURL[4] = getClass().getResource("dragon.wav");
        soundURL[5] = getClass().getResource("DashDemn.wav");
        soundURL[5] = getClass().getResource("DashDemn.wav");
        soundURL[6] = getClass().getResource("CrabDead.wav");
        soundURL[7] = getClass().getResource("BtnPress.wav");
        soundURL[8] = getClass().getResource("BtnReleased.wav");
        soundURL[9] = getClass().getResource("RKhit.wav");
        soundURL[10] = getClass().getResource("SkillSE.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}

