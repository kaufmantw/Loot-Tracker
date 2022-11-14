package items;
public class Dex extends Loot{
    public static int count;
    public static final String NAME = "Dexterous Prayer Scroll";
    
    public Dex(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 3.45, kc, isPersonal, isSolo);
        count++;
    }
}