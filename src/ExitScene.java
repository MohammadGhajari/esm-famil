import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class ExitScene {
    private Button yesCloseBTN = new Button(), noCloseBTN = new Button();
    private Label closeWarningLBL;
    private Scene scene;

    public ExitScene() {
        yesCloseBTN.setLayoutX(120);
        yesCloseBTN.setLayoutY(100);
        yesCloseBTN.setStyle("-fx-background-color: #8A8CF5; ");
        yesCloseBTN.setGraphic(new ImageView("YesMouseOnExit.png"));

        noCloseBTN.setLayoutY(100);
        noCloseBTN.setLayoutX(220);
        noCloseBTN.setGraphic(new ImageView("NoMouseOnExit.png"));
        noCloseBTN.setStyle("-fx-background-color: #8A8CF5; ");

        closeWarningLBL = new Label("آیا میخواهید از برنامه خارج شوید؟");
        closeWarningLBL.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 25));
        closeWarningLBL.setTextFill(Color.AQUA);
        closeWarningLBL.setLayoutY(40);
        closeWarningLBL.setLayoutX(30);
        Pane YesNoCloseRoot = new Pane(yesCloseBTN, noCloseBTN, closeWarningLBL);
        ImageView ExitBackground = new ImageView("ExitBackground.jpg");
        StackPane Root = new StackPane(ExitBackground, YesNoCloseRoot);
        scene = new Scene(Root, 400, 200);

        getYesCloseBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getYesCloseBTN().setGraphic(new ImageView("YesMouseOnEnter.png"));
            }
        });
        getYesCloseBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getYesCloseBTN().setGraphic(new ImageView("YesMouseOnExit.png"));
            }
        });
        getNoCloseBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getNoCloseBTN().setGraphic(new ImageView("NoMouseOnEnter.png"));
            }
        });
        getNoCloseBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getNoCloseBTN().setGraphic(new ImageView("NoMouseOnExit.png"));
            }
        });
    }

    public Scene getScene() {

        return scene;

    }

    public Button getYesCloseBTN() {
        return yesCloseBTN;
    }

    public Button getNoCloseBTN() {
        return noCloseBTN;
    }
}
