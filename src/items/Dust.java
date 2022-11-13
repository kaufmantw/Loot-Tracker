package items;

public class Dust extends Loot {
    public static int count;
    public static final String NAME = "Metamorphic Dust";

    public Dust(int kc, boolean isPersonal, boolean isSolo) {
        super(NAME, 400, kc, isPersonal, isSolo);
        count++;
    }
}