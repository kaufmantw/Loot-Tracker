import items.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


//TODO: this looks pretty ugly right now haha.
//TODO: if we are to have statistical analsis (binomial probability), would it go here?
public class LogScene extends SceneManager{
    
    public LogScene(SheetManager sm){
        super(sm);
    }

    public void logScene(Stage primaryStage){
        BorderPane bpane = new BorderPane();

        HBox hbox = new HBox(5);
        bpane.setBottom(hbox);

        VBox logpane = new VBox(5);
        bpane.setRight(logpane);

        //this is a rough generation of the log
        for(Loot item : sm.items){
            Button btnRemove = new Button("Remove");
            Label lblItem = new Label(item.getName() + " KC: " + item.getKc() + " Date: " + item.getTime());
            btnRemove.setOnAction(e ->{
                sm.remove(item);
                logpane.getChildren().removeAll(btnRemove, lblItem);
            });
            logpane.getChildren().addAll(lblItem, btnRemove);
        }

        Button btnReturn = new Button("Return to main menu");
        // formality of the situation
        btnReturn.setOnAction(e -> {
            startScene(primaryStage);
        });

        Label lblLastBow = new Label("Kc since last bow: " + kcSinceTbow());

        hbox.getChildren().addAll(btnReturn, lblLastBow);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(bpane, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public int kcSinceTbow(){
        Loot tempBow = new Twisted_Bow(0,false,false,false,"1999-11-22 15:53:03.6845859");
        for(Loot item : sm.items){
            if ((item instanceof Twisted_Bow) &&
                item.stamp.after(tempBow.stamp)){
                    tempBow = item;
            }
        }

        return sm.totalKc() - tempBow.getKc();
        
    }


}