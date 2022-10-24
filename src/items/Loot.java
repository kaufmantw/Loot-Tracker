package items;
public class Loot{
    private double dropRate;

    public Loot(double rate){
        this.dropRate = 1 / rate;
    }

    public double getRate(){
        return dropRate;
    }
}