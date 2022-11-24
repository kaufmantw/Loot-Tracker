import items.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public LogScene(SheetManager sm) {
        super(sm);
    }

    public void logScene(Stage primaryStage) {
        BorderPane bpane = new BorderPane();

        HBox hbox = new HBox(5);
        bpane.setBottom(hbox);

        VBox logpane = new VBox(5);

        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setContent(logpane);

        VBox statspane = new VBox();
        int totalItems = sm.items.size();
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

        Label lblLastBow = new Label("Kc since last bow: " + kcSinceTbow());

        hbox.getChildren().addAll(btnReturn, lblLastBow);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(bpane, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public int kcSinceTbow() {
        Loot tempBow = new Twisted_Bow(0, false, false, false, "1999-11-22 15:53:03.6845859");
        for (Loot item : sm.items) {
            if ((item instanceof Twisted_Bow) &&
                    item.stamp.after(tempBow.stamp)) {
                tempBow = item;
            }
        }

        return sm.totalKc() - tempBow.getKc();

    }

}