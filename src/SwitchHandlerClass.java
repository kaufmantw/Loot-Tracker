import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SwitchHandlerClass implements EventHandler<ActionEvent> {
    private Stage stage;
    private SceneManager manage;
    private int cases;

    public SwitchHandlerClass(Stage primaryStage, SceneManager smanage, int cases){
        this.stage = primaryStage;
        this.manage = smanage;
        this.cases = cases;
    }
    @Override
    public void handle(ActionEvent e){
        if(cases == 0){
            manage.startScene(stage);
        }
        if(cases == 1){
            manage.submitScene(stage);
        }
        if(cases == 2){
            manage.compareScene(stage);
        }

    }
}