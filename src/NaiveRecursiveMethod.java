import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm:
 * 1)
 * 
 * Time Complexity is O()
 * referenced from: https://www.geeksforgeeks.org/weighted-job-scheduling/
 **/

public class NaiveRecursiveMethod {

    public static int sequenceJobs(List<Job> list, int listSize) {
        // Sort the jobs according to increasing order of finish time
        list.sort((b, a) -> b.getFinish() - a.getFinish());
        return findMaxProfitRec(list, listSize);
    }

    // Find the latest job that does not conflict with the current job
    // Return -1 if no such job is found
    private static int latestNonConflict(List<Job> list, int listSize) {
        for (int j = listSize - 1; j >= 0; j--) {
            if (list.get(j).getFinish() <= list.get(listSize - 1).getStart())
                return j;
        }
        return -1;
    }

    // Recursive method that returns the maximum possible profit
    private static int findMaxProfitRec(List<Job> list, int listSize) {
        // Base case
        if (listSize == 1)
            return list.get(listSize - 1).getProfit();

        // Find profit when current job is included
        int inclProf = list.get(listSize - 1).getProfit();
        int i = latestNonConflict(list, listSize);
        if (i != -1)
            inclProf += findMaxProfitRec(list, i + 1);

        // Find profit when current job is excluded
        int exclProf = findMaxProfitRec(list, listSize - 1);

        return Math.max(inclProf, exclProf);
    }

    // To Sunshuai: here is start and finish instead of deadline
    // Another issue: returns only the optimal profit and not the sequence of jobs, so need to modify
    public static void main(String args[]) {
        List<Job> list = new ArrayList<Job>();
        list.add(new Job(3, 10, 20));
        list.add(new Job(1, 2, 50));
        list.add(new Job(6, 19, 100));
        list.add(new Job(2, 100, 200));
        System.out.println("The optimal profit is " + sequenceJobs(list, list.size()));
    }

    static class Job {
        int start, finish, profit;
        Job(int start, int finish, int profit) {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
        public int getStart() {
            return start;
        }
        public int getFinish() {
            return finish;
        }
        public int getProfit() {
            return profit;
        }
    }

}
