import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ThemeScene {
    public Button theme1BTN = new Button(), theme2BTN = new Button(), theme3BTN = new Button(), backThemeBTN = new Button();
    private Scene scene;

    public ThemeScene() {
        backThemeBTN.setPrefSize(70, 50);
        backThemeBTN.setStyle("-fx-background-color: #4FF58C; ");
        backThemeBTN.setGraphic(new ImageView("backMouseOnExit.png"));
        backThemeBTN.setLayoutX(0);
        backThemeBTN.setLayoutY(0);
        backThemeBTN.setTooltip(new Tooltip("بازگشت"));


        theme1BTN.setPrefSize(70, 50);
        theme1BTN.setStyle("-fx-background-color: #4FF58C; ");
        theme1BTN.setGraphic(new ImageView("themeMouseExit.png"));
        theme1BTN.setLayoutX(625);
        theme1BTN.setLayoutY(270);
        theme1BTN.setTooltip(new Tooltip("تم 1"));


        theme2BTN.setGraphic(new ImageView("themeMouseExit.png"));
        theme2BTN.setPrefSize(70, 50);
        theme2BTN.setStyle("-fx-background-color: #4FF58C; ");
        theme2BTN.setLayoutX(625);
        theme2BTN.setLayoutY(340);
        theme2BTN.setTooltip(new Tooltip("تم 2"));


        theme3BTN.setGraphic(new ImageView("themeMouseExit.png"));
        theme3BTN.setPrefSize(70, 50);
        theme3BTN.setStyle("-fx-background-color: #4FF58C; ");
        theme3BTN.setLayoutX(625);
        theme3BTN.setLayoutY(410);
        theme3BTN.setTooltip(new Tooltip("تم 3"));

        ImageView imageView = new ImageView("background.png");
        Pane Root = new Pane(backThemeBTN, theme1BTN, theme2BTN, theme3BTN);
        StackPane MainRoot = new StackPane(imageView, Root);


        scene = new Scene(MainRoot, 1280, 720);

        getBackThemeBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getBackThemeBTN().setGraphic(new ImageView("backMouseOnEnter.png"));
            }
        });
        getBackThemeBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getBackThemeBTN().setGraphic(new ImageView("backMouseOnExit.png"));
            }
        });
        getTheme1BTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getTheme1BTN().setGraphic(new ImageView("themeMouseEnter.png"));
            }
        });
        getTheme1BTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getTheme1BTN().setGraphic(new ImageView("themeMouseExit.png"));
            }
        });
        getTheme2BTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getTheme2BTN().setGraphic(new ImageView("themeMouseEnter.png"));
            }
        });
        getTheme2BTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getTheme2BTN().setGraphic(new ImageView("themeMouseExit.png"));
            }
        });
        getTheme3BTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getTheme3BTN().setGraphic(new ImageView("themeMouseEnter.png"));
            }
        });
        getTheme3BTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getTheme3BTN().setGraphic(new ImageView("themeMouseExit.png"));
            }
        });
    }

    public Scene getScene() {
        return scene;
    }


    public Button getTheme1BTN() {
        return theme1BTN;
    }

    public Button getTheme2BTN() {
        return theme2BTN;
    }

    public Button getTheme3BTN() {
        return theme3BTN;
    }

    public Button getBackThemeBTN() {
        return backThemeBTN;
    }


}
