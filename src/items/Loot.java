package items;

import java.sql.Timestamp;
import java.time.Instant;

public class Loot{

    private String name;
    private double dropRate;
    private int kc;
    private boolean isPersonal;
    private boolean isSolo;
    private boolean isCM;

    private Timestamp stamp;

    public Loot(String name, double rate, int kc, boolean isPersonal, boolean isSolo, boolean isCM, String time){
        this.name = name;
        this.dropRate = 1 / rate;
        this.kc = kc;
        this.isPersonal = isPersonal;
        this.isSolo = isSolo;
        this.isCM = isCM;

        this.stamp = Timestamp.valueOf(time);
    }

    public Loot(String name, double rate, int kc, boolean isPersonal, boolean isSolo, boolean isCM){
        this.name = name;
        this.dropRate = 1 / rate;
        this.kc = kc;
        this.isPersonal = isPersonal;
        this.isSolo = isSolo;
        this.isCM = isCM;

        this.stamp = Timestamp.from(Instant.now());
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

    public String getTime(){
        return stamp.toString();
    }
}
