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
    String precedingPath = "C:\\Users\\timka\\Documents\\code\\java\\OOP_Coursework\\Loot-Tracker\\bin\\images\\";
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
        GridPane gpane = this.initializeButtonGrid(7, 2);
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
            Loot loot;
            for (int i = 0; i < itemButtons.length; i++) {
                if (itemButtons[i] == selected) {
                    currentItem = i;
                }
            }
            switch (currentItem) {
                case 0:
                    loot = new Dex();
                    break;
                case 1:
                    loot = new Arcane();
                    break;
                case 2:
                    loot = new Buckler();
                    break;
                case 3:
                    loot = new DHCB();
                    break;
                case 4:
                    loot = new Dinh();
                    break;
                case 5:
                    loot = new Ances_Hat();
                    break;
                case 6:
                    loot = new Ances_Top();
                    break;
                case 7:
                    loot = new Ances_Bottom();
                    break;
                case 8:
                    loot = new Claws();
                    break;
                case 9:
                    loot = new Elder_Maul();
                    break;
                case 10:
                    loot = new Kodai_Insignia();
                    break;
                case 11:
                    loot = new Twisted_Bow();
                    break;
                case 12:
                    loot = new Olmlet();
                    break;
                case 13:
                    loot = new Dust();
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

    public void compareScene(Stage primaryStage) {

        // Initializing pane for the comparison scene
        BorderPane bpane = new BorderPane();

        // Setting up the chart to display comparisons
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Comparing Drops");
        bc.setAnimated(false);
        bc.setMinHeight(550);
        xAxis.setLabel("Item");
        yAxis.setLabel("Quantity");
        XYChart.Series series = new XYChart.Series();
        series.setName("The stuff");
        // series.getData().add(new XYChart.Data("Twisted Bow", Twisted_Bow.count));
        // series.getData().add(new XYChart.Data("Kodai Insignia",
        // Kodai_Insignia.count));

        // Returning to main menu button
        Button btReturn = new Button("Return to main menu");
        btReturn.setOnAction(e -> {
            startScene(primaryStage);
        });

        // Initializing Button Grid
        GridPane gpane = this.initializeButtonGrid(3, 5);
        gpane.setTranslateX(10);
        gpane.setTranslateY(10);
        gpane.setHgap(10);
        gpane.setVgap(10);

        // Initializing real middle layout (vboxception)
        VBox vbox = new VBox();
        HBox hbox = new HBox();

        GridPane options = new GridPane();
        options.setTranslateX(50);
        gpane.setTranslateY(10);
        options.setHgap(10);
        options.setVgap(10);

        vbox.getChildren().add(bc);
        vbox.getChildren().add(hbox);
        hbox.getChildren().add(gpane);
        hbox.getChildren().add(options);

        // setting up options for chart
        TextField killCountMin = new TextField();
        TextField killCountMax = new TextField();
        killCountMin.setMaxWidth(75);
        killCountMax.setMaxWidth(75);

        // checkboxes
        CheckBox challengeBox = new CheckBox();
        CheckBox soloBox = new CheckBox();
        CheckBox personalBox = new CheckBox();

        // submit button
        Button clearButton = new Button("Clear Selections");

        // corresponding labels
        Label killCountLabel = new Label("Kill Count Range");
        Label challengeLabel = new Label("Challenge Mode");
        Label soloLabel = new Label("Obtained Solo");
        Label personalLabel = new Label("Personally Obtained");

        options.add(killCountLabel, 0, 0);
        options.add(killCountMin, 0, 1);
        options.add(killCountMax, 1, 1);
        options.add(challengeLabel, 0, 2);
        options.add(challengeBox, 1, 2);
        options.add(soloLabel, 0, 3);
        options.add(soloBox, 1, 3);
        options.add(personalLabel, 0, 4);
        options.add(personalBox, 1, 4);
        options.add(clearButton, 0, 5);

        // active item storage for comparing
        boolean[] activeItems = new boolean[numItems];

        // initializing the item counts corresponding to the buttons.
        int[] itemCounts = new int[] { Dex.count, Arcane.count, Buckler.count, DHCB.count, Dinh.count, Ances_Hat.count,
                Ances_Top.count, Ances_Bottom.count, Claws.count, Elder_Maul.count, Kodai_Insignia.count,
                Twisted_Bow.count, Olmlet.count, Dust.count };

        String[] itemNames = new String[]{Dex.NAME, Arcane.NAME, Buckler.NAME, DHCB.NAME, Dinh.NAME, Ances_Hat.NAME,
                Ances_Top.NAME, Ances_Bottom.NAME, Claws.NAME, Elder_Maul.NAME, Kodai_Insignia.NAME,
                Twisted_Bow.NAME, Olmlet.NAME, Dust.NAME};

        XYChart.Data[] chartData = new XYChart.Data[numItems];

        for (int i = 0; i < numItems; i++) {
            //TODO: change item field to be more dynamic.
            chartData[i] = new XYChart.Data<String, Integer>(itemNames[i] + " ", itemCounts[i]);
        }

        // Adding item to the chart
        for (int i = 0; i < numItems; i++) {
            int currentItem = i;
            itemButtons[i].setOnAction(e -> {
                if (activeItems[currentItem] == false) {
                    System.out.println(currentItem);
                    activeItems[currentItem] = true;
                    itemButtons[currentItem].setDisable(true);
                    series.getData().add(chartData[currentItem]);
                    for (int j = 0; j < activeItems.length; j++) {
                        System.out.print(activeItems[j]);
                    }
                }
            });
        }

        // clearing chart and resetting buttons
        clearButton.setOnAction(e -> {
            for (int i = 0; i < numItems; i++) {
                itemButtons[i].setDisable(false);
                activeItems[i] = false;
                series.getData().removeAll(chartData[i]);
            }
        });

        bpane.setCenter(vbox);

        bpane.setBottom(btReturn);
        bpane.setAlignment(btReturn, Pos.CENTER);
        bc.getData().add(series);

        // Adding objects to scene and displaying scene
        Scene scene = new Scene(bpane, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    // Method that takes grid size parameters and returns the corresponding button
    // grid
    public GridPane initializeButtonGrid(int length, int width) {

        GridPane gpane = new GridPane();

        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (count < numItems) {
                    // Image Creation
                    String temp = precedingPath.concat(imagePaths[count].concat(fileExtension));
                    Image image = new Image(temp);
                    //precedingPath + imagePaths[count] + fileExtension
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
        return gpane;

    }
}