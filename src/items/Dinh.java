package items;
public class Dinh extends Loot{
    public static int count;
    public static final String NAME = "Dinh's Bulwark";
    
    public Dinh(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 23, kc, isPersonal, isSolo);
        count++;
    }
}