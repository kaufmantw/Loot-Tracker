import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

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

public class SceneManager {
    String[] itemNames = new String[] { Dex.NAME, Arcane.NAME, Buckler.NAME, DHCB.NAME, Dinh.NAME, Ances_Hat.NAME,
            Ances_Top.NAME, Ances_Bottom.NAME, Claws.NAME, Elder_Maul.NAME, Kodai_Insignia.NAME,
            Twisted_Bow.NAME, Olmlet.NAME, Dust.NAME };

    // strings to initialize images
    String precedingPath = "\\images\\";
    String[] imagePaths = new String[] { "dps", "aps", "tb", "dhc", "db", "ah", "art", "arb", "dc", "em", "ki",
            "tbow", "o", "md" };
    String fileExtension = ".png";

    // images array
    Image[] images;

    Button[] itemButtons = new Button[imagePaths.length];
    Button selected;

    int numItems = 14;

    SubmitScene sscene;
    CompareScene cscene;
    LogScene lscene;

    SheetManager sm;

    public SceneManager(SheetManager sm) {
        this.sm = sm;
    }

    public void startScene(Stage primaryStage) {
        // Initial Pane Setup
        BorderPane bpane = new BorderPane();

        HBox hbox = new HBox(5);
        bpane.setBottom(hbox);

        HBox topbox = new HBox(5);
        bpane.setTop(topbox);

        StackPane spane = new StackPane();
        bpane.setCenter(spane);

        Button btnSave = new Button("Save");
        btnSave.setOnAction(e -> {
            try {
                this.sm.save();
            } catch (IOException f) {
                f.getMessage();
            }
        });
        btnSave.setPadding(new Insets(5));

        Button btnList = new Button("Print List");
        btnList.setOnAction(e -> {
            this.sm.printList();
        });
        btnList.setPadding(new Insets(5));

        Button btnCount = new Button("Print Counts");
        btnCount.setOnAction(e -> {
            this.sm.printCounts();
        });
        btnCount.setPadding(new Insets(5));

        cscene = new CompareScene(sm);

        // Button to swap to the comparison stage
        Button btCompare = new Button("Compare");
        // SwitchHandlerClass compareHandle = new SwitchHandlerClass(primaryStage, this,
        // 2);
        btCompare.setOnAction(e -> {
            cscene.compareScene(primaryStage);
        });
        btCompare.setPadding(new Insets(5));

        sscene = new SubmitScene(sm);

        Button btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> {
            sscene.submitScene(primaryStage);
        });
        btSubmit.setPadding(new Insets(5));

        lscene = new LogScene(sm);

        Button btLog = new Button("Log");
        btLog.setOnAction(e -> {
            lscene.logScene(primaryStage);
        });
        btLog.setPadding(new Insets(5));

        Image scroll = new Image("\\images\\osrsScrollfin.PNG");
        ImageView view = new ImageView(scroll);
        Font font = Font.loadFont(
                "file:bin\\fonts\\runescape_uf.ttf",
                25);
        Text welcome = new Text("Welcome to the loot tracker!");
        welcome.setFont(font);
        spane.getChildren().addAll(view, welcome);

        // Adding objects to scene and displaying scene
        hbox.getChildren().addAll(btnCount, btnList, btCompare, btSubmit, btLog);
        hbox.setAlignment(Pos.CENTER);
        topbox.getChildren().addAll(btnSave);
        topbox.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(bpane, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    // submit scene currently not implemented.

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
                    // precedingPath + imagePaths[count] + fileExtension
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