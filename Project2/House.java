/**
 * Created by kyle on 3/25/2017.
 */

import java.util.*;
public class House {
    private String name;
    private boolean lockStatus;
    private Map<String, Boolean> keys;
    private int keysGiven;
    public House(String name){
        this.name = name;
        this.keys = new HashMap<>();
        this.lockStatus = true;
        this.keysGiven = 0;
    }

    public void addKey(String key){
        keys.put(key, true);
    }

    public boolean lockStatus(){ return this.lockStatus; }

    public void unlock(){
        this.lockStatus = false;
    }

    public String getName(){
        return this.name;
    }

    public boolean hasKey(String akey){ return keys.containsKey(akey); }

    public void giveKey(){
        this.keysGiven++;
    }

    public int getKeyNum(){
        return keysGiven;
    }

    public String[] getKeyArray(){
        //Gets key's of a specific house. Theses keys specify with a String
        //who was given a spare key.
        ArrayList<String> theKeys = new ArrayList<String>();
        //***********MIGHT BE A PROBLEM because it reomves from mapping.
        //That only happens once though so it doesn't matter for the purposes of the assignment.
        Iterator it = keys.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, House> pair = (Map.Entry) it.next();
            theKeys.add(pair.getKey());
        }
        String [] returnArray = new String[theKeys.size()];
        return theKeys.toArray(returnArray);
    }

}
