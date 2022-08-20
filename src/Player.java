import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.Socket;

public class Player {
    private Label nameLBL;
    private Label esmLBL, heivanLBL, shahrLBL, familLBL, ghazaLBL, keshvarLBL, pooshakLBL, miveLBL, mashinLBL, golLBL, ashiaLBL;
    private TextField esmTXF = new TextField()
            , heivanTXF = new TextField()
            , shahrTXF = new TextField()
            , familTXF = new TextField()
            , ghazaTXF = new TextField()
            , keshvarTXF = new TextField()
            , pooshakTXF = new TextField()
            , miveTXF = new TextField()
            , mashinTXF = new TextField()
            , golTXF = new TextField()
            , ashiaTXF = new TextField();
    public Scene scene;
    private Button finishBTN = new Button();
    private TextField letter = new TextField();
    private Label rondLBL, text, playerscoreLBL, computerscoreLBL;
    private Text timer;
    public Player() {
    }

    public Player(String fields, String name) {
        nameLBL = new Label( "نام بازیکن:" + name);
        nameLBL.setLayoutY(30);
        nameLBL.setLayoutX(20);
        nameLBL.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));

        VBox fieldsVBOX = new VBox();
        HBox ashiaHBOX, esmHBOX, familHBOX, ghazaHBOX, golHBOX, heivanHBOX, keshvarHBOX, mashinHBOX, miveHBOX, pooshakHBOX, shahrHBOX;
        if(fields.contains("a")) {
            ashiaLBL = new Label("اشیا:");
            ashiaHBOX = new HBox(ashiaTXF, ashiaLBL);
            ashiaHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(ashiaHBOX);
        }
        if(fields.contains("b")) {
            esmLBL = new Label("اسم:");
            esmHBOX = new HBox(esmTXF, esmLBL);
            esmHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(esmHBOX);
        }
        if(fields.contains("c")) {
            familLBL = new Label("فامیل:");
            familHBOX = new HBox(familTXF, familLBL);
            familHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(familHBOX);
        }
        if(fields.contains("d")) {
            ghazaLBL = new Label("غذا:");
            ghazaHBOX = new HBox(ghazaTXF, ghazaLBL);
            ghazaHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(ghazaHBOX);
        }
        if(fields.contains("e")) {
            golLBL = new Label("گل:");
            golHBOX = new HBox(golTXF, golLBL);
            golHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(golHBOX);
        }
        if(fields.contains("f")) {
            heivanLBL = new Label("حیوان:");
            heivanHBOX = new HBox(heivanTXF, heivanLBL);
            heivanHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(heivanHBOX);
        }
        if(fields.contains("g")) {
            keshvarLBL = new Label("کشور:");
            keshvarHBOX = new HBox(keshvarTXF, keshvarLBL);
            keshvarHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(keshvarHBOX);
        }
        if(fields.contains("h")) {
            mashinLBL = new Label("ماشین:");
            mashinHBOX = new HBox(mashinTXF, mashinLBL);
            mashinHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(mashinHBOX);
        }
        if(fields.contains("i")) {
            miveLBL = new Label("میوه:");
            miveHBOX = new HBox(miveTXF, miveLBL);
            miveHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(miveHBOX);
        }
        if(fields.contains("j")) {
            pooshakLBL = new Label("پوشاک:");
            pooshakHBOX = new HBox(pooshakTXF, pooshakLBL);
            pooshakHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(pooshakHBOX);
        }
        if(fields.contains("k")) {
            shahrLBL = new Label("شهر:");
            shahrHBOX = new HBox(shahrTXF, shahrLBL);
            shahrHBOX.setSpacing(5);
            fieldsVBOX.getChildren().add(shahrHBOX);
        }

        fieldsVBOX.setSpacing(10);
        fieldsVBOX.setLayoutX(1000);
        fieldsVBOX.setLayoutY(120);

        rondLBL = new Label();
        rondLBL.setLayoutX(20);
        rondLBL.setLayoutY(90);
//        rondLBL.setTextFill(Color.BLUE);
        rondLBL.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));


        finishBTN.setGraphic(new ImageView("nextMouseOnExit.png"));
        finishBTN.setPrefSize(70, 50);
        finishBTN.setStyle("-fx-background-color: #8A8CF5; ");
        finishBTN.setLayoutX(605);
        finishBTN.setLayoutY(600);
        finishBTN.setTooltip(new Tooltip("بعدی"));

        finishBTN.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                finishBTN.setGraphic(new ImageView("nextMouseOnEnter.png"));
            }
        });
        finishBTN.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                finishBTN.setGraphic(new ImageView("nextMouseOnExit.png"));
            }
        });

        text = new Label("حرف شروع کلمه:");
        text.setLayoutY(30);
        text.setLayoutX(1080);

        letter.setLayoutY(50);
        letter.setLayoutX(1000);

        playerscoreLBL = new Label();
        playerscoreLBL.setLayoutX(20);
        playerscoreLBL.setLayoutY(150);
//        playerscoreLBL.setTextFill(Color.GREEN);
        playerscoreLBL.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));

        computerscoreLBL = new Label();
        computerscoreLBL.setLayoutX(20);
        computerscoreLBL.setLayoutY(210);
//        computerscoreLBL.setTextFill(Color.RED);
        computerscoreLBL.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));

        timer = new Text();
        timer.setLayoutX(550);
        timer.setLayoutY(80);
        timer.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 25));

        ImageView imageView = new ImageView("background.png");
        Pane pane = new Pane(fieldsVBOX, finishBTN, letter, rondLBL, text, computerscoreLBL, playerscoreLBL, nameLBL, timer);

        StackPane stackPane = new StackPane(imageView, pane);
        scene = new Scene(stackPane, 1280, 720);

    }

    public Scene getScene() {
        return scene;
    }

    public TextField getEsmTXF() {
        return esmTXF;
    }

    public TextField getHeivanTXF() {
        return heivanTXF;
    }

    public TextField getShahrTXF() {
        return shahrTXF;
    }

    public TextField getFamilTXF() {
        return familTXF;
    }

    public TextField getGhazaTXF() {
        return ghazaTXF;
    }

    public TextField getKeshvarTXF() {
        return keshvarTXF;
    }

    public TextField getPooshakTXF() {
        return pooshakTXF;
    }

    public TextField getMiveTXF() {
        return miveTXF;
    }

    public TextField getMashinTXF() {
        return mashinTXF;
    }

    public TextField getGolTXF() {
        return golTXF;
    }

    public TextField getAshiaTXF() {
        return ashiaTXF;
    }

    public Button getFinishBTN() {
        return finishBTN;
    }

    public TextField getLetter() {
        return letter;
    }

    public Label getRondLBL() {
        return rondLBL;
    }

    public Label getPlayerscoreLBL() {
        return playerscoreLBL;
    }

    public Label getComputerscoreLBL() {
        return computerscoreLBL;
    }

    public Text getTimer() {
        return timer;
    }
}
