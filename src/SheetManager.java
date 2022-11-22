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
    File copySheet;

    public SheetManager(){
        items = new ArrayList<>();
        this.saveSheet = new File(".\\bin\\sheets\\SaveSheet.csv");
        this.copySheet = new File(".\\bin\\sheets\\CopySheet.csv");
        try{
            emptyCopy();
            makeCopy();
        }
        catch(IOException e){
            e.getMessage();
        }

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

                case "Dragon hunter Crossbow":
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

    public void printList(){
        System.out.println();
        for(int i = 0;i<this.items.size();i++){
            System.out.println(items.get(i).getName() + " " + items.get(i).getKc() + " " +
                                items.get(i).isPersonal() + " " + items.get(i).isSolo() +
                                " " + items.get(i).isCM() + " " + items.get(i).getTime());
        }
    }

    public void printCounts(){
        System.out.println("Dexterous Prayer scrolls: " + Dex.count);
        System.out.println("Arcane Prayer scrolls: " + Arcane.count);
        System.out.println("Dinhs Bulwark: " + Dinh.count);
        System.out.println("Ances Bottoms: " + Ances_Bottom.count);
        System.out.println("Ances Top: " + Ances_Top.count);
        System.out.println("Ances Hat: " + Ances_Hat.count);
        System.out.println("Dragon Claws: " + Claws.count);
        System.out.println("Buckler: " + Buckler.count);
        System.out.println("DHCB: " + DHCB.count);
        System.out.println("Elder Mauls: " + Elder_Maul.count);
        System.out.println("Kodais: " + Kodai_Insignia.count);
        System.out.println("Twisted Bows: " + Twisted_Bow.count);
        System.out.println("Olmlet: " + Olmlet.count);
        System.out.println("Dust: " + Dust.count);
        System.out.println();

    }

    private void makeCopy()throws IOException{
        

        Path readPath = (Paths.get(".\\bin\\sheets\\SaveSheet.csv")).normalize();
        List<String> lines = Files.readAllLines(readPath);
        Path writePath = Paths.get(".\\bin\\sheets\\CopySheet.csv");
        Files.write(writePath, lines, StandardOpenOption.APPEND);
    }

    private void emptyCopy()throws IOException{
        FileWriter fw = new FileWriter(".\\bin\\sheets\\CopySheet.csv", false);
        fw.write("");
        fw.close();
    }

    public void add(Loot obj)throws IOException{
        this.items.add(obj);
        
        FileWriter fw = new FileWriter(".\\bin\\sheets\\CopySheet.csv", true);
        //added a BufferedWriter to start entries on new lines.
        BufferedWriter bw = new BufferedWriter(fw);
        fw.write(obj.getName()+",");
        fw.write(obj.getKc()+",");
        if(obj.isPersonal()){
            fw.write("YES,");
        }
        else{
            fw.write("NO,");

        }
        if(obj.isSolo()){
            fw.write("YES,");
        }
        else{
            fw.write("NO,");
        }

        if(obj.isCM()){
            fw.write("YES,");
        }
        else{
            fw.write("NO,");
        }

        fw.write(obj.getTime());
        //adding the new line, then closing with the FileWriter.
        bw.newLine();
        bw.close();
        fw.close();
    }

    public void save()throws IOException{
        FileWriter fw = new FileWriter(".\\bin\\sheets\\SaveSheet.csv", false);
        fw.write("");
        fw.close();

        Path readPath = (Paths.get(".\\bin\\sheets\\CopySheet.csv")).normalize();
        List<String> lines = Files.readAllLines(readPath);
        Path writePath = Paths.get(".\\bin\\sheets\\SaveSheet.csv");
        Files.write(writePath, lines, StandardOpenOption.APPEND);
    }

    //this method is used to generate data for
    //the compare charts.
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
}