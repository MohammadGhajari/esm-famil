import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class EndScene {

    private Scene scene;
    private Label systemScoreLBL, playerScoreLBL;
    private Button endBTN = new Button();
    public EndScene(String s) {


        systemScoreLBL = new Label();
        systemScoreLBL.setLayoutX(550);
        systemScoreLBL.setLayoutY(200);
        systemScoreLBL.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 25));

        playerScoreLBL = new Label();
        playerScoreLBL.setLayoutY(250);
        playerScoreLBL.setLayoutX(550);
        playerScoreLBL.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 25));


        endBTN.setGraphic(new ImageView("homeMouseOnExit.png"));
        endBTN.setStyle("-fx-background-color: #4FF58C; ");
        endBTN.setPrefSize(70, 50);
        endBTN.setTooltip(new Tooltip("خانه"));
        endBTN.setLayoutX(605);
        endBTN.setLayoutY(600);

        endBTN.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                endBTN.setGraphic(new ImageView("homeMouseOnEnter.png"));
            }
        });
        endBTN.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                endBTN.setGraphic(new ImageView("homeMouseOnExit.png"));
            }
        });

        Pane pane = new Pane(systemScoreLBL, playerScoreLBL, endBTN);

        ImageView imageView = new ImageView(s);
        StackPane stackPane = new StackPane(imageView, pane);
        scene = new Scene(stackPane, 1280, 720);
    }

    public Scene getScene() {
        return scene;
    }

    public Label getSystemScoreLBL() {
        return systemScoreLBL;
    }

    public Label getPlayerScoreLBL() {
        return playerScoreLBL;
    }


    public Button getEndBTN() {
        return endBTN;
    }
}
