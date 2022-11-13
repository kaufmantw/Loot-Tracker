package items;
public class Elder_Maul extends Loot{
    public static int count;
    public static final String NAME = "Elder Maul";
    
    public Elder_Maul(int kc, boolean isPersonal, boolean isSolo){
        super(NAME, 34.5, kc, isPersonal, isSolo);
        count++;
    }
}