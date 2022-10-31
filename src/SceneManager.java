import javafx.scene.text.Text;
import items.Kodai_Insignia;
import items.Twisted_Bow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class SceneManager {
    public SceneManager(){

    }

    public void startScene(Stage primaryStage){
        BorderPane bpane = new BorderPane();
        Text welcome = new Text("Welcome to the loot tracker!");
        bpane.setTop(welcome);
        bpane.setAlignment(welcome, Pos.CENTER);

        HBox hbox = new HBox(5);
        bpane.setBottom(hbox);

        Button btBow = new Button("Create Bow");
        TestHandlerClass testhandle = new TestHandlerClass();
        btBow.setOnAction(e -> {
            Twisted_Bow k = new Twisted_Bow();
            System.out.println("The loot has a rate of: " + k.getRate());
            System.out.println("There are now: " + Twisted_Bow.count + " Twisted Bows.");
            System.out.println();
        });
        btBow.setPadding(new Insets(5));

        Button btKodai = new Button("Create Kodai");
        //SwitchHandlerClass switchHandle = new SwitchHandlerClass(primaryStage, this, 1);
        btKodai.setOnAction(e ->{
            Kodai_Insignia k = new Kodai_Insignia();
            System.out.println("The loot has a rate of: " + k.getRate());
            System.out.println("There are now: " + Kodai_Insignia.count + " Kodai Insignias.");
            System.out.println();
        });
        btKodai.setPadding(new Insets(5));

        Button btCompare = new Button("Compare");
        //SwitchHandlerClass compareHandle = new SwitchHandlerClass(primaryStage, this, 2);
        btCompare.setOnAction(e -> {
            compareScene(primaryStage);
        });
        btCompare.setPadding(new Insets(5));
        
        
        hbox.getChildren().addAll(btBow, btKodai, btCompare);
        hbox.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(bpane,400,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    //submit scene currently not implemented.
    public void submitScene(Stage primaryStage){
        BorderPane bpane = new BorderPane();
        HBox hbox = new HBox();

        Text t = new Text("This is the new scene!");
        bpane.setCenter(t);

        Button btReturn = new Button("Return to main menu");
        //SwitchHandlerClass returnHandle = new SwitchHandlerClass(primaryStage, this, 0);
        btReturn.setOnAction(e ->{
            startScene(primaryStage);
        });
        bpane.setBottom(btReturn);
        bpane.setAlignment(btReturn, Pos.CENTER);

        Scene scene = new Scene(bpane, 400,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void compareScene(Stage primaryStage){
        BorderPane bpane = new BorderPane();

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

        Button btReturn = new Button("Return to main menu");
        //SwitchHandlerClass returnHandle = new SwitchHandlerClass(primaryStage, this, 0);
        btReturn.setOnAction(e -> {
            startScene(primaryStage);
        });
        bpane.setBottom(btReturn);
        bpane.setAlignment(btReturn, Pos.CENTER);
        bpane.setCenter(bc);
        bc.getData().add(series);

        Scene scene = new Scene(bpane, 400,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}