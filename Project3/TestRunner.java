/**
 * Created by kyle on 4/23/2017.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by nathanielwendt on 4/2/17.
 */
public class TestRunner {
    public static void main(String[] args){
        Program3 prog3 = new Program3();

        System.out.println("----- Running Activity Tests ------");

        List<ActivityTestCase> activityTests = new ArrayList<ActivityTestCase>();

        // Sample activity test case using the example in the assignment handout

        // Initialize the problem
        ActivityProblem actProblem0 = new ActivityProblem(
                new String[] {"Skydiving", "Dance Lessons", "Snorkeling"},
                new int[] {60,40,30},
                new int[] {4,3,3},
                6);

        ActivityProblem actProblem1 = new ActivityProblem(
                new String[] {"Skydiving", "Dance Lessons", "Snorkeling","Jumping"},
                new int[] {60,40,30,1000},
                new int[] {4,3,2,10},
                9);

        ActivityProblem actProblem2 = new ActivityProblem(
                new String[] {"Skydiving", "Dance Lessons", "Snorkeling","Jumping"},
                new int[] {60,40,30,1000},
                new int[] {0,0,0,0},
                0);

        ActivityProblem actProblem3 = new ActivityProblem(
                new String[] {"Skydiving"},
                new int[] {60},
                new int[] {4},
                4);

        ActivityProblem actProblem4 = new ActivityProblem(
                new String[] {"Skydiving"},
                new int[] {60},
                new int[] {4},
                0);

        // Initialize the solution
        ActivityResult actSolution0 = new ActivityResult(
                70,
                new HashSet<String>(Arrays.asList(new String[] {"Dance Lessons", "Snorkeling"})));

        ActivityResult actSolution1 = new ActivityResult(
                130,
                new HashSet<String>(Arrays.asList(new String[] {"Skydiving", "Dance Lessons", "Snorkeling"})));

        ActivityResult actSolution2 = new ActivityResult(
                1130,
                new HashSet<String>(Arrays.asList(new String[] {"Skydiving", "Dance Lessons", "Snorkeling","Jumping"})));

        ActivityResult actSolution3 = new ActivityResult(
                60,
                new HashSet<String>(Arrays.asList(new String[] {"Skydiving"})));

        ActivityResult actSolution4 = new ActivityResult(
                0,
                new HashSet<String>(Arrays.asList(new String[] {})));

        // Create and add test case based on specified problem and solution
        activityTests.add(new ActivityTestCase(actProblem0, actSolution0));
        activityTests.add(new ActivityTestCase(actProblem1, actSolution1));
        activityTests.add(new ActivityTestCase(actProblem2, actSolution2));
        activityTests.add(new ActivityTestCase(actProblem3, actSolution3));
        activityTests.add(new ActivityTestCase(actProblem4, actSolution4));
        // Compare your Program3 activity selector against the solutions specified above
        for(ActivityTestCase activityTest : activityTests){
            ActivityResult res = prog3.selectActivity(activityTest.getProblem());
            activityTest.check(res);
        }

        System.out.println("----- Running Scheduling Tests ------");

        List<SchedulingTestCase> schedulingTests = new ArrayList<SchedulingTestCase>();

        // Sample scheduling test case using the example in the assignment handout

        // Initialize the problem
        SchedulingProblem schProblem0 = new SchedulingProblem(
                new int[] {10,100,10},
                new int[] {400,20,400},
                50);

        SchedulingProblem schProblem1 = new SchedulingProblem(
                new int[] {10},
                new int[] {400},
                50);

        SchedulingProblem schProblem2 = new SchedulingProblem(
                new int[] {10,100,10},
                new int[] {400,20,400},
                1000);

        int[] myMaui = new int[10000];
        int[] myOahu = new int[10000];
        for(int i = 0; i < 10000; i++){
            myMaui[i] = 1;
            myOahu[i] = 100;
        }
        SchedulingProblem schProblem3 = new SchedulingProblem(
                myMaui,
                myOahu,
                10);

        SchedulingProblem schProblem4 = new SchedulingProblem(
                new int[] {10,100,10},
                new int[] {400,20,400},
                0);
        // Initialize the solution
        SchedulingResult schSolution0 = new SchedulingResult(
                new boolean[] {true,true,true});

        SchedulingResult schSolution1 = new SchedulingResult(
                new boolean[] {true});

        SchedulingResult schSolution2 = new SchedulingResult(
                new boolean[] {true,true,true});
        boolean[] bigdata1= new boolean[10000];
        for(int i = 0; i < 10000; i++){
            bigdata1[i] = true;
        }
        SchedulingResult schSolution3 = new SchedulingResult(
                bigdata1);

        SchedulingResult schSolution4 = new SchedulingResult(
                new boolean[] {true,false,true});

        // Create and add test case based on specified problem and solution
        schedulingTests.add(new SchedulingTestCase(schProblem0, schSolution0));
        schedulingTests.add(new SchedulingTestCase(schProblem1, schSolution1));
        schedulingTests.add(new SchedulingTestCase(schProblem2, schSolution2));
        schedulingTests.add(new SchedulingTestCase(schProblem3, schSolution3));
        schedulingTests.add(new SchedulingTestCase(schProblem4, schSolution4));
        // Compare your Program3 schedule selector against the solutions specified above
        for(SchedulingTestCase schedulingTest: schedulingTests){
            SchedulingResult res = prog3.selectScheduling(schedulingTest.getProblem());
            schedulingTest.check(res);
        }
    }
}

