package items;
public class Ances_Hat extends Loot{
    public static int count;
    public static final String NAME = "Ancestral Hat";
    
    public Ances_Hat(int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        super(NAME, 23, kc, isPersonal, isSolo, isCM);
        count++;
    }

    public Ances_Hat(int kc, boolean isPersonal, boolean isSolo, boolean isCM, String time){
        super(NAME, 23, kc, isPersonal, isSolo, isCM, time);
        count++;
    }
}