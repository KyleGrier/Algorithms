import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Created by kyle on 4/23/2017.
 */

public class Program3 {
    private final boolean MAUI = true;
    private final boolean OAHU = false;
    public ActivityResult selectActivity(ActivityProblem activityProblem){
        String[] theActivities = activityProblem.getActivities();
        int[] funLevels = activityProblem.getFunLevels();
        int[] riskLevels = activityProblem.getRiskLevels();
        int numAct = theActivities.length;
        int W = activityProblem.riskBudget;
        int[][] optimize = new int[numAct+1][W+1];
        ArrayList<String> solution = new ArrayList<String>();
        for(int i = 1; i < numAct+1; i++){
            int curRisk = riskLevels[i-1];
            int curFun = funLevels[i-1];
            for(int w = 0; w< W+1; w++){
                if(curRisk > w){
                    optimize[i][w] = optimize[i-1][w];
                }else{
                    optimize[i][w] = getMax(optimize[i-1][w], curFun + optimize[i-1][w-curRisk]);
                }
            }
        }
        int i = numAct;
        int curW = W;
        int back = optimize[i][curW];
        int maxValue = back;
        while(i != 0){
            int next = i-1;
            int thenext = optimize[next][curW];
            if(thenext == back){
                i--;
                back = thenext;
                continue;
            }else{
                i--;
                solution.add(theActivities[i]);
                curW -= riskLevels[i];
                back = optimize[i][curW];
            }
        }
        Set<String> selectedActivity = new HashSet(solution);
        return new ActivityResult(maxValue, selectedActivity);
    }

    public SchedulingResult selectScheduling(SchedulingProblem schedulingProblem){
        int[] mauiCosts = schedulingProblem.getMauiCosts();
        int[] oahuCosts = schedulingProblem.getOahuCosts();
        int transferCost = schedulingProblem.getTransferCost();
        int numDays = mauiCosts.length;
        int[][] optimize = new int[numDays][2];
        optimize[0][0] = mauiCosts[0];
        optimize[0][1] = oahuCosts[0];
        for(int i = 1; i < numDays; i++){
            int stayMaui = mauiCosts[i] + optimize[i-1][0];
            int fromOahu = mauiCosts[i] + transferCost + optimize[i-1][1];
            optimize[i][0]= begMin(stayMaui, fromOahu);

            int stayOahu = oahuCosts[i] + optimize[i-1][1];
            int fromMaui = oahuCosts[i] + transferCost + optimize[i-1][0];
            optimize[i][1]= begMin(fromMaui, stayOahu);
        }
        boolean[] answer = new boolean[numDays];
        int endMaui = optimize[numDays-1][0];
        int endOahu = optimize[numDays-1][1];
        answer[numDays-1] = endMin(endMaui,endOahu);

        for(int i = numDays-2; i >= 0; i--){
            boolean previous = answer[i+1];

            if(previous == MAUI){
                int prevMaui = optimize[i+1][0];
                int theLink = optimize[i][0] + mauiCosts[i+1];
                if(theLink == prevMaui){
                    answer[i] = MAUI;
                }else{
                    answer[i] = OAHU;
                }
            }else{
                int prevOahu = optimize[i+1][1];
                int theLink = optimize[i][1] + oahuCosts[i+1];
                if(theLink == prevOahu){
                    answer[i] = OAHU;
                }else{
                    answer[i] = MAUI;
                }
            }

        }
        return new SchedulingResult(answer);
    }


    private int getMax(int val1, int val2){
        if(val1 > val2){
            return val1;
        }else if(val2 > val1){
            return val2;
        }else{
            return val1;
        }
    }
    private int begMin(int val1, int val2){
        if(val1 < val2){
            return val1;
        }else if(val2 < val1){
            return val2;
        }else{
            return val1;
        }
    }
    //true for maui
    //false for oahu
    private boolean endMin(int maui, int oahu ){
        if(maui < oahu){
            return MAUI;
        }else if(oahu < maui){
            return OAHU;
        }else{
            return MAUI;
        }
    }

}

