package items;
public class Buckler extends Loot{
    public static int count;
    public static final String NAME = "Twisted Buckler";
    
    public Buckler(int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        super(NAME, 17.25, kc, isPersonal, isSolo, isCM);
        count++;
    }

    public Buckler(int kc, boolean isPersonal, boolean isSolo, boolean isCM, String time){
        super(NAME, 17.25, kc, isPersonal, isSolo, isCM, time);
        count++;
    }
}   