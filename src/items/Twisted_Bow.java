package items;
public class Twisted_Bow extends Loot{
    public static int count;
    public static final String NAME = "Twisted Bow";
    
    public Twisted_Bow(int kc, boolean isPersonal, boolean isSolo){
        super(NAME,34.5, kc, isPersonal, isSolo);
        count++;
    }
}