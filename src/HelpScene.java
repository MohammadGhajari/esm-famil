import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class HelpScene {
    private Button backHelpBTN = new Button();
    private Scene scene;


    public HelpScene() {

        backHelpBTN.setGraphic(new ImageView("backMouseOnExit.png"));
        backHelpBTN.setStyle("-fx-background-color: #1BC6F5; ");
        backHelpBTN.setLayoutY(0);
        backHelpBTN.setLayoutX(0);
        backHelpBTN.setTooltip(new Tooltip("بازگشت"));

        Pane root = new Pane(backHelpBTN);
        ImageView background = new ImageView("help.png");
        StackPane helpRoot = new StackPane(background, root);
        scene = new Scene(helpRoot);

        getBackHelpBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getBackHelpBTN().setGraphic(new ImageView("backMouseOnEnter.png"));
            }
        });
        getBackHelpBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getBackHelpBTN().setGraphic(new ImageView("backMouseOnExit.png"));
            }
        });
    }


    public Scene getScene() {
        return scene;
    }

    public Button getBackHelpBTN() {
        return backHelpBTN;
    }
}
