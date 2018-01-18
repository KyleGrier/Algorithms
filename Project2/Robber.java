/**
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Comparator;
import java.math.BigDecimal;
public class Robber {

    /*
     * This method should return true if the robber can rob all the houses in the neighborhood,
     * which are represented as a graph, and false if he cannot. The function should also print to the console the
     * order in which the robber should rob the houses if he can rob the houses. You do not need to print anything
     * if all the houses cannot be robbed.
     */
    public boolean canRobAllHouses(Graph neighborhood) {
        //Build String for the final System.output of the function
        StringBuilder builder = new StringBuilder();
        //The Houses that are in the graph
        String[] theHouses = neighborhood.getHouseNames();
        //The path Fruitcake will take on his road to vengeance
        Queue<String> robPath = new LinkedList<>();
        //The Houses that are currently locked
        Map<String, Integer> currentLocked = new HashMap<>();

        for(String ahouse: theHouses){
            //Add the unlocked door to Fruitcakes path map.
            //He will find the golden ticket one day.
            if(!neighborhood.isLocked(ahouse)){
                robPath.add(ahouse);
            }else{
                currentLocked.put(ahouse,neighborhood.keysGiven(ahouse));
            }
        }

        while(!robPath.isEmpty()){
            //Go through the Queue of Houses on FruitCakes path
            //Fruitcake the Oompa Loompa had escaped after 30 years a slave
            //Not a big deal. An Oompa lives to 2000 years old.
            //Just a bad summer in his world
            String nextHouse = robPath.remove();
            builder.append(nextHouse);
            builder.append(", ");
            String[] keyBounty = neighborhood.getKeyArray(nextHouse);
            for(String akey: keyBounty){
                if(currentLocked.containsKey(akey)){
                    decKeyCount(currentLocked, akey);
                    if(currentLocked.get(akey) == 0){
                        robPath.add(akey);
                        currentLocked.remove(akey);
                    }
                }
            }
        }
        builder.delete(builder.length()-2, builder.length());
        if(currentLocked.size() == 0){
            String answer = builder.toString();
            System.out.println(answer);
            return true;
        }
        return false;
    }

    //decrement number of keys need to unlock a house
    private void decKeyCount(Map<String, Integer> currentLocked, String theHouse){
        int count = currentLocked.get(theHouse);
        count--;
        currentLocked.replace(theHouse,count);
    }

    /*
     *
     */
    public void maximizeLoot(String lootList) {
        //TODO: Implement Function

        try {
            double bagCap;
            BufferedReader lootBR = new BufferedReader(new FileReader(lootList));
            Comparator<Loot> toCompare = new LootComparator();
            PriorityQueue<Loot> lootDecisions = new PriorityQueue<>(11, toCompare);

            String next = lootBR.readLine();
            String[] tokens = next.split( " ");
            bagCap = Double.parseDouble(tokens[0]);
            while(lootBR.ready()) {
                next = lootBR.readLine();
                tokens = next.split( ",");
                Loot newLoot = new Loot(tokens);
                lootDecisions.add(newLoot);
            }
            StringBuilder builder = new StringBuilder();
            DecimalFormat toform = new DecimalFormat("#.##");
            while(bagCap > 0 && !lootDecisions.isEmpty()){
                Loot nextPick = lootDecisions.remove();
                Double takings = nextPick.getAmount();
                if(takings > bagCap){
                    double taken = bagCap;
                    bagCap = 0;
                    builder.append(nextPick.getName() + " ");
                    builder.append(toform.format(taken) + "\n");
                }else{
                    bagCap = bagCap - takings;
                    builder.append(nextPick.getName() + " ");
                    builder.append(toform.format(takings) + "\n");
                }
            }

            String answer = builder.toString();
            System.out.println(answer);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void scheduleMeetings(String buyerList) {
        //TODO: Implement Function
        Double wiggleroom = 0.25;
        try {
            BufferedReader buyerBR = new BufferedReader(new FileReader(buyerList));
            ArrayList<Criminal> theMafia = new ArrayList<>();
            while(buyerBR.ready()){
                String crimLine = buyerBR.readLine();
                String[] tokens = crimLine.split(",");
                String crimName = tokens[0];
                Criminal joker = new Criminal(crimName);
                String[] timelength = tokens[1].split("-");
                joker.setStart(timelength[0].trim());
                joker.setFinish(timelength[1].trim());
                theMafia.add(joker);
            }
            quickSortCrim(theMafia, 0, theMafia.size()-1);
            ArrayList<Criminal> theAnswer = new ArrayList<>();
            theAnswer.add(theMafia.get(0));
            double toComp = theMafia.get(0).getDubF()+wiggleroom;
            for(int i = 1; i < theMafia.size(); i++){
                Criminal challenger = theMafia.get(i);
                if(challenger.getDubS()-wiggleroom > toComp){
                    toComp = challenger.getDubF()+wiggleroom;
                    theAnswer.add(challenger);
                }
            }
            StringBuilder builder = new StringBuilder();
            for(Criminal bob: theAnswer){
                builder.append(bob.getName());
                builder.append("\n");
            }
            String answer = builder.toString();
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private static void quickSortCrim(ArrayList<Criminal> theMafia, int low, int high){
        if(low == high){
            return;
        }
        int beg = low;
        int end = high;
        boolean side = true; // corresponds to pivot on left side
        while(beg != end){
            double comp1 = theMafia.get(beg).getDubF();
            double comp2 = theMafia.get(end).getDubF();
            if(side){ //pivot on left side
                if(comp2 < comp1){
                    Criminal ref = theMafia.get(beg);
                    theMafia.set(beg, theMafia.get(end));
                    theMafia.set(end, ref);
                    beg++;
                    side = false;
                }else{
                    end--;
                }
            }else{    // pivot on right side
                if(comp2 < comp1){
                    Criminal ref = theMafia.get(beg);
                    theMafia.set(beg, theMafia.get(end));
                    theMafia.set(end, ref);
                    end--;
                    side = false;
                }else{
                    beg++;
                }
            }
        }
        int pivot = beg;
        if(low != pivot) {
            quickSortCrim(theMafia, low, pivot - 1);
        }
        if(high != pivot) {
            quickSortCrim(theMafia, pivot + 1, high);
        }
        return;

    }
    private static class LootComparator implements Comparator<Loot> {

        @Override
        public int compare(Loot x, Loot y) {
            double xprice = x.getPrice();
            double yprice = y.getPrice();

            if (xprice > yprice)
                return -1;
            if (xprice < yprice)
                return 1;
            if (xprice == yprice)
                return 0;

            return 1;
        }
    }
}
