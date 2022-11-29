import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import items.*;

public class SheetManager{

    ArrayList<Loot> items;
    File saveSheet;

    public SheetManager(){
        items = new ArrayList<>();
        this.saveSheet = new File(".\\bin\\sheets\\SaveSheet.csv");
        fillList();
    }

    public void fillList(){
        String name;
        int kc;
        boolean isPersonal;
        boolean isSolo;
        boolean isCM;
        String time;
        try{
            String line = "";
            String delimit = ",";
            BufferedReader br = new BufferedReader(new FileReader(this.saveSheet));
            //this while loop creates one object per line in the CSV file.
            while((line = br.readLine()) != null){
                String[] read = line.split(delimit);
                name = read[0];
                //System.out.print(name + " ");
                kc = Integer.parseInt(read[1]);
                //System.out.print(kc + " ");
                isPersonal = read[2].equalsIgnoreCase("YES");
                //System.out.print(isPersonal + " ");

                isSolo = read[3].equalsIgnoreCase("YES");
                //System.out.print(isSolo + " ");

                isCM = read[4].equalsIgnoreCase("YES");

                time = read[5];

                //This switch statement creates the correct Loot Object to go into our list.
                Loot temp;
                switch(name){

                case "Dexterous Prayer Scroll":
                    temp = new Dex(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;
                
                case "Arcane Prayer Scroll":
                    temp = new Arcane(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Ancestral Robe Bottoms":
                    temp = new Ances_Bottom(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Ancestral Robe Top":
                    temp = new Ances_Top(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Ancestral Hat":
                    temp = new Ances_Hat(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Twisted Buckler":
                    temp = new Buckler(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Dragonhunter Crossbow":
                    temp = new DHCB(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Dinh's Bulwark":
                    temp = new Dinh(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Dragon Claws":
                    temp = new Claws(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Elder Maul":
                    temp = new Elder_Maul(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Kodai Insignia":
                    temp = new Kodai_Insignia(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Twisted Bow":
                    temp = new Twisted_Bow(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Olmlet":
                    temp = new Olmlet(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;

                case "Metamorphic Dust":
                    temp = new Dust(kc, isPersonal, isSolo, isCM, time);
                    items.add(temp);
                    break;
                
                default:
                    throw new InputMismatchException("There is a strange item in the file.");

                }

                //System.out.println();

            }
            br.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Fix the file path dumbass.");
        }

        catch(IOException e){
            System.out.println("Fix the file dumbass.");
        }
    }

    public void add(Loot obj)throws IOException{
        this.items.add(obj);

    }

    public void save()throws IOException{
        FileWriter fw = new FileWriter(".\\bin\\sheets\\SaveSheet.csv", false);
    
        for(int i = 0;i<items.size();i++){
            //added a BufferedWriter to start entries on new lines.
            fw.write(items.get(i).getName()+",");
            fw.write(items.get(i).getKc()+",");
            if(items.get(i).isPersonal()){
                fw.write("YES,");
            }
            else{
                fw.write("NO,");

            }
            if(items.get(i).isSolo()){
                fw.write("YES,");
            }
            else{
                fw.write("NO,");
            }

            if(items.get(i).isCM()){
                fw.write("YES,");
            }
            else{
                fw.write("NO,");
            }

            fw.write(items.get(i).getTime());
            if((i<items.size()-1)){
                fw.write("\r\n");
            }
            //adding the new line, then closing with the FileWriter.
            }
            fw.close();
    }

    //this method is used to generate data for
    //the compare charts.
    //TODO: returnCount should probably incorporate a name for specification.
    public int returnCount(int lowerbound, int upperbound, boolean isCM, boolean isSolo, boolean isPersonal){
        int count = 0;
        for(Loot temp : items){
            if( temp.getKc() <= upperbound &&
                temp.getKc() >= lowerbound &&
                temp.isSolo() == isSolo &&
                temp.isPersonal() == isPersonal){
                    count++;
                }
        }
        return count;
    }

    public int totalKc(){
        Loot tempNorm = new Loot("", 5,0, false, false, false);
        Loot tempCM = new Loot("", 5,0, false, false, false);
        for (Loot temp : items){
            if (temp.isCM()){
                tempCM = temp;
            }
            else{
                tempNorm = temp;
            }
        }
        return tempNorm.getKc() + tempCM.getKc();
    }

    public void remove(Loot obj){
        items.remove(obj);
    }

    public int totalPersonal(){
        int counter = 0;
        for(Loot item : items){
            if(item.isPersonal())
                counter++;
        }
        return counter;
    }

    public int totalSolo(){
        int counter = 0;
        for(Loot item : items){
            if(item.isSolo())
                counter++;
        }
        return counter;

    }
}