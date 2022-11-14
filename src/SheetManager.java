import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import items.*;

public class SheetManager{

    ArrayList<Loot> items;
    File sheet;

    public SheetManager(){
        items = new ArrayList<>();
        this.sheet = new File("C:\\Users\\timka\\Documents\\code\\java\\OOP_Coursework\\Loot-Tracker\\bin\\sheets\\TempSheet.csv");
        fillList();
    }

    public void fillList(){
        String name;
        int kc;
        boolean isPersonal;
        boolean isSolo;
        try{
            String line = "";
            String delimit = ",";
            BufferedReader br = new BufferedReader(new FileReader(this.sheet));
            //this while loop creates one object per line in the CSV file.
            while((line = br.readLine()) != null){
                String[] read = line.split(delimit);
                name = read[0];
                //System.out.print(name + " ");
                kc = Integer.parseInt(read[1]);
                //System.out.print(kc + " ");
                if(read[2].equalsIgnoreCase("YES")){
                    isPersonal = true;
                }
                else{
                    isPersonal = false;
                }
                //System.out.print(isPersonal + " ");

                if(read[3].equalsIgnoreCase("YES")){
                    isSolo = true;
                }
                else{
                    isSolo = false;
                }
                //System.out.print(isSolo + " ");

                //This switch statement creates the correct Loot Object to go into our list.
                switch(name){

                case "Dexterous Prayer Scroll":
                    items.add(new Dex(kc, isPersonal, isSolo));
                    break;
                
                case "Arcane Prayer Scroll":
                    items.add(new Arcane(kc, isPersonal, isSolo));
                    break;

                case "Ancestral Robe Bottoms":
                    items.add(new Ances_Bottom(kc, isPersonal, isSolo));
                    break;

                case "Ancestral Robe Top":
                    items.add(new Ances_Top(kc, isPersonal, isSolo));
                    break;

                case "Ancestral Hat":
                    items.add(new Ances_Hat(kc, isPersonal, isSolo));
                    break;

                case "Twisted Buckler":
                    items.add(new Buckler(kc, isPersonal, isSolo));
                    break;

                case "Dragon hunter Crossbow":
                    items.add(new DHCB(kc, isPersonal, isSolo));
                    break;

                case "Dinh's Bulwark":
                    items.add(new Dinh(kc, isPersonal, isSolo));
                    break;

                case "Dragon Claws":
                    items.add(new Claws(kc, isPersonal, isSolo));
                    break;

                case "Elder Maul":
                    items.add(new Elder_Maul(kc, isPersonal, isSolo));
                    break;

                case "Kodai Insignia":
                    items.add(new Kodai_Insignia(kc, isPersonal, isSolo));
                    break;

                case "Twisted Bow":
                    items.add(new Twisted_Bow(kc, isPersonal, isSolo));
                    break;

                case "Olmlet":
                    items.add(new Olmlet(kc, isPersonal, isSolo));
                    break;

                case "Metamorphic Dust":
                    items.add(new Dust(kc, isPersonal, isSolo));
                    break;

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
                                items.get(i).isPersonal() + " " + items.get(i).isSolo());
        }
    }
}