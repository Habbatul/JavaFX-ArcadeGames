package com.example.seadanya;

import java.net.URL;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Objects;

//musik=============================================================================================================================

//end musik====================================================================================================================
public class MainProgram extends Application
{
    static Sound sound=new Sound();

    public static boolean isCekLaguMainMenu() {
        return cekLaguMainMenu;
    }

    public static void setCekLaguMainMenu(boolean cekLaguMainMenu) {
        MainProgram.cekLaguMainMenu = cekLaguMainMenu;
    }

    static boolean cekLaguMainMenu=true;
    public static void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }

    public static void stopMusic(){
        sound.stop();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle( "Hq.Han Javafx Game" );
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainProgram.fxml"));
            Parent newRoot = loader.load();
            Scene scene = new Scene(newRoot);
            stage.setScene(scene);
            stage.show();
            Image ikon = new Image(String.valueOf(this.getClass().getResource("logoHq.Han.png")));
            stage.getIcons().add(ikon);
            if(cekLaguMainMenu)playMusic(0);
            stage.setOnCloseRequest(windowEvent -> {
                System.exit(0);
            });
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}

