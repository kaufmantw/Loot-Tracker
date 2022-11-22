import java.io.IOException;

import items.*;

public class Test{
    public static void main(String [] args)throws IOException{
        SheetManager sm = new SheetManager();

        sm.add(new Claws(35, false, false));
        sm.add(new Twisted_Bow(73, false, false));
        sm.printList();
        //sm.emptyCopy();
        sm.save();
        //sm.emptyCopy();
    }
}