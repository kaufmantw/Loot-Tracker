package items;
public class Dinh extends Loot{
    public static int count;
    public static final String NAME = "Dinh's Bulwark";
    
    public Dinh(int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        super(NAME, 23, kc, isPersonal, isSolo, isCM);
        count++;
    }
}