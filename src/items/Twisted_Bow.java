package items;
public class Twisted_Bow extends Loot{
    public static int count;
    public static final String NAME = "Twisted Bow";
    
    public Twisted_Bow(int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        super(NAME,34.5, kc, isPersonal, isSolo, isCM);
        count++;
    }
}