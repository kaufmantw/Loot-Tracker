import java.io.IOException;

import items.*;

public class Test{
    public static void main(String [] args)throws IOException{
        SheetManager sm = new SheetManager();

        //sm.add(new Claws(35, false, false));
        //sm.add(new Twisted_Bow(73, false, false));
        //sm.printList();
        //sm.emptyCopy();
        //sm.save();
        //sm.emptyCopy();

        //tests for returnCount
        int count = sm.returnCount(0, 10000, false, false, false);
        System.out.println("The first set of items has " + count + " entries.");

        count = sm.returnCount(36, 100000, false, false, false);
        System.out.println("The second set of items has " + count + " entries.");

        count = sm.returnCount(0, 106, false, false, false);
        System.out.println("The third set of items has " + count  + " entries.");

        count = sm.returnCount(93, 108, false, false, false);
        System.out.println("The fourth set of items has " + count + " entries");

        count = sm.returnCount(0, 100000, false, true, true);
        System.out.println("The fifth set of items has " + count + " entries.");

    }
}