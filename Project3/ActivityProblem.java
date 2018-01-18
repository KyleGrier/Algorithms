/**
 * Created by kyle on 4/23/2017.
 */
import java.util.Arrays;

public class ActivityProblem {
    String[] activities;
    int[] funLevels;
    int[] riskLevels;
    int riskBudget;

    public ActivityProblem(){}

    public ActivityProblem(String[] activities, int[] funLevels, int[] riskLevels, int riskBudget) {
        this.activities = activities;
        this.funLevels = funLevels;
        this.riskLevels = riskLevels;
        this.riskBudget = riskBudget;
    }

    public int[] getFunLevels() {
        return funLevels;
    }

    public String[] getActivities() {
        return activities;
    }

    public void setActivities(String[] activities) {
        this.activities = activities;
    }

    public void setFunLevels(int[] funLevels) {
        this.funLevels = funLevels;
    }

    public int[] getRiskLevels() {
        return riskLevels;
    }

    public void setRiskLevels(int[] riskLevels) {
        this.riskLevels = riskLevels;
    }

    public int getRiskBudget() {
        return riskBudget;
    }

    public void setRiskBudget(int riskBudget) {
        this.riskBudget = riskBudget;
    }

    @Override
    public String toString() {
        return "ActivityProblem{" +
                "selectedActivities=" + Arrays.toString(activities) +
                ", funLevels=" + Arrays.toString(funLevels) +
                ", riskLevels=" + Arrays.toString(riskLevels) +
                ", riskBudget=" + riskBudget +
                '}';
    }
}
