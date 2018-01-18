/**
 * Created by kyle on 3/27/2017.
 */
public class Criminal {
    private int sHour;
    private int sMinute;
    private double sDub;
    private int fHour;
    private int fMinute;
    private double fDub;
    private String name;
    public Criminal(String name){
        this.name = name;
    }

    public void setStart(String time){
        int thatlen = time.length();
        String timenumber = time.substring(0, thatlen - 2);
        String timetype = time.substring(thatlen - 2, thatlen);;
        if(timenumber.length() > 2){
            String[] tokens = timenumber.split(":");
            sHour = Integer.parseInt(tokens[0]);
            sMinute = Integer.parseInt(tokens[1]);
        }else{
            sHour = Integer.parseInt(timenumber);
            sMinute = 0;
        }
        if(timetype.equals("pm")){
            sHour += 12;
        }
        toDoubleS();
    }
     public void setFinish(String time){
         int thatlen = time.length();
         String timenumber = time.substring(0, thatlen - 2);
         String timetype = time.substring(thatlen - 2, thatlen);;
         if(timenumber.length() > 2){
             String[] tokens = timenumber.split(":");
             fHour = Integer.parseInt(tokens[0]);
             fMinute = Integer.parseInt(tokens[1]);
         }else{
             fHour = Integer.parseInt(timenumber);
             fMinute = 0;
         }
         if(timetype.equals("pm")){
             fHour += 12;
         }
         toDoubleF();

     }

     private void toDoubleF(){
         double dec = fMinute/60.0;
         double toreturn = fHour + dec;
         this.fDub = toreturn;
     }

     public void toDoubleS(){
        double dec = sMinute/60.0;
        double toreturn = sHour + dec;
        this.sDub = toreturn;

     }

     public double getDubS(){
         return this.sDub;
     }

     public double getDubF(){
         return this.fDub;
     }

     public String getName() {return this.name;}
}
