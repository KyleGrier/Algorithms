/**
 *
 */
public class Driver {

    /* This driver is provided to help you test your program. You can make changes to this file, but we will not use
     * them for grading your program. We will use our own driver for grading your program. Hence, the correctness of
     * your algorithms should not be dependent on the code in this file.
     */
    public static void main(String[] args) {
       Graph neighborhood = new Graph("unlocked.txt", "keys.txt");
       testGraph(neighborhood);
       Robber fruitcake = new Robber();
       Boolean ans = fruitcake.canRobAllHouses(neighborhood);
       if(!ans){
           System.out.print("Path cannot be found\n");
       }
       fruitcake.maximizeLoot("ingredients.txt");
       fruitcake.scheduleMeetings("buyers.txt");
    }

    public static void testGraph(Graph neighborhood){
        Boolean ans = neighborhood.isLocked("House A");
        System.out.println("House A = " + ans);
        ans = neighborhood.containsEdge("House A", "House C");
        System.out.println(ans);
        ans = neighborhood.containsVertex("House E");
        System.out.println(ans);
    }

}
