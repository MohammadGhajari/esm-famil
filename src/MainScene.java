import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.util.Timer;

public class MainScene {
    private Button settingBTN = new Button(), startBTN = new Button(), exitBTN = new Button(), aboutUsBTN = new Button(), changeThemeBTN = new Button();
    private StackPane MainRoot;
    private Pane ButtonsRoot;
    private MediaView mediaView;
    private Scene scene;



    public MainScene() {
        startBTN.setGraphic(new ImageView("startMouseOnExit.png"));
        startBTN.setPrefSize(100, 50);
        startBTN.setStyle("-fx-background-color: #27C9F5; ");
        startBTN.setLayoutX(610);
        startBTN.setLayoutY(250);
        startBTN.setTooltip(new Tooltip("شروع بازی"));


        settingBTN.setGraphic(new ImageView("settingMouseOnExit.png"));
        settingBTN.setPrefSize(70, 50);
        settingBTN.setStyle("-fx-background-color: #4FF58C; ");
        settingBTN.setLayoutX(625);
        settingBTN.setLayoutY(350);
        settingBTN.setTooltip(new Tooltip("تنظیمات"));


        exitBTN.setPrefSize(70, 50);
        exitBTN.setStyle("-fx-background-color: #4FF58C; ");
        exitBTN.setGraphic(new ImageView("ExitGreen.png"));
        exitBTN.setLayoutX(625);
        exitBTN.setLayoutY(560);
        exitBTN.setTooltip(new Tooltip("خروج از برنامه"));


        aboutUsBTN.setGraphic(new ImageView("infoMouseOnExit.png"));
        aboutUsBTN.setPrefSize(70, 50);
        aboutUsBTN.setStyle("-fx-background-color: #4FF58C; ");
        aboutUsBTN.setLayoutX(625);
        aboutUsBTN.setLayoutY(490);
        aboutUsBTN.setTooltip(new Tooltip("درباره ما"));


        changeThemeBTN.setGraphic(new ImageView("ChangeThemeMouseOnExit.png"));
        changeThemeBTN.setPrefSize(70, 50);
        changeThemeBTN.setStyle("-fx-background-color: #4FF58C; ");
        changeThemeBTN.setLayoutX(625);
        changeThemeBTN.setLayoutY(420);
        changeThemeBTN.setTooltip(new Tooltip("تغییر تم"));


        Media media = new Media(new File("Lake - 91562.mp4").toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        ButtonsRoot = new Pane(settingBTN, exitBTN, startBTN, aboutUsBTN, changeThemeBTN);
        MainRoot = new StackPane(mediaView, ButtonsRoot);

        scene = new Scene(MainRoot, 1280, 720);




        getChangeThemeBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getChangeThemeBTN().setGraphic(new ImageView("ChangeThemeMouseOnEnter.png"));
            }
        });
        getChangeThemeBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getChangeThemeBTN().setGraphic(new ImageView("ChangeThemeMouseOnExit.png"));
            }
        });
        getExitBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getExitBTN().setGraphic(new ImageView("ExitRed.png"));
            }
        });
        getExitBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getExitBTN().setGraphic(new ImageView("ExitGreen.png"));
                Main.buttonsPlaySound = true;
            }
        });
        getSettingBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getSettingBTN().setGraphic(new ImageView("settingMouseOnEnter.png"));
            }
        });
        getSettingBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getSettingBTN().setGraphic(new ImageView("settingMouseOnExit.png"));
            }
        });
        getAboutUsBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getAboutUsBTN().setGraphic(new ImageView("infoMouseOnEnter.png"));
            }
        });
        getAboutUsBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getAboutUsBTN().setGraphic(new ImageView("infoMouseOnExit.png"));
            }
        });
        getStartBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getStartBTN().setGraphic(new ImageView("startMouseOnEnter.png"));
            }
        });
        getStartBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getStartBTN().setGraphic(new ImageView("startMouseOnExit.png"));
            }
        });
    }

    public Scene getScene() {
        return scene;
    }

    public Button getSettingBTN() {
        return settingBTN;
    }

    public Button getStartBTN() {
        return startBTN;
    }

    public Button getExitBTN() {
        return exitBTN;
    }

    public Button getAboutUsBTN() {
        return aboutUsBTN;
    }

    public Pane getButtonsRoot() {
        return ButtonsRoot;
    }

    public Button getChangeThemeBTN() {
        return changeThemeBTN;
    }
}
