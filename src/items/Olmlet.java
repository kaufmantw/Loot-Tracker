package items;

public class Olmlet extends Loot {
    public static int count;
    public static final String NAME = "Olmlet";

    public Olmlet(int kc, boolean isPersonal, boolean isSolo) {
        super(NAME, 53, kc, isPersonal, isSolo);
        count++;
    }
}