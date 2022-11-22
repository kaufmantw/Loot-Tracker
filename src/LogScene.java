import items.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LogScene extends SceneManager{
    
    public LogScene(SheetManager sm){
        super(sm);
    }

    public void logScene(Stage primaryStage){
        BorderPane bpane = new BorderPane();
        HBox hbox = new HBox(5);
        bpane.setBottom(hbox);

        Button btReturn = new Button("Return to main menu");

        // formality of the situation
        btReturn.setOnAction(e -> {
            startScene(primaryStage);
        });

        hbox.getChildren().addAll(btReturn);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(bpane, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }


}