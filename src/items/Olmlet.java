package items;

public class Olmlet extends Loot {
    public static int count;
    public static final String NAME = "Olmlet";

    public Olmlet(int kc, boolean isPersonal, boolean isSolo, boolean isCM) {
        super(NAME, 53, kc, isPersonal, isSolo, isCM);
        count++;
    }
    public Olmlet(int kc, boolean isPersonal, boolean isSolo, boolean isCM, String time) {
        super(NAME, 53, kc, isPersonal, isSolo, isCM, time);
        count++;
    }
}