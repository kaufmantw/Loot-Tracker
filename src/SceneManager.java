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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneManager {

    // strings to initialize images
    String precedingPath = "C:\\Users\\justa\\Documents\\SFA_CLASSWORK\\CSCI_3331_-_Object_Oriented_Programming_Methods\\Loot-Tracker\\bin\\images\\";
    String[] imagePaths = new String[] { "dps", "aps", "tb", "dhc", "db", "ah", "art", "arb", "dc", "em", "ki",
            "tbow", "o", "md" };
    String fileExtension = ".png";

    // images array
    Image[] images;

    Button[] itemButtons = new Button[imagePaths.length];
    Button selected;

    int numItems = 14;

    int iterator = 0;

    public SceneManager() {

    }

    public void startScene(Stage primaryStage) {
        // Initial Pane Setup
        BorderPane bpane = new BorderPane();
        Text welcome = new Text("Welcome to the loot tracker!");
        bpane.setTop(welcome);
        bpane.setAlignment(welcome, Pos.CENTER);

        HBox hbox = new HBox(5);
        bpane.setBottom(hbox);

        Button btBow = new Button("Create Bow");
        TestHandlerClass testhandle = new TestHandlerClass();

        // Buttons interactions to add objects
        btBow.setOnAction(e -> {
            Twisted_Bow k = new Twisted_Bow();
            System.out.println("The loot has a rate of: " + k.getRate());
            System.out.println("There are now: " + Twisted_Bow.count + " Twisted Bows.");
            System.out.println();
        });
        btBow.setPadding(new Insets(5));

        Button btKodai = new Button("Create Kodai");
        // SwitchHandlerClass switchHandle = new SwitchHandlerClass(primaryStage, this,
        // 1);
        btKodai.setOnAction(e -> {
            Kodai_Insignia k = new Kodai_Insignia();
            System.out.println("The loot has a rate of: " + k.getRate());
            System.out.println("There are now: " + Kodai_Insignia.count + " Kodai Insignias.");
            System.out.println();
        });
        btKodai.setPadding(new Insets(5));

        // Button to swap to the comparison stage
        Button btCompare = new Button("Compare");
        // SwitchHandlerClass compareHandle = new SwitchHandlerClass(primaryStage, this,
        // 2);
        btCompare.setOnAction(e -> {
            compareScene(primaryStage);
        });
        btCompare.setPadding(new Insets(5));

        Button btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> {
            submitScene(primaryStage);
        });
        btSubmit.setPadding(new Insets(5));

        // Adding objects to scene and displaying scene
        hbox.getChildren().addAll(btBow, btKodai, btCompare, btSubmit);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(bpane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    // submit scene currently not implemented.
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
        GridPane gpane = new GridPane();
        gpane.setTranslateX(10);
        gpane.setTranslateY(10);
        gpane.setHgap(10);
        gpane.setVgap(10);

        // Looping to actually create the buttons
        int count = 0;
        bpane.setLeft(gpane);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                if (count < numItems) {
                    // Image Creation
                    Image image = new Image(precedingPath + imagePaths[count] + fileExtension);
                    ImageView imageView = new ImageView(image);

                    // Button Formatting
                    Button button = new Button();
                    button.setMinHeight(50);
                    button.setMinWidth(50);
                    button.setGraphic(imageView);
                    button.setAlignment(Pos.CENTER);

                    itemButtons[count] = button;

                    // button.setTranslateX(100 + (j * 50));
                    // button.setTranslateY(100 + (i * 100));

                    gpane.add(button, j, i);

                    count++;
                }

            }
        }
        count = 0;

        /*
         * for (iterator = 0; iterator < numItems; iterator++) {
         * itemButtons[iterator].setOnAction(e -> {
         * System.out.print(iterator);
         * Loot loot;
         * switch (iterator) {
         * case 0:
         * loot = new Ances_Bottom();
         * break;
         * case 1:
         * loot = new Ances_Hat();
         * break;
         * case 2:
         * loot = new Ances_Top();
         * break;
         * case 3:
         * loot = new Arcane();
         * break;
         * case 4:
         * loot = new Buckler();
         * break;
         * case 5:
         * loot = new Claws();
         * break;
         * case 6:
         * loot = new Dex();
         * break;
         * case 7:
         * loot = new DHCB();
         * break;
         * case 8:
         * loot = new Dinh();
         * break;
         * case 9:
         * loot = new Elder_Maul();
         * break;
         * case 10:
         * loot = new Kodai_Insignia();
         * break;
         * case 11:
         * loot = new Twisted_Bow();
         * break;
         * case 12:
         * loot = new Twisted_Bow();
         * break;
         * case 13:
         * loot = new Twisted_Bow();
         * break;
         * case 14:
         * loot = new Twisted_Bow();
         * break;
         * 
         * }
         * 
         * });
         * }
         */

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

        Button btReturn = new Button("Return to main menu");
        // SwitchHandlerClass returnHandle = new SwitchHandlerClass(primaryStage, this,
        // 0);
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

    public void compareScene(Stage primaryStage) {

        // Initializing pane for the comparison scene
        BorderPane bpane = new BorderPane();

        // Setting up the chart to display comparisons
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Comparing Drops");
        xAxis.setLabel("Item");
        yAxis.setLabel("Quantity");
        XYChart.Series series = new XYChart.Series();
        series.setName("The stuff");
        series.getData().add(new XYChart.Data("Twisted Bow", Twisted_Bow.count));
        series.getData().add(new XYChart.Data("Kodai Insignia", Kodai_Insignia.count));

        // Returning to main menu button

        Button btReturn = new Button("Return to main menu");
        // SwitchHandlerClass returnHandle = new SwitchHandlerClass(primaryStage, this,
        // 0);
        btReturn.setOnAction(e -> {
            startScene(primaryStage);
        });
        bpane.setBottom(btReturn);
        bpane.setAlignment(btReturn, Pos.CENTER);
        bpane.setCenter(bc);
        bc.getData().add(series);

        // Adding objects to scene and displaying scene
        Scene scene = new Scene(bpane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}