import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TestHandlerClass implements EventHandler<ActionEvent> {
    private Loot obj;
    public TestHandlerClass(){

    }
    @Override
    public void handle(ActionEvent e){
        this.obj = new Twisted_Bow();
        System.out.println("The loot has a rate of: " + obj.getRate());
        System.out.println("There are now: " + Twisted_Bow.count + " Twisted Bows.");
        System.out.println();
    }
}