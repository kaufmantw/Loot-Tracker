package items;
public class Ances_Bottom extends Loot{
    public static int count;
    public static final String NAME = "Ancestral Robe Bottoms";
    
    public Ances_Bottom(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 23, kc, isPersonal, isSolo);
        count++;
    }
}