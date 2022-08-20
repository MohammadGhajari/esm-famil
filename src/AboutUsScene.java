import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class AboutUsScene {
    private Button backAboutUsBTN = new Button(), telegramBTN = new Button(), instagramBTN = new Button();
    private  StackPane MainRoot;
    private Label aboutUsLBL = new Label("راههای ارتباط با ما:");
    private Scene scene;


    public AboutUsScene() {
        telegramBTN.setGraphic(new ImageView("telegramMouseOnExit.png"));
        telegramBTN.setLayoutX(605);
        telegramBTN.setLayoutY(180);
        telegramBTN.setStyle("-fx-background-color: #F0B5F5; ");
        telegramBTN.setPrefSize(60, 40);
        telegramBTN.setTooltip(new Tooltip("تلگرام"));

        instagramBTN.setGraphic(new ImageView("instaMouseOnExit.png"));
        instagramBTN.setLayoutX(605);
        instagramBTN.setLayoutY(260);
        instagramBTN.setStyle("-fx-background-color: #F0B5F5; ");
        instagramBTN.setPrefSize(60, 40);
        instagramBTN.setTooltip(new Tooltip("اینستاگرام"));

        backAboutUsBTN.setGraphic(new ImageView("backMouseOnExit.png"));
        backAboutUsBTN.setLayoutY(0);
        backAboutUsBTN.setLayoutX(0);
        backAboutUsBTN.setStyle("-fx-background-color: #1BC6F5; ");
        backAboutUsBTN.setTooltip(new Tooltip("بازگشت"));

        aboutUsLBL.setLayoutX(580);
        aboutUsLBL.setLayoutY(150);

        Pane aboutUsRoot = new Pane(backAboutUsBTN, instagramBTN, telegramBTN, aboutUsLBL);
        ImageView backgruand = new ImageView("background.png");
        MainRoot = new StackPane(backgruand, aboutUsRoot);

        scene = new Scene(MainRoot, 1280, 720);

        getBackAboutUsBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getBackAboutUsBTN().setGraphic(new ImageView("backMouseOnEnter.png"));
            }
        });
        getBackAboutUsBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getBackAboutUsBTN().setGraphic(new ImageView("backMouseOnExit.png"));
            }
        });
        getInstagramBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getInstagramBTN().setGraphic(new ImageView("instaMouseOnEnter.png"));
            }
        });
        getInstagramBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getInstagramBTN().setGraphic(new ImageView("instaMouseOnExit.png"));
            }
        });
        getTelegramBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getTelegramBTN().setGraphic(new ImageView("telegramMouseOnEnter.png"));
            }
        });
        getTelegramBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getTelegramBTN().setGraphic(new ImageView("telegramMouseOnExit.png"));
            }
        });
    }

    public Scene getScene() {
        return scene;
    }

    public Button getBackAboutUsBTN() {
        return backAboutUsBTN;
    }

    public Button getTelegramBTN() {
        return telegramBTN;
    }

    public Button getInstagramBTN() {
        return instagramBTN;
    }

}
