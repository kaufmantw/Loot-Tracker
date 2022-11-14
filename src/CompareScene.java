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

public class CompareScene extends SceneManager {
    SheetManager sm;

    public CompareScene(SheetManager sm){
        this.sm = sm;
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

        String[] itemNames = new String[] { Dex.NAME, Arcane.NAME, Buckler.NAME, DHCB.NAME, Dinh.NAME, Ances_Hat.NAME,
                Ances_Top.NAME, Ances_Bottom.NAME, Claws.NAME, Elder_Maul.NAME, Kodai_Insignia.NAME,
                Twisted_Bow.NAME, Olmlet.NAME, Dust.NAME };

        XYChart.Data[] chartData = new XYChart.Data[numItems];

        for (int i = 0; i < numItems; i++) {
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
}
