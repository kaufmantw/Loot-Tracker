package items;
public class DHCB extends Loot{
    public static int count;
    public static final String NAME = "Dragonhunter Crossbow";
    
    public DHCB(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 17.25, kc, isPersonal, isSolo);
        count++;
    }
}