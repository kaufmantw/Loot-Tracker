import java.util.ArrayList;

import items.*;

public class StatTracker{
    ArrayList<Loot> items;

    public StatTracker(ArrayList<Loot> items){
        this.items = items;
    }

   //checks the killcount since the last Twisted Bow item.
    public int kcSinceTbow() {
        Loot tempBow = new Twisted_Bow(0, false, false, false, "1999-11-22 15:53:03.6845859");
        for (Loot item : items) {
            if ((item instanceof Twisted_Bow) &&
                    item.stamp.after(tempBow.stamp)) {
                tempBow = item;
            }
        }

        return totalKc() - tempBow.getKc();

    }
    
    //returns the added kill counts for both challenge mode and
    //normal mode items.
    private int totalKc(){
        Loot tempNorm = new Loot("", 5,0, false, false, false,"2020-11-29 12:59:20.0186474");
        Loot tempCM = new Loot("", 5,0, false, false, false,"2020-11-29 12:59:20.0186474");
        for (Loot temp : items){
                if (temp.isCM() && temp.stamp.after(tempCM.stamp)){
                    tempCM = temp;
                }
                else if (temp.stamp.after(tempNorm.stamp)){
                    tempNorm = temp;
                }
            }
            return tempNorm.getKc() + tempCM.getKc();
        }

    //returns the total # of personal items.
    public int totalPersonal(){
        int counter = 0;
        for(Loot item : items){
            if(item.isPersonal())
                counter++;
        }
        return counter;
    }

    //returns the total # of solo items.
    public int totalSolo(){
        int counter = 0;
        for(Loot item : items){
            if(item.isSolo())
                counter++;
        }
        return counter;

    }

    //returns the number of items of a specific item
    //in the list.
    public int numOfItem(Loot obj){
        int counter = 0;
        for (Loot item : items){
            if(item.getName().equalsIgnoreCase(obj.getName()))
                counter++;
        }
        return counter;

    }

    public double binomProb(int total, int success, double rate){
        double failureRate = 1 - rate;
        double sum3 = Math.pow(failureRate, total - success);
        double sum2 = Math.pow(rate, success);
        int combo = combination(total, success);

        return (combo * sum2 * sum3);

    }

    public double binomAbove(int total, int success, double rate){
        double prob = 0;
        for(int i = success+1;i<total;i++){
            prob += binomProb(total, i, rate);
        }
        return prob;

    }

    public double binomBelow(int total, int success, double rate){
        double prob = 0;
        for(int i = success-1;i>=0;i--){
            prob += binomProb(total, i, rate);
        }
        return prob;

    }

    private int combination(int total, int success){
        int fact1 = factorial(total);
        int fact2 = factorial(success);
        int fact3 = factorial(total - success);

        return (fact1 / (fact2 * fact3));
    }

    private static int factorial(int number){
        if(number == 0){
            return 1;
        }
        else{
            return number * factorial(number - 1);
        }
    }
}