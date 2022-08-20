import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CreateScene {
    private StackPane mainRoot;
    private Scene scene;
    private TextField playerNameTXF;
    private Label alphabetLBL, rondLBL, topicsLBL, gameTypeLBL, timeLBL, playerNameLBL;
    private ComboBox alphabetCOMBO;
    private ComboBox rondCOMBO;
    private ComboBox gameTypeCOMBO;
    private CheckBox esmCH, heivanCh, familCH, shahrCH, keshvarCH, ghazaCH, pooshakCH, miveCH, mashinCH, golCH, ashiaCH;
    private Button confirmBTN = new Button(), backCreateBTN = new Button();
    private RadioButton time1 = new RadioButton("یک دقیقه"), time2 = new RadioButton("دو دقیقه"), time3 = new RadioButton("سه دقیقه");





    public CreateScene() {
        String[] alphabet = {"آ", "ب", "پ", "ت", "ث", "ج", "چ", "ح", "خ", "د", "ذ", "ر", "ز", "ژ", "س", "ش", "ص", "ض", "ط", "ظ","ع", "غ", "ف", "ق", "ک", "گ", "ل", "م", "ن", "و","ه", "ی"};
        String[] rondCount = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String[] playercount = {"1", "2", "3", "4", "5", "6"};
        String[] type = {"زماندار", "بدون زمان"};


        playerNameLBL = new Label("نام:");
        playerNameLBL.setLayoutX(1150);
        playerNameLBL.setLayoutY(30);

        playerNameTXF = new TextField();
        playerNameTXF.setLayoutX(1000);
        playerNameTXF.setLayoutY(30);
        playerNameTXF.setPrefWidth(110);

        rondLBL = new Label("تعداد دست بازی:");
        rondLBL.setLayoutX(1150);
        rondLBL.setLayoutY(80);

        rondCOMBO = new ComboBox(FXCollections.observableArrayList(rondCount));
        rondCOMBO.setLayoutX(1000);
        rondCOMBO.setLayoutY(80);
        rondCOMBO.setPrefWidth(110);


        alphabetLBL = new Label("شروع بازی با حرف:");
        alphabetLBL.setLayoutX(1150);
        alphabetLBL.setLayoutY(130);

        alphabetCOMBO = new ComboBox(FXCollections.observableArrayList(alphabet));
        alphabetCOMBO.setLayoutX(1000);
        alphabetCOMBO.setLayoutY(130);
        alphabetCOMBO.setPrefWidth(110);


        gameTypeLBL = new Label("نوع بازی:");
        gameTypeLBL.setLayoutX(1150);
        gameTypeLBL.setLayoutY(180);

        gameTypeCOMBO = new ComboBox(FXCollections.observableArrayList(type));
        gameTypeCOMBO.setLayoutY(180);
        gameTypeCOMBO.setLayoutX(1000);


        timeLBL = new Label("زمان(دقیقه):");
        timeLBL.setLayoutY(240);
        timeLBL.setLayoutX(1150);

        ToggleGroup group = new ToggleGroup();


        time1.setToggleGroup(group);
        time2.setToggleGroup(group);
        time3.setToggleGroup(group);

        time1.setLayoutY(240);
        time1.setLayoutX(1000);
        time2.setLayoutX(900);
        time2.setLayoutY(240);
        time3.setLayoutY(240);
        time3.setLayoutX(800);
        time1.setDisable(true);
        time2.setDisable(true);
        time3.setDisable(true);

        topicsLBL = new Label("موضوعات:");
        topicsLBL.setLayoutY(300);
        topicsLBL.setLayoutX(1190);

        esmCH = new CheckBox("اسم");
        heivanCh = new CheckBox("حیوان");
        shahrCH = new CheckBox("شهر");
        familCH = new CheckBox("فامیل");
        ghazaCH = new CheckBox("غذا");
        keshvarCH = new CheckBox("کشور");
        pooshakCH = new CheckBox("پوشاک");
        miveCH = new CheckBox("میوه");
        mashinCH = new CheckBox("ماشین");
        golCH = new CheckBox("گل");
        ashiaCH = new CheckBox("اشیا");


        confirmBTN.setGraphic(new ImageView("YesMouseOnExit.png"));
        confirmBTN.setPrefSize(70, 50);
        confirmBTN.setStyle("-fx-background-color: #8A8CF5; ");
        confirmBTN.setLayoutX(605);
        confirmBTN.setLayoutY(600);
        confirmBTN.setTooltip(new Tooltip("تایید"));

        backCreateBTN.setGraphic(new ImageView("backMouseOnExit.png"));
        backCreateBTN.setPrefSize(70, 50);
        backCreateBTN.setLayoutX(0);
        backCreateBTN.setLayoutY(0);
        backCreateBTN.setStyle("-fx-background-color: #1BC6F5; ");
        backCreateBTN.setTooltip(new Tooltip("بازگشت"));

        VBox topicsVBOX = new VBox();
        topicsVBOX.getChildren().addAll(esmCH, familCH, heivanCh, golCH, mashinCH, miveCH, pooshakCH, keshvarCH, ghazaCH, shahrCH, ashiaCH);
        topicsVBOX.setLayoutX(1150);
        topicsVBOX.setLayoutY(350);
        topicsVBOX.setSpacing(10);
        topicsVBOX.nodeOrientationProperty().set(NodeOrientation.RIGHT_TO_LEFT);

        ImageView imageView = new ImageView("background.png");
        Pane pane = new Pane(playerNameLBL, playerNameTXF, rondLBL, rondCOMBO, alphabetLBL, alphabetCOMBO, gameTypeLBL, gameTypeCOMBO, timeLBL, time1, time2, time3, topicsLBL, topicsVBOX, confirmBTN, backCreateBTN);
        StackPane stackPane = new StackPane(imageView, pane);

        scene = new Scene(stackPane, 1280, 720);



        getBackCreateBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getBackCreateBTN().setGraphic(new ImageView("backMouseOnEnter.png"));
            }
        });
        getBackCreateBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getBackCreateBTN().setGraphic(new ImageView("backMouseOnExit.png"));
            }
        });
        getConfirmBTN().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Main.buttonsPlaySound) {
                    Main.buttonsSoundPlayer.play();
                    Main.buttonsSoundPlayer.seek(Duration.ZERO);
                    Main.buttonsPlaySound = false;
                }
                getConfirmBTN().setGraphic(new ImageView("YesMouseOnEnter.png"));
            }
        });
        getConfirmBTN().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.buttonsPlaySound = true;
                getConfirmBTN().setGraphic(new ImageView("YesMouseOnExit.png"));
            }
        });
    }


    public Scene getScene() {
        return scene;
    }

    public ComboBox getGameTypeCOMBO() {
        return gameTypeCOMBO;
    }

    public RadioButton getTime1() {
        return time1;
    }

    public RadioButton getTime2() {
        return time2;
    }

    public RadioButton getTime3() {
        return time3;
    }

    public Button getBackCreateBTN() {
        return backCreateBTN;
    }

    public Button getConfirmBTN() {
        return confirmBTN;
    }

    public ComboBox getAlphabetCOMBO() {
        return alphabetCOMBO;
    }

    public ComboBox getRondCOMBO() {
        return rondCOMBO;
    }

    public CheckBox getEsmCH() {
        return esmCH;
    }

    public CheckBox getHeivanCh() {
        return heivanCh;
    }

    public CheckBox getFamilCH() {
        return familCH;
    }

    public CheckBox getShahrCH() {
        return shahrCH;
    }

    public CheckBox getKeshvarCH() {
        return keshvarCH;
    }

    public CheckBox getGhazaCH() {
        return ghazaCH;
    }

    public CheckBox getPooshakCH() {
        return pooshakCH;
    }

    public CheckBox getMiveCH() {
        return miveCH;
    }

    public CheckBox getMashinCH() {
        return mashinCH;
    }

    public CheckBox getGolCH() {
        return golCH;
    }

    public CheckBox getAshiaCH() {
        return ashiaCH;
    }

    public TextField getPlayerNameTXF() {
        return playerNameTXF;
    }
}
