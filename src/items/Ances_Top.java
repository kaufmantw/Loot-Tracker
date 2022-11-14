package items;
public class Ances_Top extends Loot{
    public static int count;
    public static final String NAME = "Ancestral Robe Top";
    
    public Ances_Top(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 23, kc, isPersonal, isSolo);
        count++;
    }
}