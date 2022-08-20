import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SettingScene {
    private Button musicBTN = new Button(), backSettingBTN = new Button(), helpBTN = new Button();
    private StackPane MainRoot;
    private Scene scene;
    private Slider slider = new Slider();


    public SettingScene() {
        backSettingBTN.setGraphic(new ImageView("backMouseOnExit.png"));
        backSettingBTN.setTooltip(new Tooltip("بازگشت"));
        backSettingBTN.setStyle("-fx-background-color: #1BC6F5; ");


        helpBTN.setGraphic(new ImageView("helpMouseOnExit.png"));
        helpBTN.setLayoutX(595);
        helpBTN.setLayoutY(400);
        helpBTN.setStyle("-fx-background-color: #4FF58C; ");
        helpBTN.setTooltip(new Tooltip("راهنما"));

        slider.setLayoutX(560);
        slider.setLayoutY(370);
        slider.setPrefSize(150, 20);

        musicBTN.setGraphic(new ImageView("music_unmute.png"));
        musicBTN.setLayoutX(595);
        musicBTN.setLayoutY(300);
        musicBTN.setStyle("-fx-background-color: #4FF58C; ");
        musicBTN.setTooltip(new Tooltip("قطع و وصل صدا"));




        Pane Setting_Root = new Pane();
        Setting_Root.getChildren().addAll(backSettingBTN, musicBTN, helpBTN, slider);

        ImageView backgruand = new ImageView("background.png");
        MainRoot = new StackPane(backgruand, Setting_Root);

        scene = new Scene(MainRoot, 1280, 720);

        getHelpBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getHelpBTN().setGraphic(new ImageView("helpMouseOnEnter.png"));
            }
        });
        getHelpBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getHelpBTN().setGraphic(new ImageView("helpMouseOnExit.png"));
            }
        });
        getBackSettingBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getBackSettingBTN().setGraphic(new ImageView("backMouseOnEnter.png"));
            }
        });
        getBackSettingBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getBackSettingBTN().setGraphic(new ImageView("backMouseOnExit.png"));
            }
        });
        getMusicBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
            }
        });
        getMusicBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
            }
        });

    }

    public Scene getScene() {
        return scene;
    }

    public Button getMusicBTN() {
        return musicBTN;
    }

    public Button getBackSettingBTN() {
        return backSettingBTN;
    }

    public Button getHelpBTN() {
        return helpBTN;
    }

    public Slider getSlider() {
        return slider;
    }
}
