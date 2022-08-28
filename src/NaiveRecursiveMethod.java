import java.util.List;

/**
 * Algorithm:
 * 1)
 * 
 * Time Complexity is O()
 * referenced from: https://www.geeksforgeeks.org/weighted-job-scheduling/
 **/

public class NaiveRecursiveMethod {

    public static void sequenceJobs(List<Job> list, int listSize) {
        // Sort the jobs according to increasing order of deadline
        list.sort((b, a) -> b.getDeadline() - a.getDeadline());
        System.out.println(list);
        return sequenceJobs(list, listSize);
    }

    // Find the latest job that does not conflict with list[i]
    // Return -1 if there is no compatible job
    private static int latestNonConflict(List<Job> list, int listSize) {
        for (int i = listSize - 1; i >= 0; i--) {
            // finish <= start
            if (list.get(i).getDeadline() <= list.get(listSize - 1).getDeadline())
                return i;
        }
        return -1;
    }

    // A recursive function that returns the maximum possible
    // profit from given array of jobs. The array of jobs must
    // be sorted according to finish time.
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

}