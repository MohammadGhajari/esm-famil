import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    public static boolean playMusic = true, buttonsPlaySound = true;
    public static Stage primarystage = new Stage();
    public static MediaPlayer buttonsSoundPlayer;
    public String[] used;
    public int playerScore = 0;
    public int SystemmScore = 0;
    public int rond = 0;
    public int ronds;
    public boolean END = true;
    public boolean usedFlag = true;
    public boolean letterflag = false;
    public int time;
    public Timer timer = new Timer();
    public boolean endTime = false;

    static {
        Media buttonsSound = new Media(Paths.get("button sound.mp3").toUri().toString());
        buttonsSoundPlayer = new MediaPlayer(buttonsSound);
    }

    public MainScene main = new MainScene();
    public SettingScene setting = new SettingScene();
    public ExitScene exit = new ExitScene();
    public AboutUsScene aboutUs = new AboutUsScene();
    public HelpScene help = new HelpScene();
    public ThemeScene theme = new ThemeScene();
    public CreateScene create = new CreateScene();
    public WarningScene warningOnFieldsCount = new WarningScene();
    public Player player = new Player();
    public Systemm systemm;

    public String letter;
    public String selectedFields = "";

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {



        primarystage.setResizable(false);
        primarystage.setTitle("اسم فامیل");
        Stage CloseStage = new Stage();
        CloseStage.setResizable(false);
        CloseStage.setTitle("خروج");
        Stage warningStage = new Stage();
        warningStage.setResizable(false);
        warningStage.setTitle("warning!");


        Media backgroundMusic = new Media(Paths.get("BrunuhVille-Spirit-of-the-Wild.mp3").toUri().toString());
        MediaPlayer MusicPlayer = new MediaPlayer(backgroundMusic);
        MusicPlayer.play();
        MusicPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                MusicPlayer.seek(Duration.ZERO);
            }
        });

        setting.getSlider().setValue(MusicPlayer.getVolume() * 100);
        setting.getSlider().valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                MusicPlayer.setVolume(setting.getSlider().getValue() / 100);
            }
        });

        main.getChangeThemeBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(theme.getScene());
            }
        });
        main.getExitBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.getButtonsRoot().setDisable(true);
                CloseStage.setScene(exit.getScene());
                CloseStage.show();
                exit.getYesCloseBTN().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.exit(0);
                    }
                });
                exit.getNoCloseBTN().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        CloseStage.close();
                        main.getButtonsRoot().setDisable(false);
                    }
                });
                CloseStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        main.getButtonsRoot().setDisable(false);
                    }
                });
            }
        });
        main.getSettingBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(setting.getScene());
            }
        });
        main.getAboutUsBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(aboutUs.getScene());
            }
        });
        main.getStartBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(create.getScene());
            }
        });
        setting.getHelpBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(help.getScene());
            }
        });
        setting.getBackSettingBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(main.getScene());
            }
        });
        setting.getMusicBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (playMusic) {
                    setting.getMusicBTN().setGraphic(new ImageView("music_mute.png"));
                    MusicPlayer.pause();
                } else {
                    MusicPlayer.play();
                    setting.getMusicBTN().setGraphic(new ImageView("music_unmute.png"));
                }
                playMusic = !playMusic;
            }
        });
        aboutUs.getBackAboutUsBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(main.getScene());
            }
        });
        aboutUs.getInstagramBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String telegramLink = "https://www.instagram.com/daenerys_dracaryss/";
                getHostServices().showDocument(telegramLink);
            }
        });
        aboutUs.getTelegramBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String instaLink = "https://t.me/Dracarys_Daenerys";
                getHostServices().showDocument(instaLink);
            }
        });
        help.getBackHelpBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(setting.getScene());
            }
        });
        theme.getBackThemeBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primarystage.setScene(main.getScene());
            }
        });
        theme.getTheme1BTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeTheme(1);
            }
        });
        theme.getTheme2BTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeTheme(2);
            }
        });
        theme.getTheme3BTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeTheme(3);
            }
        });
        create.getBackCreateBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                primarystage.setScene(main.getScene());
            }
        });
        create.getGameTypeCOMBO().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(create.getGameTypeCOMBO().getValue().equals("زماندار")){
                    create.getTime1().setDisable(false);
                    create.getTime2().setDisable(false);
                    create.getTime3().setDisable(false);
                }else {
                    create.getTime1().setDisable(true);
                    create.getTime2().setDisable(true);
                    create.getTime3().setDisable(true);
                }
            }
        });
        create.getConfirmBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isValidGame(create)) {
                    letter = (String) create.getAlphabetCOMBO().getValue();
                    player = new Player(selectedFields, create.getPlayerNameTXF().getText());
                    player.getLetter().setText(letter);
                    systemm = new Systemm(selectedFields);
                    ronds = Integer.parseInt((String) create.getRondCOMBO().getValue());
                    if(usedFlag) {
                        used = new String[ronds];
                        usedFlag = false;
                    }


                    primarystage.setScene(player.getScene());

                    player.getRondLBL().setText("دست:" + (rond + 1) + "/" + ronds);
                    player.getComputerscoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                    player.getPlayerscoreLBL().setText("امتیاز شما:" + playerScore);
                    if(create.getGameTypeCOMBO().getValue().equals("بدون زمان")) {
                        player.getFinishBTN().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {


                                    Scanner ashiaS = null;
                                    Scanner esmS = null;
                                    Scanner familS = null;
                                    Scanner heivanS = null;
                                    Scanner shahrS = null;
                                    Scanner keshvarS = null;
                                    Scanner mashinS = null;
                                    Scanner golS = null;
                                    Scanner miveS = null;
                                    Scanner pooshakS = null;
                                    Scanner ghazaS = null;


                                    try {
                                        ashiaS = new Scanner(new File("ashia.txt"));
                                        esmS = new Scanner(new File("esm.txt"));
                                        familS = new Scanner(new File("famil.txt"));
                                        heivanS = new Scanner(new File("heivan.txt"));
                                        shahrS = new Scanner(new File("shahr.txt"));
                                        keshvarS = new Scanner(new File("keshvar.txt"));
                                        mashinS = new Scanner(new File("mashin.txt"));
                                        golS = new Scanner(new File("gol.txt"));
                                        miveS = new Scanner(new File("mive.txt"));
                                        pooshakS = new Scanner(new File("pooshak.txt"));
                                        ghazaS = new Scanner(new File("ghaza.txt"));
                                    }catch (Exception e) {

                                    }

                                    if(selectedFields.contains("a")) {
                                        while (ashiaS.hasNext()) {
                                            String s = ashiaS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setAshia(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("b")) {
                                        while (esmS.hasNext()) {
                                            String s = esmS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setEsm(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("c")) {
                                        while (familS.hasNext()) {
                                            String s = familS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setFamil(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("d")) {
                                        while (ghazaS.hasNext()) {
                                            String s = ghazaS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setGhaza(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("e")) {
                                        while (golS.hasNext()) {
                                            String s = golS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setGol(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("f")) {
                                        while (heivanS.hasNext()) {
                                            String s = heivanS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setHeivan(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("g")) {
                                        while (keshvarS.hasNext()) {
                                            String s = keshvarS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setKeshvar(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("h")) {
                                        while (mashinS.hasNext()) {
                                            String s = mashinS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setMashin(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("i")) {
                                        while (miveS.hasNext()) {
                                            String s = miveS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setMive(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("j")) {
                                        while (pooshakS.hasNext()) {
                                            String s = pooshakS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setPooshak(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("k")) {
                                        while (shahrS.hasNext()) {
                                            String s = shahrS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setShahr(s);
                                                break;
                                            }
                                        }
                                    }


                                    boolean flag = true;
                                    if(selectedFields.contains("a")) {
                                        if(player.getAshiaTXF().getText().length() != 0) {
                                            if(player.getAshiaTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("b")) {
                                        if(player.getEsmTXF().getText().length() != 0) {
                                            if(player.getEsmTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("c")) {
                                        if(player.getFamilTXF().getText().length() != 0){
                                            if(player.getFamilTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;

                                    }
                                    if(selectedFields.contains("d")) {
                                        if(player.getGhazaTXF().getText().length() != 0) {
                                            if(player.getGhazaTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("e")) {
                                        if(player.getGolTXF().getText().length() != 0) {
                                            if(player.getGolTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("f")) {
                                        if(player.getHeivanTXF().getText().length() != 0) {
                                            if(player.getHeivanTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("g")) {
                                        if(player.getKeshvarTXF().getText().length() != 0) {
                                            if(player.getKeshvarTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("h")) {
                                        if(player.getMashinTXF().getText().length() != 0) {
                                            if(player.getMashinTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("i")) {
                                        if(player.getMiveTXF().getText().length() != 0) {
                                            if(player.getMiveTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("j")) {
                                        if(player.getPooshakTXF().getText().length() != 0) {
                                            if(player.getPooshakTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("k")) {
                                        if(player.getShahrTXF().getText().length() != 0) {
                                            if(player.getShahrTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }

                                    if(flag) {
                                        calculate(systemm, player, selectedFields);
                                        player.getComputerscoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                        player.getPlayerscoreLBL().setText("امتیاز شما:" + playerScore);
                                        used[rond] = letter;
                                        player.getRondLBL().setText("دست:" + (rond + 2) + "/" + ronds);
                                        letterflag = true;
                                        rond++;

                                        if(rond == ronds) {
                                            if(playerScore > SystemmScore) {
                                                EndScene endScene = new EndScene("win.png");
                                                endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                                endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                                endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent event) {
                                                        primarystage.setScene(main.getScene());
                                                    }
                                                });
                                                primarystage.setScene(endScene.getScene());
                                            }
                                            if(playerScore < SystemmScore) {
                                                EndScene endScene = new EndScene("lose.png");
                                                endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                                endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                                endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent event) {
                                                        primarystage.setScene(main.getScene());
                                                    }
                                                });
                                                primarystage.setScene(endScene.getScene());
                                            }
                                            if(playerScore == SystemmScore) {
                                                EndScene endScene = new EndScene("tie.png");
                                                endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                                endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                                endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent event) {
                                                        primarystage.setScene(main.getScene());
                                                    }
                                                });
                                                primarystage.setScene(endScene.getScene());
                                            }
                                        }

                                        if(rond % 2 == 0 && rond != 0 && rond != ronds) {
                                            SelectAlphabetScene selectAlphabet = new SelectAlphabetScene(used);
                                            primarystage.setScene(selectAlphabet.getScene());
                                            selectAlphabet.getContinueBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    letter = (String) selectAlphabet.getAlphabetCOMBO().getValue();
                                                    player.getLetter().setText(letter);
                                                    primarystage.setScene(player.getScene());
                                                }
                                            });
                                        }else if(rond != 0 && rond != ronds){
                                            while (true) {
                                                int[] a = {1570, 1575, 1576, 1662, 1578, 1579, 1580, 1670, 1581, 1582, 1583, 1584, 1585, 1586, 1688, 1587, 1588, 1589, 1590, 1591, 1592, 1593, 1594, 1601, 1602, 1705, 1711, 1604, 1605, 1606, 1607, 1608, 1740};
                                                Random random = new Random();
                                                int b = random.nextInt(33);
                                                boolean flag1 = true;
                                                for(int i=  0; i < rond; i++) {
                                                    if((char) a[b] == used[i].charAt(0)) {
                                                        flag1 = false;
                                                        break;
                                                    }
                                                }
                                                if(flag1 && letterflag) {
                                                    letter = (char)a[b] + "";
                                                    player.getLetter().setText(letter);
                                                    letterflag = false;
                                                    break;
                                                }else
                                                    continue;
                                            }
                                        }


                                        systemm.setAshia("0");
                                        systemm.setEsm("0");
                                        systemm.setFamil("0");
                                        systemm.setGhaza("0");
                                        systemm.setGol("0");
                                        systemm.setHeivan("0");
                                        systemm.setKeshvar("0");
                                        systemm.setMashin("0");
                                        systemm.setMive("0");
                                        systemm.setPooshak("0");
                                        systemm.setShahr("0");

                                        player.getAshiaTXF().setText(null);
                                        player.getEsmTXF().setText(null);
                                        player.getFamilTXF().setText(null);
                                        player.getGhazaTXF().setText(null);
                                        player.getGolTXF().setText(null);
                                        player.getHeivanTXF().setText(null);
                                        player.getKeshvarTXF().setText(null);
                                        player.getMashinTXF().setText(null);
                                        player.getMiveTXF().setText(null);
                                        player.getPooshakTXF().setText(null);
                                        player.getShahrTXF().setText(null);
                                    }else {
                                        WarningOnLetter warningOnLetter = new WarningOnLetter();
                                        warningOnLetter.getWarningLBL().setText("کلمه مورد نظر باید با " + letter + " شروع شود");
                                        warningStage.setScene(warningOnLetter.getScene());
                                        warningStage.show();
                                        warningOnLetter.getOkBTN().setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                warningStage.close();
                                            }
                                        });
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else {
                        if(create.getTime1().isSelected()) {
                            time = 60;
                        }
                        if(create.getTime2().isSelected()) {
                            time = 120;
                        }
                        if(create.getTime3().isSelected()) {
                            time = 180;
                        }

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                time--;
                                int min = time / 60;
                                int sec = time % 60;

                                if(time > 10) {
                                    player.getTimer().setText("زمان باقی مانده:" + min + ":" + sec);
                                }else {
                                    player.getTimer().setFill(Color.RED);
                                    player.getTimer().setText("زمان باقی مانده:" + min + ":" + sec);
                                }
                                if(time == 0){
                                    endTime = true;
                                    player.getFamilTXF().setDisable(true);
                                    player.getEsmTXF().setDisable(true);
                                    player.getShahrTXF().setDisable(true);
                                    player.getPooshakTXF().setDisable(true);
                                    player.getMiveTXF().setDisable(true);
                                    player.getMashinTXF().setDisable(true);
                                    player.getKeshvarTXF().setDisable(true);
                                    player.getHeivanTXF().setDisable(true);
                                    player.getGolTXF().setDisable(true);
                                    player.getGhazaTXF().setDisable(true);
                                    player.getAshiaTXF().setDisable(true);
                                    timer.cancel();
                                    player.getTimer().setText("زمان شما به اتمام رسید");
                                }
                            }
                        }, 0, 1000);
                        player.getFinishBTN().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {

                                    if(endTime) {
                                        if(playerScore > SystemmScore) {
                                            EndScene endScene = new EndScene("win.png");
                                            endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                            endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                            endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    primarystage.setScene(main.getScene());
                                                }
                                            });
                                            primarystage.setScene(endScene.getScene());
                                        }
                                        if(playerScore < SystemmScore) {
                                            EndScene endScene = new EndScene("lose.png");
                                            endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                            endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                            endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    primarystage.setScene(main.getScene());
                                                }
                                            });
                                            primarystage.setScene(endScene.getScene());
                                        }
                                        if(playerScore == SystemmScore) {
                                            EndScene endScene = new EndScene("tie.png");
                                            endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                            endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                            endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    primarystage.setScene(main.getScene());
                                                }
                                            });
                                            primarystage.setScene(endScene.getScene());
                                        }
                                    }
                                    Scanner ashiaS = null;
                                    Scanner esmS = null;
                                    Scanner familS = null;
                                    Scanner heivanS = null;
                                    Scanner shahrS = null;
                                    Scanner keshvarS = null;
                                    Scanner mashinS = null;
                                    Scanner golS = null;
                                    Scanner miveS = null;
                                    Scanner pooshakS = null;
                                    Scanner ghazaS = null;


                                    try {
                                        ashiaS = new Scanner(new File("ashia.txt"));
                                        esmS = new Scanner(new File("esm.txt"));
                                        familS = new Scanner(new File("famil.txt"));
                                        heivanS = new Scanner(new File("heivan.txt"));
                                        shahrS = new Scanner(new File("shahr.txt"));
                                        keshvarS = new Scanner(new File("keshvar.txt"));
                                        mashinS = new Scanner(new File("mashin.txt"));
                                        golS = new Scanner(new File("gol.txt"));
                                        miveS = new Scanner(new File("mive.txt"));
                                        pooshakS = new Scanner(new File("pooshak.txt"));
                                        ghazaS = new Scanner(new File("ghaza.txt"));
                                    }catch (Exception e) {

                                    }

                                    if(selectedFields.contains("a")) {
                                        while (ashiaS.hasNext()) {
                                            String s = ashiaS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setAshia(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("b")) {
                                        while (esmS.hasNext()) {
                                            String s = esmS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setEsm(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("c")) {
                                        while (familS.hasNext()) {
                                            String s = familS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setFamil(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("d")) {
                                        while (ghazaS.hasNext()) {
                                            String s = ghazaS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setGhaza(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("e")) {
                                        while (golS.hasNext()) {
                                            String s = golS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setGol(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("f")) {
                                        while (heivanS.hasNext()) {
                                            String s = heivanS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setHeivan(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("g")) {
                                        while (keshvarS.hasNext()) {
                                            String s = keshvarS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setKeshvar(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("h")) {
                                        while (mashinS.hasNext()) {
                                            String s = mashinS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setMashin(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("i")) {
                                        while (miveS.hasNext()) {
                                            String s = miveS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setMive(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("j")) {
                                        while (pooshakS.hasNext()) {
                                            String s = pooshakS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setPooshak(s);
                                                break;
                                            }
                                        }
                                    }
                                    if(selectedFields.contains("k")) {
                                        while (shahrS.hasNext()) {
                                            String s = shahrS.nextLine();
                                            if(s.charAt(0) == letter.charAt(0)) {
                                                systemm.setShahr(s);
                                                break;
                                            }
                                        }
                                    }


                                    boolean flag = true;
                                    if(selectedFields.contains("a")) {
                                        if(player.getAshiaTXF().getText().length() != 0) {
                                            if(player.getAshiaTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("b")) {
                                        if(player.getEsmTXF().getText().length() != 0) {
                                            if(player.getEsmTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("c")) {
                                        if(player.getFamilTXF().getText().length() != 0){
                                            if(player.getFamilTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;

                                    }
                                    if(selectedFields.contains("d")) {
                                        if(player.getGhazaTXF().getText().length() != 0) {
                                            if(player.getGhazaTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("e")) {
                                        if(player.getGolTXF().getText().length() != 0) {
                                            if(player.getGolTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("f")) {
                                        if(player.getHeivanTXF().getText().length() != 0) {
                                            if(player.getHeivanTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("g")) {
                                        if(player.getKeshvarTXF().getText().length() != 0) {
                                            if(player.getKeshvarTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("h")) {
                                        if(player.getMashinTXF().getText().length() != 0) {
                                            if(player.getMashinTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("i")) {
                                        if(player.getMiveTXF().getText().length() != 0) {
                                            if(player.getMiveTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("j")) {
                                        if(player.getPooshakTXF().getText().length() != 0) {
                                            if(player.getPooshakTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }
                                    if(selectedFields.contains("k")) {
                                        if(player.getShahrTXF().getText().length() != 0) {
                                            if(player.getShahrTXF().getText().charAt(0) != letter.charAt(0))
                                                flag = false;
                                        }
                                        else
                                            flag = false;
                                    }

                                    if(flag) {
                                        calculate(systemm, player, selectedFields);
                                        player.getComputerscoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                        player.getPlayerscoreLBL().setText("امتیاز شما:" + playerScore);
                                        used[rond] = letter;
                                        player.getRondLBL().setText("دست:" + (rond + 2) + "/" + ronds);
                                        letterflag = true;
                                        rond++;

                                        if(rond == ronds) {
                                            if(playerScore > SystemmScore) {
                                                EndScene endScene = new EndScene("win.png");
                                                endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                                endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                                endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent event) {
                                                        primarystage.setScene(main.getScene());
                                                    }
                                                });
                                                primarystage.setScene(endScene.getScene());
                                            }
                                            if(playerScore < SystemmScore) {
                                                EndScene endScene = new EndScene("lose.png");
                                                endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                                endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                                endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent event) {
                                                        primarystage.setScene(main.getScene());
                                                    }
                                                });
                                                primarystage.setScene(endScene.getScene());
                                            }
                                            if(playerScore == SystemmScore) {
                                                EndScene endScene = new EndScene("tie.png");
                                                endScene.getPlayerScoreLBL().setText("امتیاز شما:" + playerScore);
                                                endScene.getSystemScoreLBL().setText("امتیاز کامپیوتر:" + SystemmScore);
                                                endScene.getEndBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent event) {
                                                        primarystage.setScene(main.getScene());
                                                    }
                                                });
                                                primarystage.setScene(endScene.getScene());
                                            }
                                        }

                                        if(rond % 2 == 0 && rond != 0 && rond != ronds) {
                                            SelectAlphabetScene selectAlphabet = new SelectAlphabetScene(used);
                                            primarystage.setScene(selectAlphabet.getScene());
                                            selectAlphabet.getContinueBTN().setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    letter = (String) selectAlphabet.getAlphabetCOMBO().getValue();
                                                    player.getLetter().setText(letter);
                                                    primarystage.setScene(player.getScene());
                                                }
                                            });
                                        }else if(rond != 0 && rond != ronds){
                                            while (true) {
                                                int[] a = {1570, 1575, 1576, 1662, 1578, 1579, 1580, 1670, 1581, 1582, 1583, 1584, 1585, 1586, 1688, 1587, 1588, 1589, 1590, 1591, 1592, 1593, 1594, 1601, 1602, 1705, 1711, 1604, 1605, 1606, 1607, 1608, 1740};
                                                Random random = new Random();
                                                int b = random.nextInt(33);
                                                boolean flag1 = true;
                                                for(int i=  0; i < rond; i++) {
                                                    if((char) a[b] == used[i].charAt(0)) {
                                                        flag1 = false;
                                                        break;
                                                    }
                                                }
                                                if(flag1 && letterflag) {
                                                    letter = (char)a[b] + "";
                                                    player.getLetter().setText(letter);
                                                    letterflag = false;
                                                    break;
                                                }else
                                                    continue;
                                            }
                                        }


                                        systemm.setAshia("0");
                                        systemm.setEsm("0");
                                        systemm.setFamil("0");
                                        systemm.setGhaza("0");
                                        systemm.setGol("0");
                                        systemm.setHeivan("0");
                                        systemm.setKeshvar("0");
                                        systemm.setMashin("0");
                                        systemm.setMive("0");
                                        systemm.setPooshak("0");
                                        systemm.setShahr("0");

                                        player.getAshiaTXF().setText(null);
                                        player.getEsmTXF().setText(null);
                                        player.getFamilTXF().setText(null);
                                        player.getGhazaTXF().setText(null);
                                        player.getGolTXF().setText(null);
                                        player.getHeivanTXF().setText(null);
                                        player.getKeshvarTXF().setText(null);
                                        player.getMashinTXF().setText(null);
                                        player.getMiveTXF().setText(null);
                                        player.getPooshakTXF().setText(null);
                                        player.getShahrTXF().setText(null);
                                    }else if(!endTime){
                                        WarningOnLetter warningOnLetter = new WarningOnLetter();
                                        warningOnLetter.getWarningLBL().setText("کلمه مورد نظر باید با " + letter + " شروع شود");
                                        warningStage.setScene(warningOnLetter.getScene());
                                        warningStage.show();
                                        warningOnLetter.getOkBTN().setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                warningStage.close();
                                            }
                                        });
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }

                }else {
                    warningStage.setScene(warningOnFieldsCount.getScene());
                    warningStage.show();
                }
            }
        });
        warningOnFieldsCount.getOkBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                warningStage.close();
            }
        });


        primarystage.setScene(main.getScene());
        primarystage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                timer.cancel();
            }
        });
        primarystage.show();
    }








    public void changeTheme(int a) {
        switch(a) {
            case 1:
                main.getChangeThemeBTN().setStyle("-fx-background-color: #99D9EA; ");
                main.getExitBTN().setStyle("-fx-background-color: #99D9EA; ");
                main.getSettingBTN().setStyle("-fx-background-color: #99D9EA; ");
                main.getAboutUsBTN().setStyle("-fx-background-color: #99D9EA; ");
                main.getStartBTN().setStyle("-fx-background-color: #FF8106; ");
                setting.getHelpBTN().setStyle("-fx-background-color: #99D9EA; ");
                setting.getMusicBTN().setStyle("-fx-background-color: #99D9EA; ");
                aboutUs.getInstagramBTN().setStyle("-fx-background-color: #99D9EA; ");
                aboutUs.getTelegramBTN().setStyle("-fx-background-color: #99D9EA; ");
                exit.getYesCloseBTN().setStyle("-fx-background-color: #99D9EA; ");
                exit.getNoCloseBTN().setStyle("-fx-background-color: #99D9EA; ");
                theme.getTheme1BTN().setStyle("-fx-background-color: #99D9EA; ");
                theme.getTheme2BTN().setStyle("-fx-background-color: #99D9EA; ");
                theme.getTheme3BTN().setStyle("-fx-background-color: #99D9EA; ");
                create.getConfirmBTN().setStyle("-fx-background-color: #99D9EA; ");
                player.getFinishBTN().setStyle("-fx-background-color: #99D9EA; ");
                create.getBackCreateBTN().setStyle("-fx-background-color: #74EA64; ");
                setting.getBackSettingBTN().setStyle("-fx-background-color: #74EA64; ");
                aboutUs.getBackAboutUsBTN().setStyle("-fx-background-color: #74EA64; ");
                help.getBackHelpBTN().setStyle("-fx-background-color: #74EA64; ");
                theme.getBackThemeBTN().setStyle("-fx-background-color: #74EA64; ");
                break;
            case 2:
                main.getChangeThemeBTN().setStyle("-fx-background-color: #D64161FF; ");
                main.getExitBTN().setStyle("-fx-background-color: #D64161FF; ");
                main.getSettingBTN().setStyle("-fx-background-color: #D64161FF; ");
                main.getAboutUsBTN().setStyle("-fx-background-color: #D64161FF; ");
                main.getStartBTN().setStyle("-fx-background-color: #FF8106; ");
                setting.getHelpBTN().setStyle("-fx-background-color: #D64161FF; ");
                setting.getMusicBTN().setStyle("-fx-background-color: #D64161FF; ");
                aboutUs.getInstagramBTN().setStyle("-fx-background-color: #D64161FF; ");
                aboutUs.getTelegramBTN().setStyle("-fx-background-color: #D64161FF; ");
                exit.getYesCloseBTN().setStyle("-fx-background-color: #D64161FF; ");
                exit.getNoCloseBTN().setStyle("-fx-background-color: #D64161FF; ");
                theme.getTheme1BTN().setStyle("-fx-background-color: #D64161FF; ");
                theme.getTheme2BTN().setStyle("-fx-background-color: #D64161FF; ");
                theme.getTheme3BTN().setStyle("-fx-background-color: #D64161FF; ");
                create.getConfirmBTN().setStyle("-fx-background-color: #D64161FF; ");
                player.getFinishBTN().setStyle("-fx-background-color: #D64161FF; ");
                create.getBackCreateBTN().setStyle("-fx-background-color: #435E55FF; ");
                setting.getBackSettingBTN().setStyle("-fx-background-color: #435E55FF; ");
                aboutUs.getBackAboutUsBTN().setStyle("-fx-background-color: #435E55FF; ");
                help.getBackHelpBTN().setStyle("-fx-background-color: #435E55FF; ");
                theme.getBackThemeBTN().setStyle("-fx-background-color: #435E55FF; ");
                break;
            case 3:
                main.getChangeThemeBTN().setStyle("-fx-background-color: #FFD662FF; ");
                main.getExitBTN().setStyle("-fx-background-color: #FFD662FF; ");
                main.getSettingBTN().setStyle("-fx-background-color: #FFD662FF; ");
                main.getAboutUsBTN().setStyle("-fx-background-color: #FFD662FF; ");
                main.getStartBTN().setStyle("-fx-background-color: #FF8106; ");
                setting.getHelpBTN().setStyle("-fx-background-color: #FFD662FF; ");
                setting.getMusicBTN().setStyle("-fx-background-color: #FFD662FF; ");
                aboutUs.getInstagramBTN().setStyle("-fx-background-color: #FFD662FF; ");
                aboutUs.getTelegramBTN().setStyle("-fx-background-color: #FFD662FF; ");
                exit.getYesCloseBTN().setStyle("-fx-background-color: #FFD662FF; ");
                exit.getNoCloseBTN().setStyle("-fx-background-color: #FFD662FF; ");
                theme.getTheme1BTN().setStyle("-fx-background-color: #FFD662FF; ");
                theme.getTheme2BTN().setStyle("-fx-background-color: #FFD662FF; ");
                theme.getTheme3BTN().setStyle("-fx-background-color: #FFD662FF; ");
                create.getConfirmBTN().setStyle("-fx-background-color: #FFD662FF; ");
                player.getFinishBTN().setStyle("-fx-background-color: #FFD662FF; ");
                create.getBackCreateBTN().setStyle("-fx-background-color: #00539CFF; ");
                setting.getBackSettingBTN().setStyle("-fx-background-color: #00539CFF; ");
                aboutUs.getBackAboutUsBTN().setStyle("-fx-background-color: #00539CFF; ");
                help.getBackHelpBTN().setStyle("-fx-background-color: #00539CFF; ");
                theme.getBackThemeBTN().setStyle("-fx-background-color: #00539CFF; ");
                break;
        }
    }

    public boolean isValidGame(CreateScene create) {
        int count = 0;

        if(create.getAshiaCH().isSelected()) {
            count++;
            selectedFields += "a";
        }
        if(create.getEsmCH().isSelected()) {
            count++;
            selectedFields += "b";
        }
        if(create.getFamilCH().isSelected()){
            count++;
            selectedFields += "c";
        }
        if(create.getGhazaCH().isSelected()) {
            count++;
            selectedFields += "d";
        }
        if(create.getGolCH().isSelected()) {
            count++;
            selectedFields += "e";
        }
        if(create.getHeivanCh().isSelected()) {
            count++;
            selectedFields += "f";
        }
        if(create.getKeshvarCH().isSelected()) {
            count++;
            selectedFields += "g";
        }
        if(create.getMashinCH().isSelected()) {
            count++;
            selectedFields += "h";
        }
        if(create.getMiveCH().isSelected()) {
            count++;
            selectedFields += "i";
        }
        if(create.getPooshakCH().isSelected()) {
            count++;
            selectedFields += "j";
        }
        if(create.getShahrCH().isSelected()) {
            count++;
            selectedFields += "k";
        }

        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        if(create.getAlphabetCOMBO().getValue() == null)
            flag1 = false;
        if(create.getRondCOMBO().getValue() == null)
            flag2 = false;
        if(create.getGameTypeCOMBO().getValue() == null)
            flag3 = false;
        if(create.getPlayerNameTXF().getText().length() <= 0)
            flag4 = false;

        if(count >= 5 && flag2 && flag1 && flag3 && flag4)
            return true;
        else
            return false;
    }

    public void calculate(Systemm systemm, Player player, String fields) throws FileNotFoundException {



        File ashiaF = new File("ashia.txt");
        File esmF = new File("esm.txt");
        File familF = new File("famil.txt");
        File heivanF = new File("heivan.txt");
        File shahrF = new File("shahr.txt");
        File keshvarF = new File("keshvar.txt");
        File mashinF = new File("mashin.txt");
        File golF = new File("gol.txt");
        File miveF = new File("mive.txt");
        File pooshakF = new File("pooshak.txt");
        File ghazaF = new File("ghaza.txt");


        Scanner ashiaS = new Scanner(ashiaF);
        Scanner esmS = new Scanner(esmF);
        Scanner familS = new Scanner(familF);
        Scanner heivanS = new Scanner(heivanF);
        Scanner shahrS = new Scanner(shahrF);
        Scanner keshvarS = new Scanner(keshvarF);
        Scanner mashinS = new Scanner(mashinF);
        Scanner golS = new Scanner(golF);
        Scanner miveS = new Scanner(miveF);
        Scanner pooshakS = new Scanner(pooshakF);
        Scanner ghazaS = new Scanner(ghazaF);


        if(fields.contains("a")) {
            String s = player.getAshiaTXF().getText();
            boolean flag = true;
            while (ashiaS.hasNext()) {
                String q = ashiaS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getAshia())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("b")) {
            String s = player.getEsmTXF().getText();
            boolean flag = true;
            while (esmS.hasNext()) {
                String q = esmS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getEsm())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("c")) {
            String s = player.getFamilTXF().getText();
            boolean flag = true;
            while (familS.hasNext()) {
                String q = familS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getFamil())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("d")) {
            String s = player.getGhazaTXF().getText();
            boolean flag = true;
            while (ghazaS.hasNext()) {
                String q = ghazaS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getGhaza())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("e")) {
            String s = player.getGolTXF().getText();
            boolean flag = true;
            while (golS.hasNext()) {
                String q = golS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getGol())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("f")) {
            String s = player.getHeivanTXF().getText();
            boolean flag = true;
            while (heivanS.hasNext()) {
                String q = heivanS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getHeivan())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("g")) {
            String s = player.getKeshvarTXF().getText();
            boolean flag = true;
            while (keshvarS.hasNext()) {
                String q = keshvarS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getKeshvar())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("h")) {
            String s = player.getMashinTXF().getText();
            boolean flag = true;
            while (mashinS.hasNext()) {
                String q = mashinS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getMashin())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("i")) {
            String s = player.getMiveTXF().getText();
            boolean flag = true;
            while (miveS.hasNext()) {
                String q = miveS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getMive())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("j")) {
            String s = player.getPooshakTXF().getText();
            boolean flag = true;
            while (pooshakS.hasNext()) {
                String q = pooshakS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getPooshak())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
        if(fields.contains("k")) {
            String s = player.getShahrTXF().getText();
            boolean flag = true;
            while (shahrS.hasNext()) {
                String q = shahrS.nextLine();
                q = removeWhiteSpace(q);
                if(q.equals(s)) {
                    flag = false;
                    if(s.equals(systemm.getShahr())) {
                        playerScore += 5;
                        SystemmScore += 5;
                    }else {
                        playerScore += 10;
                        SystemmScore += 10;
                    }
                    break;
                }
            }
            if(flag) {
                SystemmScore += 10;
            }
        }
    }

    public static String removeWhiteSpace(String s) {
        String q = "";
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '\u200C' && s.charAt(i) != ' ') {
                q += s.charAt(i);
            }
        }
        return q;
    }
}
