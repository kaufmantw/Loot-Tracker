import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import items.*;

public class TestHandlerClass implements EventHandler<ActionEvent> {
    private Loot obj;
    public TestHandlerClass(){

    }
    //not currently implemented, lambdas replaced my sweet child.
    @Override
    public void handle(ActionEvent e){
        this.obj = new Twisted_Bow();
        System.out.println("The loot has a rate of: " + obj.getRate());
        System.out.println("There are now: " + Twisted_Bow.count + " Twisted Bows.");
        System.out.println();
    }
}