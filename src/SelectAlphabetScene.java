import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Scanner;

public class SelectAlphabetScene {
     private Label alphabetLBL;
     private ComboBox<String> alphabetCOMBO;
     private Scene scene;
     private Button continueBTN = new Button();

     public SelectAlphabetScene(String[] used) {
         int[] a = {1570, 1575, 1576, 1662, 1578, 1579, 1580, 1670, 1581, 1582, 1583, 1584, 1585, 1586, 1688, 1587, 1588, 1589, 1590, 1591, 1592, 1593, 1594, 1601, 1602, 1705, 1711, 1604, 1605, 1606, 1607, 1608, 1740};

         int count = 0;
         String[] remains = new String[33];

         for(int i=  0; i < a.length; i++) {
             boolean flag = true;
             for(int j = 0; used[j] != null; j++) {
                 if(((char)a[i] + "").charAt(0) == used[j].charAt(0)) {
                     flag = false;
                     break;
                 }
             }
             if(flag) {
                 remains[count] = (char)a[i] + "";
                 count++;
             }
         }

         ArrayList<String> trueLetter = new ArrayList<>();
         for(int i=  0; i < count; i++) {
             trueLetter.add(remains[i]);
         }

         alphabetCOMBO = new ComboBox(FXCollections.observableArrayList(trueLetter));
         alphabetCOMBO.setPrefWidth(90);

         alphabetLBL = new Label("حرف شروع کلمه:");

         HBox h = new HBox(alphabetCOMBO, alphabetLBL);
         h.setSpacing(20);
         h.setAlignment(Pos.CENTER);
         h.setLayoutY(300);
         h.setPrefSize(1280, 50);

         continueBTN.setGraphic(new ImageView("YesMouseOnExit.png"));
         continueBTN.setPrefSize(70, 50);
         continueBTN.setLayoutY(600);
         continueBTN.setLayoutX(615);
         continueBTN.setStyle("-fx-background-color: #1BC6F5; ");
         continueBTN.setTooltip(new Tooltip("ادامه بازی"));


         continueBTN.setOnMouseEntered(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {
                 if (Main.buttonsPlaySound) {
                     Main.buttonsSoundPlayer.play();
                     Main.buttonsSoundPlayer.seek(Duration.ZERO);
                     Main.buttonsPlaySound = false;
                 }
                 continueBTN.setGraphic(new ImageView("YesMouseOnEnter.png"));
             }
         });
         continueBTN.setOnMouseExited(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {
                 Main.buttonsPlaySound = true;
                 continueBTN.setGraphic(new ImageView("YesMouseOnExit.png"));
             }
         });


         Pane pane = new Pane(continueBTN, h);
         ImageView imageView = new ImageView("background.png");
         StackPane stackPane = new StackPane(imageView, pane);
         scene = new Scene(stackPane, 1280, 720);

     }

    public ComboBox getAlphabetCOMBO() {
        return alphabetCOMBO;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getContinueBTN() {
        return continueBTN;
    }
}
