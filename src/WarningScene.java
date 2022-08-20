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

public class WarningScene {
    private Button okBTN = new Button();
    private Label warningLBL;
    private Scene scene;

    public WarningScene() {
        okBTN.setPrefSize(70, 50);
        okBTN.setLayoutX(165);
        okBTN.setLayoutY(100);
        okBTN.setStyle("-fx-background-color: #8A8CF5; ");
        okBTN.setGraphic(new ImageView("YesMouseOnExit.png"));

        warningLBL = new Label("حداقل 5 فیلد را انتخاب کنید!!!");
        warningLBL.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 25));
        warningLBL.setTextFill(Color.AQUA);
        warningLBL.setLayoutY(40);
        warningLBL.setLayoutX(40);

        Pane YesNoCloseRoot = new Pane(okBTN, warningLBL);
        ImageView ExitBackground = new ImageView("ExitBackground.jpg");
        StackPane Root = new StackPane(ExitBackground, YesNoCloseRoot);
        scene = new Scene(Root, 400, 200);

        getOkBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getOkBTN().setGraphic(new ImageView("YesMouseOnEnter.png"));
            }
        });
        getOkBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getOkBTN().setGraphic(new ImageView("YesMouseOnExit.png"));
            }
        });

    }

    public Scene getScene() {
        return scene;
    }

    public Button getOkBTN() {
        return okBTN;
    }
}
