import java.io.IOException;

import items.*;

public class Test{
    public static void main(String [] args)throws IOException{
        SheetManager sm = new SheetManager();

        sm.add(new Dust(35, false, false));
        sm.printList();
        //sm.emptyCopy();
        sm.save();
        //sm.emptyCopy();
    }
}