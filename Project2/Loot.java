/**
 * Created by kyle on 3/26/2017.
 */
public class Loot {


    private String name;
    private double amount;
    private double price;

    public Loot(String[] theLoot){
        this.name = theLoot[0];
        this.amount = Double.parseDouble(theLoot[1].trim());
        this.price = Double.parseDouble(theLoot[2].trim());
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

}
