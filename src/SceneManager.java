import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

        Button btTest = new Button("Create Bow");
        TestHandlerClass testhandle = new TestHandlerClass();
        btTest.setOnAction(testhandle);
        btTest.setPadding(new Insets(5));

        Button btSwitch = new Button("Switch Scenes");
        //SwitchHandlerClass switchHandle = new SwitchHandlerClass(primaryStage, this, 1);
        btSwitch.setOnAction(e ->{
            submitScene(primaryStage);
        });
        btSwitch.setPadding(new Insets(5));

        Button btCompare = new Button("Compare");
        //SwitchHandlerClass compareHandle = new SwitchHandlerClass(primaryStage, this, 2);
        btCompare.setOnAction(e -> {
            compareScene(primaryStage);
        });
        btCompare.setPadding(new Insets(5));
        
        
        hbox.getChildren().addAll(btTest, btSwitch, btCompare);
        hbox.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(bpane,400,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

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

        Text t = new Text("You have: " + Twisted_Bow.count + " Twisted Bows");
        bpane.setCenter(t);

        Button btReturn = new Button("Return to main menu");
        //SwitchHandlerClass returnHandle = new SwitchHandlerClass(primaryStage, this, 0);
        btReturn.setOnAction(e -> {
            startScene(primaryStage);
        });
        bpane.setBottom(btReturn);
        bpane.setAlignment(btReturn, Pos.CENTER);

        Scene scene = new Scene(bpane, 400,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}