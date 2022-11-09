package items;

public class Dust extends Loot {
    public static int count;
    public static final String NAME = "Metamorphic Dust";

    public Dust() {
        super(400);
        count++;
    }
}