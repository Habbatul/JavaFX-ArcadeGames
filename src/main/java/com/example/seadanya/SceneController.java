package com.example.seadanya;

import java.net.URL;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class SceneController {
    @FXML
    public AnchorPane rootPane;
    public AnchorPane rootPaneMain;

    DBconnection test = new DBconnection(); //uji coba sql
    @FXML
    public Button insertButton;
    @FXML
    public TextField nameField;

    @FXML
    private TableView<datplay> TableView;

    @FXML
    private TableColumn<datplay, String> nameColumn;

    @FXML
    private TableColumn<datplay, Integer> scoresColumn;

    @FXML
    public  Button button2;

    private Stage stage;
    private Scene scene;
    private Parent root;
    static String namaplayer;
    static int w=0;static int DemonDiam=2;
    int x=0; static int cekernilaistatic;static int durasisprint=0;static int scorevalue=0;static int penghenti=0;static int pembatz=0;static int robotrusak=0;
    boolean gameOver = false;static int level=10;static int jumlah=4;static int trigergerak=0;static int batasjurus=3;
    static int waktuspawn=0;static int arah=0;static int durasisprinta=0;static int klikgameover=0;static int wizardposisi=1;
    static int WizardBertemu=1;static int Wizardberhenti=2; static double posisiWizardDemonY=0;static double posisiWizardDemonX=0; static int triggerchatwizard=1;
    static boolean wizardtriggerTombol=false; static boolean kondisiMusikHard=true; static int ceksoundDash =0;
    static Sound musik = new Sound();
    static Sound SE = new Sound();

    static int kepencet=0;static int jumlah_kepencet=0;
//musik=============================================================================================================================

    public void playMusic(int i){
        musik.setFile(i);
        musik.play();
        musik.loop();
    }
    public void playSE(int i){
        SE.setFile(i);
        SE.play();
    }

    public static void stopMusic(){
        musik.stop();
    }
//end musik====================================================================================================================

    //sound effect
    public void SEonPress(){
        playSE(7);
    }
    public void SEonReleased(){
        playSE(8);
    }
    //==============
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainProgram.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        if(!MainProgram.isCekLaguMainMenu()){
            stopMusic();
            MainProgram.playMusic(0);
            MainProgram.setCekLaguMainMenu(true);
        }
    }
    public void exit(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);

    }
    /*
    Cara 1 :
    public void nama_fungsi_pindah_Scene (ActionEvent event) throws IOException {
        AnchorPane nama_AnchorPane_fungsi_ini = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("nama.fxml"))));
        nama_rootPane.getChildren().setAll(nama_AnchorPane_fungsi_ini);


    Cara 2 :
     public nama_fungsi_pindah_Scene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("nama.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     */

    public void switchToScenediff(ActionEvent event) throws IOException {
        AnchorPane paneOption = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("Scene2.fxml"))));
        rootPaneMain.getChildren().setAll(paneOption);
    }

    public void sceneinputnama(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("InputPlayer.fxml"))));
        rootPane.getChildren().setAll(pane);
    }
    public void sceneinputnama2(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("InputPlayerBuwung.fxml"))));
        rootPane.getChildren().setAll(pane);
    }
    //uji coba untuk tombol hidden
    public void modifytable() {
        if(!TableView.isVisible()){
            TableView.setVisible(true);
        }
    }


    //insert database1 //operasi database---------------------------------------------------------------------
    public void insertButton(ActionEvent event) throws IOException {
        if(!Objects.equals(namaplayer, nameField.getText())){
            namaplayer = nameField.getText();
            String query = "insert into skor (nama, score) value ('"+nameField.getText()+"', 0)";
            executeQuery(query);}else{}
        switchToScene2(event);
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gaming","root","");
            System.out.println("koneksi");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //insert database2 //operasi database---------------------------------------------------------------------
    public void insertButton2(ActionEvent event) throws IOException {
        if(!Objects.equals(namaplayer, nameField.getText())){
            namaplayer = nameField.getText();
            String query = "insert into hard (nama, score) value ('"+nameField.getText()+"', 0)";
            executeQuery(query);}else{}
        switchHard(event);
    }
    //insert database2 //operasi database---------------------------------------------------------------------
    //operasi database show score select disini---------------------------------------siniii
    public ObservableList<datplay> getData1List(){
        ObservableList<datplay> dataList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM skor ORDER BY skor.score DESC LIMIT 10";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            datplay dat;
            while(rs.next()) {
                dat = new datplay(rs.getString("nama"), rs.getInt("score"));
                dataList.add(dat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public void showData1() throws IOException {
        ObservableList<datplay> list = getData1List();
        nameColumn.setCellValueFactory(new PropertyValueFactory<datplay,String>("nama"));
        scoresColumn.setCellValueFactory(new PropertyValueFactory<datplay, Integer>("skor"));
        TableView.setItems(list);
        modifytable();
    }
    //data 2 hard
    public ObservableList<datplay> getData2List(){
        ObservableList<datplay> dataList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM hard ORDER BY hard.score DESC LIMIT 10";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            datplay dat;
            while(rs.next()) {
                dat = new datplay(rs.getString("nama"), rs.getInt("score"));
                dataList.add(dat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
    public void showData2() throws IOException {
        ObservableList<datplay> list = getData2List();
        nameColumn.setCellValueFactory(new PropertyValueFactory<datplay,String>("nama"));
        scoresColumn.setCellValueFactory(new PropertyValueFactory<datplay, Integer>("skor"));
        TableView.setItems(list);
        modifytable();
    }

    //show data ketiga easy
    //=============================================================================================================
    public void skor1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DataPlayer1.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //pindah-pindah ke scene 1-------------------------------------------------------------------------------------
    public void pindah() throws IOException {
        if(kondisiMusikHard==false)kondisiMusikHard=true;
        if(!MainProgram.isCekLaguMainMenu()){
            stopMusic();
            MainProgram.playMusic(0);
            MainProgram.setCekLaguMainMenu(true);
        }
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainProgram.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //pindah ke level selanjutnya
    public void nextlevel() throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Lanjut Level.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void kembali(ActionEvent event) throws IOException {
        test.insert_score2(scorevalue,namaplayer);
        level=10;
        jumlah=4;
        scorevalue=0;
        if(kondisiMusikHard==false)kondisiMusikHard=true;
        if(!MainProgram.isCekLaguMainMenu()){
            stopMusic();
            MainProgram.playMusic(0);
            MainProgram.setCekLaguMainMenu(true);
        }
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainProgram.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    ///asdasdasd900-=524=-3402-=340-=23402=3-04=-2304=-2304-=203-=4023-=40=-23400


    public void switchToScene2(ActionEvent event) throws IOException {
        MainProgram.stopMusic();
        MainProgram.setCekLaguMainMenu(false);
        playMusic(1);
        //wizard waktu
        final DurasiJurusDemon[] dmnfire = {new DurasiJurusDemon()};
        final waktuTimingWizard[] waktuw = {new waktuTimingWizard()};
        waktuw[0].startW();
        waktuw[0].setSecondsPassedW(0);
        //==========================
        test.koneksi();
        x=1;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        final TimerSimple[] waktu = {new TimerSimple()};
        waktu[0].setSecondsPassed(25);
        final boolean[] mulaidurasisprint = {true};
        Image image = new Image(String.valueOf(this.getClass().getResource("duniax.gif")) ,720,720,false,false);
        ImageView mv= new ImageView(image);

        Group root = new Group();
        root.getChildren().addAll(mv);
        scene = new Scene( root );

        Canvas p= new Canvas(720, 720);
        Canvas canvas = new Canvas( 720, 720 );

        root.getChildren().addAll(canvas,p);



        //ujicoba tombollll


        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext b = p.getGraphicsContext2D();
        Font huruf = Font.font( "Impact", FontWeight.BOLD, 34 );
        b.setTextAlign(TextAlignment.CENTER);
        b.setFill( Color.TOMATO );
        b.setFont(huruf);

        GraphicsContext C = canvas.getGraphicsContext2D();


        Font theFont = Font.font( "Comic Sans MS", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill( Color.ORANGE );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        ///148x135
        Sprite demon = new Sprite();
        demon.setImage(String.valueOf(this.getClass().getResource("demonmain.png")),80,80);
        demon.setPosition(0, 0);

        batasjurus=3;

        // wizard ========================================================
        Sprite wizard = new Sprite();
        wizard.setImage(String.valueOf(this.getClass().getResource("wizardkanan.gif")), 120, 120);
        System.out.println(String.valueOf(this.getClass().getResource("wizardkanan.gif")));
        wizard.setPosition(220,450);

        Sprite WizardChat = new Sprite();
        WizardChat.setPosition(15, 20);
        Sprite Backdarken = new Sprite();
        Backdarken.setPosition(0,0);

        DurasiBertemuWizard DBW = new DurasiBertemuWizard();
        //===============================================================
//===============================Ledakan Durasi==========================================
        final DurasiLedakan[] DurasiLedakanCrab = {new DurasiLedakan()};
        DurasiLedakanCrab[0].setSecondsPassedh(0);

//=======================================================================================
        Sprite robotKepiting =new Sprite();
        robotKepiting.setImage(String.valueOf(this.getClass().getResource("robot-kepiting1.gif")),150,100);
        robotKepiting.setPosition(300,500);

        Sprite FRENZY = new Sprite();
        FRENZY.setPosition(0,0);
        final Sprite[] moneybag = {new Sprite()};
        final Sprite[] finalMoneybag = {moneybag[0]};


        ArrayList<Sprite> moneybagList = new ArrayList<Sprite>();
        for (int i = 0; i < 13; i++)
        {
            moneybag[0] = new Sprite();
            moneybag[0].setImage(String.valueOf(this.getClass().getResource("crabby.gif")),95,95);
            double px = (720-50) * Math.random() + 50;
            double py = (720-50) * Math.random() + 50;
            moneybag[0].setPosition(px,py);
            moneybag[0].setVelocity(0,0);
            moneybagList.add(moneybag[0]);

        }

        //objek animasi kalo meledak
        Sprite explode = new Sprite();
        Sprite blueex = new Sprite();

        explode.setPosition(1000,1000);

        Sprite Skill= new Sprite();
        Skill.setImage("file:",262,162);
        Skill.setPosition(1000,1000);
        //timer
        TimerSprint sprint = new TimerSprint();//timersprint
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        IntValue score = new IntValue(0);
        TimerDash go = new TimerDash();
        final boolean[] jojo = {true};
        //kondisi kalo diklik objeknyaa-----------------------------------------------------------
        p.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(x==1){
                    x=0;
                    waktu[0].start();
                }
                Rectangle2D rect = robotKepiting.getBoundary();
                if(mouseEvent.getX() > rect.getMinX()
                        && mouseEvent.getX() < rect.getMaxX()
                        && mouseEvent.getY() > rect.getMinY()
                        && mouseEvent.getY() < rect.getMaxY()) {
                    if(DurasiLedakan.getSecondsPassedh()==0){
                        DurasiLedakanCrab[0] = new DurasiLedakan();
                        DurasiLedakanCrab[0].starth();
                    }
                    playSE(9);
                    //explode animation==================================
                    explode.setImage(String.valueOf(this.getClass().getResource("spark.gif")),162,162);
                    jojo[0] =false;
                    explode.setPosition(robotKepiting.getPositionX()+30,robotKepiting.getPositionY()-10);
                    //--================================================================
                    w+=1;
                    if(w>3 && w<=7){
                        if(robotrusak!=1){
                        robotKepiting.setImage(String.valueOf(this.getClass().getResource("robot-dort1.gif")),150,100);}
                        robotrusak=1;
                    }
                    if(w>=8){
                        robotKepiting.setImage("file:C:",150,150);
                        go.setXseconds(3);
                        go.xstart();
                        score.value+=30;
                        w=0;
                        robotrusak=0;
                    }
                }

            }
        });

        //scene wizard chat
            scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(wizardtriggerTombol){
                        triggerchatwizard++;
                        if(triggerchatwizard==1)
                            WizardChat.setImage(String.valueOf(this.getClass().getResource("WizardChat1.png")), 690, 180);
                        if(triggerchatwizard==2)
                            WizardChat.setImage(String.valueOf(this.getClass().getResource("WizardChat2.png")), 690, 180);
                        if(triggerchatwizard==3){
                            WizardChat.setImage(String.valueOf(this.getClass().getResource("WizardChat3.png")), 690, 180);
                            triggerchatwizard=0;}
                    }
                }
            });

        //=============================================
        canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Rectangle2D rect = robotKepiting.getBoundary();
                if(mouseEvent.getX() > rect.getMinX()
                        && mouseEvent.getX() < rect.getMaxX()
                        && mouseEvent.getY() > rect.getMinY()
                        && mouseEvent.getY() < rect.getMaxY()) {
                    System.out.println("makan");
                }

            }

        }); // perbaiki
        //=================================================
        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
                        if(!wizardtriggerTombol) {
                            if ((e.getCode() == e.getCode().L)) {
                                if (durasisprint >= -1 && durasisprint <= 6) {
                                    durasisprint++;
                                }
                            }
                        }
                      }
                    });


        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e) {

                            String code = e.getCode().toString();
                            input.remove(code);

                        if(!wizardtriggerTombol) {
                            if ((e.getCode() == e.getCode().L)) {
                                ceksoundDash=0;
                            }
                        }
                    }
                });

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                scene.addEventFilter(KeyEvent.ANY, keyEvent -> {
                    if(x==1){
                        x=0;
                        waktu[0].start();
                    }
                });                // calculate time since last update.
//                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;


                double elapsedTime = 1.0/70; //buat agar 70 fps di device manapun
                lastNanoTime.value = currentNanoTime;
                gc.clearRect(0, 0, 720, 720);
                b.clearRect(0, 0, 720, 720);
                demon.setVelocity(0,0);
                //====durasi jurus demon
                if(!wizardtriggerTombol) {
                    if ((input.remove("K")) && batasjurus > 0 && dmnfire[0].getSecondsPassedD() == 0) {
                        playSE(10);
                        if(arah!=9){
                            demon.setImage("file:", 80, 80);}
                        arah=9;
                        Wizardberhenti=1;
                        Backdarken.setImage(String.valueOf(this.getClass().getResource("backgroundDarken.png")), 750, 750);
                        DemonDiam=1;
                        WizardChat.setImage(String.valueOf(this.getClass().getResource("DemonChat.png")), 690, 180);
                        Skill.setImage(String.valueOf(this.getClass().getResource("fire-flame.gif")), 443,250);
                        Skill.setPosition(demon.getPositionX()-160, demon.getPositionY()-100); //bagian sini coy
                        dmnfire[0].setSecondsPassedD(0);
                        dmnfire[0] = new DurasiJurusDemon();
                        dmnfire[0].startD();
                        if (batasjurus != 0) {
                            batasjurus -= 1;
                        }
                    }
                }
                if(DurasiJurusDemon.getSecondsPassedD()>2)  {
                    try {
                        dmnfire[0].setSecondsPassedD(0);
                        Skill.setImage("file:", 0, 0);
                        Skill.setPosition(900,900);
                        demon.setImage(String.valueOf(this.getClass().getResource("demonmain.png")), 80, 80);
                        if (arah == 1) {
                            demon.setImage(String.valueOf(this.getClass().getResource("demonsampingkiri.png")), 80, 80);
                        }
                        if (arah == 2) {
                            demon.setImage(String.valueOf(this.getClass().getResource("demonsampinkanan (2).png")), 80, 80);
                        }
                        if (arah == 3) {
                            demon.setImage(String.valueOf(this.getClass().getResource("demonatasmn (2).png")), 80, 80);
                        }
                        if (arah == 4) {
                            demon.setImage(String.valueOf(this.getClass().getResource("demonmain.png")), 80, 80);
                        }arah=10;
                        for(int i=0; i< dmnfire.length;i++)
                            dmnfire[i].stopD();
                        Wizardberhenti=2;
                        DemonDiam=2;
                        WizardChat.setImage("file:", 690, 180);
                        WizardChat.setPosition(15, 0);
                        Backdarken.setImage("file:", 750, 750);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                //System.out.println(dmnfire[0].getSecondsPassedD());

                //======================Ledakan durasi Crab robot kepiting======================

                if(DurasiLedakanCrab[0].getSecondsPassedh()>2){
                    try {
                        DurasiLedakanCrab[0].stoph();
                        explode.setImage("file:",0,0);
                        for(int i=0;i<=DurasiLedakanCrab.length;i++)
                            DurasiLedakanCrab[0].setSecondsPassedh(0);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                //System.out.println(DurasiLedakan.getSecondsPassedh());

                //percobaan spawn objek===================================================
                if(waktu[0].getSecondsPassed()!=25 && Wizardberhenti==2)waktuspawn++;
                AtomicReference<Double> px = new AtomicReference<>((720 - 50) * Math.random() + 50);
                if(waktuspawn==150){
                    px.set((720 - 50) * Math.random() + 50);
                    finalMoneybag[0] = new Sprite();
                    finalMoneybag[0].setImage(String.valueOf(this.getClass().getResource("crabby.gif")), 80, 80);
                    finalMoneybag[0].setPositionY(850);
                    moneybagList.add(finalMoneybag[0]);
                    finalMoneybag[0].setPosition(px.get(), px.get());
                    waktuspawn=0;
                }
                //batas================================================================

                //========spark kepiting===============



                //================================

                //teknologi sprint pembatas
                if (input.contains("L")){
                    mulaidurasisprint[0]=false;

                }else if(!(input.contains("L"))){
                    durasisprint=0;
                    mulaidurasisprint[0]=true;
                }
                if(mulaidurasisprint[0]){
                    if(durasisprint>0){
                        durasisprint=0;
                       }
                }

                if ((input.contains("L"))){
                    if(ceksoundDash!=1 && durasisprint>1){
                        playSE(5); //dsah dmn
                        ceksoundDash=1;
                    }
                }

//System.out.println(batasjurus);
                //jangan lupa pakaii

                robotKepiting.setVelocity(0,0);
                FRENZY.setPosition(143,300);
                //kondisi penanganan durasi sprint


//durasi ledakan, berjalannya ledakan waktu kita klik robot
                if (!jojo[0]) {
                    cekernilaistatic++;
                    if (cekernilaistatic >= 130) {
                        blueex.setImage("file:", 150, 150);
                        cekernilaistatic = 0;
                        jojo[0] = true;
                    }
                }
                //batsssssssssss
                if(DemonDiam==2) {
                    if ((durasisprint > 2 && durasisprint <= 6) && input.contains("L")) {
                        if (!wizardtriggerTombol) {
                            if (input.contains("LEFT")) {
                                if (arah != 5) {
                                    demon.setImage(String.valueOf(this.getClass().getResource("demon-firekiri.gif")), 80, 80);
                                }
                                arah = 5;
                                demon.addVelocity(-600, 0);
                            } else if (input.contains("RIGHT")) {
                                if (arah != 6) {
                                    demon.setImage(String.valueOf(this.getClass().getResource("demon-firekanan.gif")), 80, 80);
                                }
                                arah = 6;
                                demon.addVelocity(600, 0);
                            } else if (input.contains("UP")) {
                                if (arah != 7) {
                                    demon.setImage(String.valueOf(this.getClass().getResource("demon-fireatas.gif")), 80, 80);
                                }
                                arah = 7;
                                demon.addVelocity(0, -600);
                            } else if (input.contains("DOWN")) {
                                if (arah != 8) {
                                    demon.setImage(String.valueOf(this.getClass().getResource("demon-firebawah.gif")), 80, 80);
                                }
                                arah = 8;
                                demon.addVelocity(0, 600);
                            }
                        }

                    } else {
                        //gerakan dengan animasi
                        if (input.contains("LEFT")) {
                            if (arah != 1) {
                                demon.setImage(String.valueOf(this.getClass().getResource("demon-kiri.gif")), 80, 80);
                            }
                            arah = 1;
                            demon.addVelocity(-150, 0);
                        } else if (input.contains("RIGHT")) {
                            if (arah != 2) {
                                demon.setImage(String.valueOf(this.getClass().getResource("demon-kanan.gif")), 80, 80);
                            }
                            arah = 2;
                            demon.addVelocity(150, 0);
                        } else if (input.contains("UP")) {
                            if (arah != 3) {
                                demon.setImage(String.valueOf(this.getClass().getResource("demon-atas.gif")), 80, 80);
                            }
                            arah = 3;
                            demon.addVelocity(0, -150);
                        } else if (input.contains("DOWN")) {
                            if (arah != 4) {
                                demon.setImage(String.valueOf(this.getClass().getResource("demon.gif")), 80, 80);
                            }
                            arah = 4;
                            demon.addVelocity(0, 150);
                        } else {
                            if (arah == 1) {
                                demon.setImage(String.valueOf(this.getClass().getResource("demonsampingkiri.png")), 80, 80);
                            }
                            if (arah == 2) {
                                demon.setImage(String.valueOf(this.getClass().getResource("demonsampinkanan (2).png")), 80, 80);
                            }
                            if (arah == 3) {
                                demon.setImage(String.valueOf(this.getClass().getResource("demonatasmn (2).png")), 80, 80);
                            }
                            if (arah == 4) {
                                demon.setImage(String.valueOf(this.getClass().getResource("demonmain.png")), 80, 80);
                            }
                            arah = 10;
                        }

                    }


                    //System.out.println(demon.getPositionX()+", "+demon.getPositionY());


                    //sdf=========================================================================
                    //==========================================================
                    demon.update(elapsedTime);
                }




                double direction = (int) (Math.random()*4);
                // collision detection
                Iterator<Sprite> moneybagIter = moneybagList.iterator();
                while ( moneybagIter.hasNext() )
                {




                    Sprite moneybag = moneybagIter.next();
                    if ( demon.intersects(moneybag) && input.contains("L") && (durasisprint>2 && durasisprint<=6))
                    {
                        playSE(6);
                        score.value++;
                        moneybagIter.remove();
                        blueex.setImage(String.valueOf(this.getClass().getResource("explosion.gif")),100,100);
                        jojo[0] =false;
                        blueex.setPosition(moneybag.getPositionX(),moneybag.getPositionY());

                    }

                    trigergerak++;
                    double xx0 = (int) (Math.random() * 4);
                    if(trigergerak<=200){
                        if (xx0 == 1) {
                            for(int i=0;i<=90;i++){
                                moneybag.addVelocity(0, 0.01);}
                        }
                        if (xx0 == 3) {
                            for(int i=0;i<=90;i++){
                                moneybag.addVelocity(0.01, 0);}
                        }
                        if (xx0 == 0) {
                            for(int i=0;i<=90;i++){
                                moneybag.addVelocity(-0.01, 0);}
                        }
                        if (xx0 == 2) {
                            for(int i=0;i<=90;i++){
                                moneybag.addVelocity(0, -0.01);}
                        }
                        moneybag.update(elapsedTime);

                    }
                    else if(trigergerak>200&& trigergerak<=229) {
                        if (xx0 == 1) {
                            for(int i=0;i<=90;i++){
                            moneybag.addVelocity(0, 0.3);
                           }
                        }
                        if (xx0 == 3) {
                            for(int i=0;i<=90;i++){
                            moneybag.addVelocity(-0.3, 0);}
                        }
                        if (xx0 == 0) {
                            for(int i=0;i<=90;i++){
                            moneybag.addVelocity(0.3, 0);}
                        }
                        if (xx0 == 2) {
                            for(int i=0;i<=90;i++){
                            moneybag.addVelocity(0, -0.3);}
                        }
                        if(Wizardberhenti!=2) {moneybag.setVelocity(0,0);}
                        moneybag.update(elapsedTime);

                    }
                    else if (trigergerak>=230){
                        moneybag.setVelocity(0,0);
                        trigergerak=1;
                    }

                    //=========================================================================================================================
                        if (Skill.intersects(moneybag))
                        {
                            score.value++;
                            moneybagIter.remove();
                            playSE(6);
                            blueex.setImage(String.valueOf(this.getClass().getResource("explosion.gif")),100,100);
                            blueex.setPosition(moneybag.getPositionX(),moneybag.getPositionY());
                        }
                    //=========================================================================================================================



                    if(demon.intersects(moneybag)){
                        if (input.contains("LEFT")){
                            moneybag.addVelocity(-300, 0);
                            moneybag.update(elapsedTime);
                            moneybag.setVelocity(0,0);
                        }
                        if (input.contains("RIGHT")){
                            moneybag.addVelocity(300, 0);
                            moneybag.update(elapsedTime);
                            moneybag.setVelocity(0,0);
                        }
                        if (input.contains("UP")){
                            moneybag.addVelocity(0, -300);
                            moneybag.update(elapsedTime);
                            moneybag.setVelocity(0,0);
                        }
                        if (input.contains("DOWN")){
                            moneybag.addVelocity(0, 300);
                        moneybag.update(elapsedTime);
                            moneybag.setVelocity(0,0);
                        }


                    }

                    if(robotKepiting.intersects(moneybag)){
                        if (direction==0 && pembatz<=400)
                            moneybag.addVelocity(-10, 0);
                        if (direction==2 && pembatz<=400)
                            moneybag.addVelocity(10, 0);
                        if (direction==1 && pembatz<=400)
                            moneybag.addVelocity(0, -10);
                        if (direction==3 && pembatz<=400)
                            moneybag.addVelocity(0, 10);
                        moneybag.update(elapsedTime);
                    }


                }
                if(demon.intersects(robotKepiting)){
                    if (input.contains("UP"))
                        robotKepiting.addVelocity(0, -500);
                    else if (input.contains("DOWN"))
                        robotKepiting.addVelocity(0, 500);}
                gc.clearRect(0, 0, 720,720);


                        if(direction==0 && pembatz<=400)
                            robotKepiting.addVelocity(100, 0);
                        if(direction==2 && pembatz<=400)
                            robotKepiting.addVelocity(-100, 0);
                        if(direction==1 && pembatz<=400)
                            robotKepiting.addVelocity(0, 100);
                        if(direction==3 && pembatz<=400){
                            robotKepiting.addVelocity(0, -200);
                        pembatz+=1;}
                        else if (direction==3 && pembatz>=400){
                            robotKepiting.addVelocity(0, 200);
                            pembatz+=1;
                            if(pembatz>=500)
                                pembatz=0;
                        }

                if(robotKepiting.getPositionX()<0){
                    for(int i=0;i<=5;i++){
                        robotKepiting.addVelocity(200, 0);}
                }
                if(robotKepiting.getPositionY()<2){
                    for(int i=0;i<=5;i++){
                        robotKepiting.addVelocity(0, 200);}
                }
                if(robotKepiting.getPositionY()<0){
                    robotKepiting.addVelocity(0, 200);
                }

                //wizard posisi
                wizard.setVelocity(0,0);
                if(Wizardberhenti!=2) {robotKepiting.setVelocity(0,0);}
                robotKepiting.update(elapsedTime);
                if(waktuw[0].getSecondsPassedW()>=62){
                    waktuw[0].setSecondsPassedW(0);}
                //======================== wizard jalan ===================
                //direction kanan kiri wizard
                if(Wizardberhenti==2) {
                    //gerakan wizard kanan kiri looping
                    if (waktuw[0].getSecondsPassedW() <= 30 && wizard.getPositionX() < 330) {
                        wizard.addVelocity(50, 0);
                        if (wizardposisi != 1) {
                            wizard.setImage(String.valueOf(this.getClass().getResource("wizardkanan.gif")), 120, 120);
                        }
                        wizardposisi = 1;
                    }
                    if (waktuw[0].getSecondsPassedW() > 30 && wizard.getPositionX() > 200) {
                        wizard.addVelocity(-50, 0);

                        if (wizardposisi != 2) {
                            wizard.setImage(String.valueOf(this.getClass().getResource("wizardkiri.gif")), 120, 120);
                        }
                        wizardposisi = 2;
                    }
                    if (wizard.getPositionX() > 330) {
                        waktuTimingWizard.setSecondsPassedW(30);
                        wizard.setPositionX(329);
                    }
                    if (wizard.getPositionX() < 200) {
                        waktuTimingWizard.setSecondsPassedW(0);
                        wizard.setPositionX(201);
                    }
                }
                // Monitoring Penting
                //System.out.println(wizard.getPositionX() + " & " + wizard.getPositionY() + " & time : "+ waktuw[0].getSecondsPassedW() + " WB: " + WizardBertemu );


                //System.out.println(waktuw.getSecondsPassedW() + " & " + wizardposisi);

                //System.out.println(wizardposisi);
                //==================== wizard Intersact ======================================


                posisiWizardDemonY=(wizard.getPositionY()-demon.getPositionY());
                posisiWizardDemonX=(wizard.getPositionX()-demon.getPositionX());
                if(demon.intersects(wizard) && WizardBertemu<=1 && (posisiWizardDemonY<=-14 && posisiWizardDemonY>=-116)&& (posisiWizardDemonX>-98&& posisiWizardDemonX<60)){

                    Wizardberhenti=1;
                    WizardBertemu=2;
                    Backdarken.setImage(String.valueOf(this.getClass().getResource("backgroundDarken.png")), 750, 750);
                    WizardChat.setImage(String.valueOf(this.getClass().getResource("WizardChat1.png")), 690, 180);
                    WizardChat.setPosition(15, 20);
                    wizardtriggerTombol=true;

                    // wizard click continue
                    waktu[0].pause();
                    wizard.setVelocity(0,0);
                    waktuw[0].pause();
                }
                else if (WizardBertemu==2 && !(demon.intersects(wizard) && (posisiWizardDemonY<=-14 && posisiWizardDemonY>=-116)&& (posisiWizardDemonX>-98 && posisiWizardDemonX<60))){
                    Backdarken.setImage("file:C:", 750, 750);
                    WizardChat.setImage("file:C:", 690, 180);
                    WizardBertemu=3;

                }
                else if(WizardBertemu==3){
                    try {
                        for (int i=0;i<waktu.length;i++)
                            waktu[i].stop();
                        for (int i=0;i<waktuw.length;i++)
                            waktuw[i].stopW();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waktuw[0] = new waktuTimingWizard();
                    waktuw[0].startW();
                    waktu[0] =new TimerSimple();
                    waktu[0].start();
                    WizardBertemu=1;
                    Wizardberhenti=2;
                    triggerchatwizard=1;
                    wizardtriggerTombol=false;
                }

                //System.out.println(triggerchatwizard + " & "+ wizardtriggerTombol);
                //=============================================================================
                wizard.update(elapsedTime);

                // render render render
                if(go.getXseconds()!=0){
                    FRENZY.setImage(String.valueOf(this.getClass().getResource("FRENZY1.png")) ,400,100);
                    FRENZY.setPosition(142,300);
                }

                gc.clearRect(0, 0, 720,720);

                for (Sprite moneybag : moneybagList ) {
                    moneybag.render(gc);
                    finalMoneybag[0].render(gc);
                }
                blueex.render(gc);
                wizard.render(gc);
                demon.render(gc);
                robotKepiting.render(gc);
                //penempatan depan belakang wizard demon robot
                if(demon.getPositionY()>=503 || robotKepiting.getPositionY()>=520 ||robotKepiting.getPositionY()<=528) {
                    if (robotKepiting.getPositionY() >= 520) {
                        wizard.render(gc);
                        robotKepiting.render(gc);
                        demon.render(gc);
                    } else if (robotKepiting.getPositionY() <= 528) {
                        robotKepiting.render(gc);
                        wizard.render(gc);
                        demon.render(gc);
                    }
                    explode.render(gc);
                    if (demon.getPositionY() >= 503) {
                        wizard.render(gc);
                        demon.render(gc);
                    } else if (demon.getPositionY() <= 490) {
                        demon.render(gc);
                        wizard.render(gc);
                    }
                }

                Skill.render(gc);
                FRENZY.render(gc);


                String pointsText = "Point: " + (score.value)+"\nWaktu : "+ waktu[0].getSecondsPassed()+"\nDelay : "+durasisprint;
                gc.fillText( pointsText, 375, 36 );
                gc.strokeText( pointsText, 375, 36 );


                String teksx = "Ulti: " + (batasjurus);
                C.fillText( teksx, 50, 36 );
                C.strokeText( teksx, 50, 36 );


                if(waktu[0].getSecondsPassed()==0){gameOver=true;}
                if(score.value>20&&score.value<=35 && waktu[0].getSecondsPassed()>=2) {
                    FRENZY.setImage(String.valueOf(this.getClass().getResource("FRENZY.png")) ,400,100);
                    FRENZY.setPosition(143,300);
                }else{FRENZY.setImage("file:C:\\" ,400,100);}
                Backdarken.render(gc);
                if(DemonDiam==1){
                    Backdarken.render(gc);
                    demon.render(gc);
                    Skill.render(gc);
                    WizardChat.render(gc);
                }
                WizardChat.render(gc);
                if(gameOver) {
                    String waktuhabis="waktu telah habis\nGame over\nPoint kamu ("+namaplayer+") : " +score.value+"\n";
                    b.fillText(waktuhabis, 350, 300);
                    b.strokeText(waktuhabis, 350, 300);
                    stop();
                    try {
                        for(int i=0; i< dmnfire.length;i++) {
                        dmnfire[i].stopD();
                        dmnfire[i].setSecondsPassedD(0);}
                        w=0;
                        Wizardberhenti=2;
                        DemonDiam=2;
                        waktu[0].stop();
                        waktuw[0].stopW();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            try {
                                test.insert_score(score.value,namaplayer);
                                pindah();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }.start();
        stage.setScene(scene);
        stage.show();
    }






    //scene hard===========================================================================








    public void switchHard(ActionEvent event) throws IOException {
        if(kondisiMusikHard){
            kondisiMusikHard=false;
            MainProgram.stopMusic();
            MainProgram.setCekLaguMainMenu(false);
            playMusic(2);
        }else if (!kondisiMusikHard){}//System.out.println(kondisiMusikHard);
        x=1;int makanan = 4;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        klikgameover=0;
        jumlah_kepencet=0;
        if(level ==10){kepencet=3;}
        if(level==15){kepencet=3;}
        if(level==20){kepencet=1;}
        waktuspawnburung sspawnburung = new waktuspawnburung();
        sspawnburung.startburung();
        waktuspawnburung.setSecondsPassedb(0);

        Image image = new Image(String.valueOf(this.getClass().getResource("burungback.gif")),720,720,false,false);
        ImageView mv= new ImageView(image);

        Group root = new Group();
        root.getChildren().addAll(mv);
        scene = new Scene( root );


        Canvas canvas = new Canvas( 720, 720 );
        root.getChildren().add( canvas );

        ArrayList<String> input = new ArrayList<String>();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext b = canvas.getGraphicsContext2D();

        Font huruf = Font.font( "fantasy", FontWeight.BOLD, 24 );
        b.setTextAlign(TextAlignment.CENTER);
        b.setFont( huruf );


        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.ORANGE );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        TimerDash go = new TimerDash();
        ///148x135
        if(level ==10){
            jumlah=5;
            makanan=7;
        }
        else if(level==15){
            jumlah=10;
            makanan=15;
        }
        else if(level ==20){
            jumlah=10;
            makanan=21;
        }

        Sprite FRENZY = new Sprite();
        FRENZY.setPosition(0,0);
        ArrayList<Sprite> robotList = new ArrayList<Sprite>();


        //nagaaa
        Sprite naga = new Sprite();
        ArrayList<Sprite> nagaList = new ArrayList<Sprite>();
        naga.setImage(String.valueOf(this.getClass().getResource("naga.gif")),150,150);

        //objek animasi kalo meledak
        Sprite explode = new Sprite();
        explode.setImage(String.valueOf(this.getClass().getResource("gambar.gif")),150,150);
        explode.setPosition(1000,1000);
        //timer
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        IntValue score = new IntValue(0);

        final boolean[] jojo = {true};
        //kondisi kalo diklik objeknyaa-----------------------------------------------------------

        int finalMakanan = makanan;
        //cek untuk lanjut level
        final int [] directn = {1};
        final double[] waktuSpawnx = {1};
        final int[] i = {0};
        final int[][] cekernilaistaticx = {{0}};

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {

                // calculate time since last update.
//                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;

                //jadikan elapsedTime nya agar 60 fps
                double elapsedTime = 1.0/50;
                lastNanoTime.value = currentNanoTime;

//                double fps = 1 / elapsedTime;
//                System.out.println("FPS: " + fps);

                waktuSpawnx[0] += 1;
                // game logic
                gc.clearRect(0, 0, 720, 720);
                //jangan lupa pakaii
               // System.out.println(cekernilaistaticx[0][0]);
                if (!jojo[0] && cekernilaistaticx[0][0]>0){
                    cekernilaistaticx[0][0]++;
                    if(cekernilaistaticx[0][0]>=25){
                        explode.setImage("file:", 150, 150);
                        jojo[0]=true;
                        cekernilaistaticx[0][0]=0;
                    }
                }
//spawn buwung sementara===============================



                if (waktuSpawnx[0] % 100 ==0 ){
                        Sprite robotKepiting = new Sprite();
                        robotKepiting.setImage(String.valueOf(this.getClass().getResource("burung.gif")), 150, 150);
                        double py = (600) * Math.random();
                        robotKepiting.setPositionX(750);
                        robotKepiting.setPositionY(py);
                        robotList.add(robotKepiting);
                        jumlah_kepencet+=1;
                }
//=============naggaaaaaaaaaaaaaaaaaa==========================================
//System.out.println(waktuSpawnx[0]);
                if (waktuSpawnx[0] % 120 == 0 ){
                        Sprite naga = new Sprite();
                        naga.setImage(String.valueOf(this.getClass().getResource("naga.gif")), 150, 150);
                        double py = (400) * Math.random();
                        naga.setPositionX(750);
                        naga.setPositionY(py);
                        nagaList.add(naga);
                    }

//System.out.println(sspawnburung.getSecondsPassedb());
                Iterator<Sprite> nagabagIter = nagaList.iterator();
                while(nagabagIter.hasNext()) {
                      Sprite naga=nagabagIter.next();
                    canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Rectangle2D rect = naga.getBoundary();
                            if(mouseEvent.getX() > rect.getMinX()
                                    && mouseEvent.getX() < rect.getMaxX()
                                    && mouseEvent.getY() > rect.getMinY()
                                    && mouseEvent.getY() < rect.getMaxY()) {
                                playSE(4);
                                jojo[0] =false;
                                cekernilaistaticx[0][0] =1;
                                //explode animation==================================
                                explode.setImage(String.valueOf(this.getClass().getResource("explosion.gif")), 150, 150);
                                explode.setPosition(mouseEvent.getX()-100, mouseEvent.getY()-100);
                                //--================================================================
                                naga.setImage("file:",0,0);
                                scorevalue-=2;
                                kepencet-=1;
                            }

                        }

                    });
                            naga.addVelocity(-250,0);
                            //direction dibuat untuk gerakan atas bawah pada naga
                             directn[0]++;
                            //System.out.println(directn);
                            if (directn[0] >=0 && directn[0]<=1000){
                                naga.addVelocity(0,100);}
                            if (directn[0] >1000 && directn[0] <=2000){
                                naga.addVelocity(0,-100);}
                            if (!((directn[0]>=0 && directn[0] <=1000) || (directn[0] >1000 && directn[0] <=2000))){directn[0]=0;}
                           // System.out.println(direct);
                            naga.update(elapsedTime);
                            naga.setVelocity(0,0);
                }
 // =========================================nagaaaaaa=====================================================

                Iterator<Sprite> moneybagIter = robotList.iterator();
                while(moneybagIter.hasNext()) {
                    Sprite robotKepiting = moneybagIter.next();
                    /* kondisi bila diklik object */
                    canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Rectangle2D rect = robotKepiting.getBoundary();
                            if(mouseEvent.getX() > rect.getMinX()
                                    && mouseEvent.getX() < rect.getMaxX()
                                    && mouseEvent.getY() > rect.getMinY()
                                    && mouseEvent.getY() < rect.getMaxY()) {
                                playSE(3);
                                jojo[0] =false;
                                cekernilaistaticx[0][0] =1;
                                //explode animation==================================
                                explode.setImage(String.valueOf(this.getClass().getResource("explosion.gif")), 150, 150);
                                explode.setPosition(mouseEvent.getX()-100, mouseEvent.getY()-100);
                                //--================================================================
                                robotKepiting.setImage("file:",0,0);
                                scorevalue+=1;
                            }
                        }
                    });
                    //ketika mengeklik tidak kena
                    //kondisi ==============================================================================================
                    FRENZY.setPosition(143, 300);
                    double speed=3;
                    if(level ==10)
                        speed=550;
                    else if(level==15)
                        speed=850;
                    else
                        speed=1100;
                    // collision detectionsdfssdfs
                    robotKepiting.addVelocity(-speed, 0);
                    robotKepiting.update(elapsedTime);
                    robotKepiting.setVelocity(0,0);
                }
                // render

                if(go.getXseconds()!=0){
                    FRENZY.setImage(String.valueOf(this.getClass().getResource("FRENZY1.png")) ,400,100);
                    FRENZY.setPosition(142,300);
                }

                gc.clearRect(0, 0, 720,720);
                explode.render(gc);
                for (Sprite naga : nagaList)
                    naga.render(gc);
                for (Sprite robotKepiting : robotList )
                    robotKepiting.render( gc );
                FRENZY.render(gc);
                String pointsText = "Point: " + (scorevalue)+"\nNyawa : "+kepencet;
                gc.fillText( pointsText, 375, 36 );
                gc.strokeText( pointsText, 375, 36 );


                if(scorevalue>= finalMakanan){
                    System.out.println(level);
                    if(level==20){
                        klikgameover=0;
                        stop();
                        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                klikgameover++;
                                if(klikgameover>3){
                                try {
                                    test.insert_score2(scorevalue,namaplayer);
                                    level=10;
                                    jumlah=4;
                                    scorevalue=0;
                                    pindah();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }}
                            }
                        });
                    }
                    else if(level==15){
                        klikgameover=0;
                        stop();
                        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                klikgameover++;
                                if(klikgameover>3){
                                try {
                                    nextlevel();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }}
                        });
                    }
                    else if(level==10){
                        klikgameover=0;
                        stop();
                        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                klikgameover++;
                                if(klikgameover>3){
                                    try {
                                        nextlevel();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }}
                        });
                    }}
                if(scorevalue>= finalMakanan){

                    System.out.println(level);
                    if(level==20){
                        String waktuhabis="Selamat kamu sampai ke level terakhir!!\nPoint kamu ("+namaplayer+") : " +scorevalue+".\nKlik beberapa kali untuk kembali ke menu utama";
                        b.fillText(waktuhabis, 350, 300);
                        b.strokeText(waktuhabis, 350, 300);
                        try {
                            sspawnburung.stopb();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(level==15){
                        String waktuhabis="Klik beberapa kali untuk ke level berikutnya!!!";
                        b.fillText(waktuhabis, 350, 350);
                        b.strokeText(waktuhabis, 350, 350);
                        level =20;
                        try {
                            sspawnburung.stopb();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(level==10){
                        String level_lanjut="Klik beberapa kali untuk ke level berikutnya!!!";
                        b.fillText(level_lanjut,350,350);
                        b.strokeText(level_lanjut,350,350);
                        level =15;
                        try {
                            sspawnburung.stopb();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(penghenti);
                }

                if(kepencet<=0){gameOver=true;}
                if(level==10 && waktuSpawnx[0]>=802){gameOver=true;}
                if(level== 15 && waktuSpawnx[0]>=1000){gameOver=true;}
                if(level== 20 && waktuSpawnx[0]>=1000){gameOver=true;}
                if(scorevalue>10) {
                    timerFrenzy durasiFrenzy = new timerFrenzy();
                    durasiFrenzy.start();
                    if(timerFrenzy.getSecondsPassed()>0){
                    FRENZY.setImage(String.valueOf(this.getClass().getResource("FRENZY.png")) ,400,100);
                    FRENZY.setPosition(143,300);} else{FRENZY.setImage("file:C:\\" ,400,100);}
                }
                if(gameOver) {
                    String waktuhabis="Burung telah habis\nGame over\nPoint kamu ("+namaplayer+") : " +scorevalue+"\nKlik beberapa kali untuk keluar";
                    b.fillText(waktuhabis, 350, 300);
                    b.strokeText(waktuhabis, 350, 300);
                    stop();
                    klikgameover=0;
                    try {
                        sspawnburung.stopb();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            klikgameover++;
                            try {
                                System.out.println(klikgameover);
                                if(klikgameover>3) {
                                    test.insert_score2(scorevalue, namaplayer);
                                    scorevalue = 0;
                                    level = 10;
                                    jumlah = 4;
                                    pindah();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
             }
            }
        }.start();
        stage.setScene(scene);
        stage.show();
    }

    //scene Easy

}

