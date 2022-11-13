package items;
public class Claws extends Loot{
    public static int count;
    public static final String NAME = "Dragon Claws";
    
    public Claws(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 23, kc, isPersonal, isSolo);
        count++;
    }
}