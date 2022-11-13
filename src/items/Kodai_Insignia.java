package items;
public class Kodai_Insignia extends Loot{
    public static int count;
    public static final String NAME = "Kodai Insignia";
    
    public Kodai_Insignia(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 34.5, kc, isPersonal, isSolo);
        count++;
    }
}