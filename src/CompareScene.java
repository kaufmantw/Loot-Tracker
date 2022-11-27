import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

import items.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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

    ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
    private int visibleGraph = 0;
    BarChart<String, Number> bc;
    PieChart pc;

    public CompareScene(SheetManager sm) {
        super(sm);
    }

    public void compareScene(Stage primaryStage) {

        // Initializing pane for the comparison scene
        BorderPane bpane = new BorderPane();

        // Setting up the chart to display comparisons
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Comparing Drops");
        bc.setAnimated(false);
        bc.setMinHeight(550);
        xAxis.setLabel("Item");
        yAxis.setLabel("Quantity");
        XYChart.Series series = new XYChart.Series();
        series.setName("The stuff");

        pc = new PieChart(pieChartList);
        pc.setTitle("Comparing Drops");
        pc.setMinHeight(550);
        pc.setLabelsVisible(true);

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

        vbox.getChildren().add(0, bc);
        vbox.getChildren().add(1, hbox);
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

        // pie chart button
        Button toggleGraphButton = new Button("Toggle Graph");

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
        options.add(toggleGraphButton, 1, 5);

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
        PieChart.Data[] pieChartData = new PieChart.Data[numItems];

        for (int i = 0; i < numItems; i++) {
            chartData[i] = new XYChart.Data<String, Integer>(itemNames[i] + " ", itemCounts[i]);
            pieChartData[i] = new PieChart.Data(itemNames[i] + " - " + itemCounts[i], itemCounts[i]);
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
                    pieChartList.add(pieChartData[currentItem]);
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
                pieChartList.removeAll(pieChartData);
            }
        });

        toggleGraphButton.setOnAction(e -> {
            toggleGraph();
            vbox.getChildren().remove(0);
            switch (visibleGraph) {
                case 0:
                    vbox.getChildren().add(0, bc);
                    break;
                case 1:
                    vbox.getChildren().add(0, pc);
                    break;
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

    public void toggleGraph() {
        visibleGraph++;
        if (visibleGraph > 1) {
            visibleGraph = 0;
        }
    }
}
