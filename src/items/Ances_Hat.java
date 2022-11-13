package items;
public class Ances_Hat extends Loot{
    public static int count;
    public static final String NAME = "Ancestral Hat";
    
    public Ances_Hat(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 23, kc, isPersonal, isSolo);
        count++;
    }
}