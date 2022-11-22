package items;

public class Dust extends Loot {
    public static int count;
    public static final String NAME = "Metamorphic Dust";

    public Dust(int kc, boolean isPersonal, boolean isSolo, boolean isCM) {
        super(NAME, 400, kc, isPersonal, isSolo, isCM);
        count++;
    }

    public Dust(int kc, boolean isPersonal, boolean isSolo, boolean isCM, String time) {
        super(NAME, 400, kc, isPersonal, isSolo, isCM, time);
        count++;
    }
}