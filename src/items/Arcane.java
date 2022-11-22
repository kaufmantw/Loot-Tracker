package items;
public class Arcane extends Loot{
    public static int count;
    public static final String NAME = "Arcane Prayer Scroll";
    
    public Arcane(int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        super(NAME, 3.45, kc, isPersonal, isSolo, isCM);
        count++;
    }
}