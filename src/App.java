import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        SheetManager sm = new SheetManager();
        SceneManager smanage = new SceneManager(sm);
        smanage.startScene(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}