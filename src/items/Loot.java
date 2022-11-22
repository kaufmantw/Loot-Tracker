package items;
public class Loot{

    private String name;
    private double dropRate;
    private int kc;
    private boolean isPersonal;
    private boolean isSolo;
    private boolean isCM;

    public Loot(String name, double rate, int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        this.name = name;
        this.dropRate = 1 / rate;
        this.kc = kc;
        this.isPersonal = isPersonal;
        this.isSolo = isSolo;
        this.isCM = isCM;
    }

    
    public String getName(){
        return this.name;
    }

    public double getRate(){
        return dropRate;
    }

    public int getKc(){
        return this.kc;
    }

    public boolean isPersonal(){
        return isPersonal;
    }

    public boolean isSolo(){
        return isSolo;
    }

    public boolean isCM(){
        return isCM;
    }
}