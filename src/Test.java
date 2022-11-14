import items.*;

public class Test{
    public static void main(String [] args){
        SheetManager sm = new SheetManager();
        sm.printList();

        sm.items.add(new Dex(50, false, false));
        sm.items.add(new Olmlet( 49, false, false));
        sm.printList();
    }
}