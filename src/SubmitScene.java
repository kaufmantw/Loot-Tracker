import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import items.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SubmitScene extends SceneManager { 
    SheetManager sm;

    public SubmitScene(SheetManager sm) {
        this.sm = sm;
    }

    public void submitScene(Stage primaryStage) {
        BorderPane bpane = new BorderPane();
        HBox hbox = new HBox();

        // Instruction label formatting
        Label instructions = new Label(
                "Instructions: Select which item you wish to add and input the kill count it was obtained at. Toggle the checkboxes to correspond to the conditions that you obtained your loot in.");
        instructions.setWrapText(true);
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setTranslateX(10);
        instructions.setMaxWidth(580);
        bpane.setTop(instructions);

        // Setting up the options in the middle of the frame;
        VBox vbox = new VBox(25);
        vbox.setTranslateY(220);

        GridPane centerGridPane = new GridPane();

        centerGridPane.setTranslateX(50);

        centerGridPane.setHgap(25);
        centerGridPane.setVgap(25);

        // kill count field
        TextField killCountField = new TextField();
        killCountField.setMaxWidth(100);

        // checkboxes
        CheckBox challengeBox = new CheckBox();
        CheckBox soloBox = new CheckBox();
        CheckBox personalBox = new CheckBox();

        // submit button
        Button submitButton = new Button("Submit");
        submitButton.setTranslateX(182);
        submitButton.setMinWidth(100);

        // corresponding labels
        Label killCountLabel = new Label("Kill Count Obtained");
        Label challengeLabel = new Label("Challenge Mode");
        Label soloLabel = new Label("Obtained Solo");
        Label personalLabel = new Label("Personally Obtained");

        centerGridPane.add(killCountLabel, 0, 0);
        centerGridPane.add(killCountField, 1, 0);
        centerGridPane.add(challengeLabel, 0, 1);
        centerGridPane.add(challengeBox, 1, 1);
        centerGridPane.add(soloLabel, 0, 2);
        centerGridPane.add(soloBox, 1, 2);
        centerGridPane.add(personalLabel, 0, 3);
        centerGridPane.add(personalBox, 1, 3);
        // centerGridPane.add(submitButton, 1, 4);

        vbox.getChildren().add(0, centerGridPane);
        vbox.getChildren().add(1, submitButton);
        bpane.setCenter(vbox);

        // Setting up the grid of buttons
        GridPane gpane = super.initializeButtonGrid(7, 2);
        gpane.setTranslateX(10);
        gpane.setTranslateY(10);
        gpane.setHgap(10);
        gpane.setVgap(10);

        // Looping to actually create the buttons

        bpane.setLeft(gpane);

        // Disabling the currently selected button
        for (int i = 0; i < itemButtons.length; i++) {
            Button active = itemButtons[i];
            active.setOnAction(e -> {
                if (selected != null) {
                    active.setDisable(true);
                    selected.setDisable(false);
                    selected = active;
                    killCountField.requestFocus();
                } else {
                    active.setDisable(true);
                    selected = active;
                    killCountField.requestFocus();
                }

            });
        }

        submitButton.setOnAction(e -> {
            // determining which item index is active
            int currentItem = 0;
            for (int i = 0; i < itemButtons.length; i++) {
                if (itemButtons[i] == selected) {
                    currentItem = i;
                }
            }
            switch (currentItem) {
                case 0:
                    sm.items.add(new Dex(Integer.parseInt(killCountField.getText()), 
                                    personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 1:
                    sm.items.add(new Arcane(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 2:
                    sm.items.add(new Buckler(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 3:
                    sm.items.add(new DHCB(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 4:
                    sm.items.add(new Dinh(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 5:
                    sm.items.add(new Ances_Hat(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 6:
                    sm.items.add(new Ances_Top(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 7:
                    sm.items.add(new Ances_Bottom(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 8:
                    sm.items.add(new Claws(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 9:
                    sm.items.add(new Elder_Maul(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 10:
                    sm.items.add(new Kodai_Insignia(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 11:
                    sm.items.add(new Twisted_Bow(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 12:
                    sm.items.add(new Olmlet(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;
                case 13:
                    sm.items.add(new Dust(Integer.parseInt(killCountField.getText()), 
                                        personalBox.selectedProperty().getValue(), soloBox.selectedProperty().getValue()));
                    break;

            }
        });

        Button btReturn = new Button("Return to main menu");

        // formality of the situation
        btReturn.setOnAction(e -> {
            startScene(primaryStage);
        });
        bpane.setBottom(btReturn);
        bpane.setAlignment(btReturn, Pos.CENTER);

        Scene scene = new Scene(bpane, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}