package items;
public class DHCB extends Loot{
    public static int count;
    public static final String NAME = "Dragonhunter Crossbow";
    
    public DHCB(int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        super(NAME, 17.25, kc, isPersonal, isSolo, isCM);
        count++;
    }

    public DHCB(int kc, boolean isPersonal, boolean isSolo, boolean isCM, String time){
        super(NAME, 17.25, kc, isPersonal, isSolo, isCM, time);
        count++;
    }
}