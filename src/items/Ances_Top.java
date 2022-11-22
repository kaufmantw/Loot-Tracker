package items;
public class Ances_Top extends Loot{
    public static int count;
    public static final String NAME = "Ancestral Robe Top";
    
    public Ances_Top(int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        super(NAME, 23, kc, isPersonal, isSolo, isCM);
        count++;
    }
}