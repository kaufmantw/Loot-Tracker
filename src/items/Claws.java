package items;
public class Claws extends Loot{
    public static int count;
    public static final String NAME = "Dragon Claws";
    
    public Claws(int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        super(NAME, 23, kc, isPersonal, isSolo, isCM);
        count++;
    }
}