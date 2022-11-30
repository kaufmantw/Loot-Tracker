import java.math.BigDecimal;
import java.math.RoundingMode;

import items.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LogScene extends SceneManager {
    StatTracker stracker;
    Loot [] lootChoices = {
        new Dex(0, false, false, false),
        new Arcane(0, false, false, false),
        new Buckler(0, false, false, false),
        new DHCB(0, false, false, false),
        new Dinh(0, false, false, false),
        new Ances_Hat(0, false, false, false),
        new Ances_Top(0, false, false, false),
        new Ances_Bottom(0, false, false, false),
        new Claws(0, false, false, false),
        new Elder_Maul(0, false, false, false),
        new Kodai_Insignia(0, false, false, false),
        new Twisted_Bow(0, false, false, false),
        new Olmlet(0, false, false, false),
        new Dust(0, false, false, false)
    };
    int totalItems = sm.items.size();

    public LogScene(SheetManager sm) {
        super(sm);
        this.stracker = new StatTracker(sm.items);
    }

    public void logScene(Stage primaryStage) {
        BorderPane bpane = new BorderPane();

        HBox hbox = new HBox(5);
        bpane.setBottom(hbox);

        VBox logpane = new VBox(5);

        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setContent(logpane);

        VBox statspane = new VBox();
        Label headerForStats = new Label("Total Items Collected: " + totalItems);
        headerForStats.setFont(new Font(15));
        statspane.getChildren().add(headerForStats);
        for (int i = 0; i < numItems; i++) {
            Label itemName = new Label(itemNames[i] + ": ");
            int numItems = 0;
            for (Loot item : sm.items) {
                if (item.getName().equals(itemNames[i])) {
                    numItems++;
                }
            }
            Label numItemsObtained = new Label(numItems + " Obtained.");
            // Label numitemsExpected = new Label(totalItems * rate);
            statspane.getChildren().add(itemName);
            statspane.getChildren().add(numItemsObtained);
            // statspane.getChildren().add(numItemsExpected);

        }
        Insets moreinfo = new Insets(5,0,5,0);

        Label lblLastBow = new Label("Kc since last bow: " + stracker.kcSinceTbow());
        lblLastBow.setPadding(new Insets(35,0,5,0));
        Label lblNumPersonal = new Label("Total personal: " + stracker.totalPersonal());
        lblNumPersonal.setPadding(moreinfo);
        Label lblNumSolo = new Label("Total solo: " + stracker.totalSolo());
        lblNumSolo.setPadding(moreinfo);

        Label lblTestProb = new Label("P(X = n) = 0%");
        lblTestProb.setPadding(moreinfo);

        Label lblTestAbove = new Label("P(X > n) = 0%");
        lblTestAbove.setPadding(moreinfo);

        Label lblTestBelow = new Label("P(X < n) = 0%");
        lblTestBelow.setPadding(moreinfo);
        //the following section is for computing probability and formatting it
        //in a more readable way.
        //combo box shenanigans

        //TODO: Issue with removing items from list and messing up statistics
        ChoiceBox<String> cbox = new ChoiceBox<>(FXCollections.observableArrayList(itemNames));
        cbox.setValue(itemNames[0]);
        cbox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){

            public void changed(ObservableValue ov, Number value, Number new_value){
                boolean found = false;
                String newName = itemNames[new_value.intValue()];
                for(Loot item : lootChoices){
                    if(item.getName().equals(newName)){
                        int numOfItem = stracker.numOfItem(item);
                        System.out.println("num of items: " + numOfItem);

                        double equalsProb = (stracker.binomProb(totalItems, numOfItem, item.getRate())) * 100;
                        BigDecimal equalsDec = new BigDecimal(Double.toString(equalsProb));
                        equalsDec = equalsDec.setScale(2, RoundingMode.HALF_UP);
                        lblTestProb.setText("P(X = "+ numOfItem + ") = " + equalsDec + "%");

                        double aboveProb = (stracker.binomAbove(totalItems, numOfItem, item.getRate())) * 100;
                        BigDecimal aboveDec = new BigDecimal(Double.toString(aboveProb));
                        aboveDec = aboveDec.setScale(2, RoundingMode.HALF_UP);
                        lblTestAbove.setText("P(X > "+ numOfItem + ") = " + aboveDec + "%");

                        double belowProb = (stracker.binomBelow(totalItems, numOfItem, item.getRate())) * 100;
                        BigDecimal belowDec = new BigDecimal(Double.toString(belowProb));
                        belowDec = belowDec.setScale(2, RoundingMode.HALF_UP);
                        lblTestBelow.setText("P(X < "+ numOfItem + ") = " + belowDec + "%");
                        found = true;
                        break;
                    }
                }
                if (!found){
                    System.out.println("This shouldn't happen.");
                    lblTestProb.setText("P(X = n) = 0%");
                    lblTestAbove.setText("P(X > n) = 0%");
                    lblTestBelow.setText("P(X < n) = 0%");
                }
            }

        });
        statspane.getChildren().addAll(lblLastBow, lblNumPersonal, lblNumSolo, lblTestProb,
                                        lblTestAbove, lblTestBelow,cbox);
        HBox mainHBox = new HBox(2);
        mainHBox.getChildren().add(scrollpane);
        mainHBox.getChildren().add(statspane);
        bpane.setCenter(mainHBox);

        // this is a rough generation of the log
        for (Loot item : sm.items) {
            // hboxes and vboxes for each log entry
            HBox logEntry = new HBox();
            VBox buttonAndIcon = new VBox();
            VBox labelEntries = new VBox();
            labelEntries.setMaxWidth(325);
            GridPane gpane = new GridPane();
            // generating removal button and labels for item
            Button btnRemove = new Button("Remove");
            Label lblItemName = new Label(item.getName());
            Label lblStats = new Label(item.getName() + " KC: " + item.getKc() + " Date: " + item.getTime());
            // lblStats.setMaxWidth(350);
            Label lblCheckboxes = new Label(
                    "Challenge Mode: " + item.isCM() + "   Solo: " + item.isSolo() + "   Personal: "
                            + item.isPersonal());

            // generating image for log entry
            int logImageIndex = 0;
            for (int i = 0; i < numItems; i++) {
                if (item.getName() == itemNames[i]) {
                    logImageIndex = i;
                }
            }
            ImageView imageView = new ImageView(
                    new Image(precedingPath.concat(imagePaths[logImageIndex].concat(fileExtension))));

            // formatting vboxes and hboxes for log entries
            buttonAndIcon.getChildren().add(imageView);
            buttonAndIcon.getChildren().add(btnRemove);
            labelEntries.getChildren().add(lblItemName);
            labelEntries.getChildren().add(lblStats);
            labelEntries.getChildren().add(lblCheckboxes);
            logEntry.getChildren().add(buttonAndIcon);
            logEntry.getChildren().add(labelEntries);
            // removing item from log when requested
            btnRemove.setOnAction(e -> {
                sm.remove(item);
                totalItems = sm.items.size();
                logpane.getChildren().removeAll(logEntry);
            });

            // adding items to log
            logpane.getChildren().addAll(logEntry);
        }

        Button btnReturn = new Button("Return to main menu");
        // formality of the situation
        btnReturn.setOnAction(e -> {
            startScene(primaryStage);
        });

        hbox.getChildren().addAll(btnReturn);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(bpane, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }
}