import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 */
public class Graph {

    /*
     * Creates a graph to represent the neighborhood, where unlocked is the file name for the unlocked houses
     * and keys is the file name for which houses have which keys.
     */
    private Map<String, House> houses;

    public Graph(String unlocked, String keys) {
        //TODO: Implement Constructor
        houses = new HashMap<>();
        try {
            //Create readers for the unlocked and keys files
            BufferedReader ulockBR = new BufferedReader(new FileReader(unlocked));
            BufferedReader keysBR = new BufferedReader(new FileReader(keys));

            //Make House classes to add to Map houses
            //Add key to those House classes
            while(keysBR.ready()){
                String nextLine = keysBR.readLine();
                String[] tokens = nextLine.split(":");
                House nextHouse = new House(tokens[0]);
                houses.put(nextHouse.getName(), nextHouse);
                //Check to see if no one trusted this poor fools house with keys.
                if(tokens.length == 1){
                    continue;
                }
                //***Test to see if one key is properly parsed
                //***Pretty sure it is
                String[] houseKeys = tokens[1].split(",");
                for(String hKey: houseKeys){
                    String gKey = hKey.trim();
                    nextHouse.addKey(gKey);
                }
            }
            keysBR.close();
            for(Map.Entry<String, House> dahouse: houses.entrySet()){
                String[] thekeys = dahouse.getValue().getKeyArray();
                for(String aKey: thekeys){
                    //dahouse.getValue().giveKey();
                    House theGiver = houses.get(aKey);
                    theGiver.giveKey();
                }
            }
            //For the houses that are unlocked, update that attribute in its corresponding object
            while(ulockBR.ready()){
                String nextLine = ulockBR.readLine();
                House carelessHomeowner = houses.get(nextLine);
                carelessHomeowner.unlock();
            }
            ulockBR.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * This method should return true if the Graph contains the vertex described by the input String.
     */
    public boolean containsVertex(String node) {
        //TODO: Implement function
        if(houses.containsKey(node)){
            return true;
        }
        return false;
    }

    /*
     * This method should return true if there is a direct edge from the vertex
     * represented by start String and end String.
     */
    public boolean containsEdge(String start, String end) {
        //TODO: Implement function
        if(containsVertex(start)){
            House check = houses.get(start);
            return check.hasKey(end);
        }

        return false;
    }

    /*
     * This method returns true if the house represented by the input String is locked
     * and false is the house has been left unlocked.
     */
    public boolean isLocked(String house) {
        //TODO: Implement function
        House check = houses.get(house);
        return check.lockStatus();
    }

    public String[] getHouseNames(){
        ArrayList<String> theHouses = new ArrayList<String>();
        Iterator it = houses.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, House> pair = (Map.Entry) it.next();
            theHouses.add(pair.getKey());
        }
        String [] returnArray = new String[theHouses.size()];
        return theHouses.toArray(returnArray);
    }

    public int keysGiven(String house){
        House check = houses.get(house);
        return check.getKeyNum();
    }

    public String[] getKeyArray(String house){
        House ahouse = houses.get(house);
        return ahouse.getKeyArray();
    }

}
