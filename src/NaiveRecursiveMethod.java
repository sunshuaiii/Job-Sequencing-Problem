import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Algorithm:
 * 1) Sort the jobs according to increasing order of end time
 *
 * 2) Now apply following recursive process.
 *      -Here list is an ArrayList of jobs
 *      -Here listSize is the size for the ArrayList of jobs
 *      -Here jobSequence is the ArrayList for storing the sequence of jobs
 *
 *      // Base case
 *      a) if (listSize == 1)
 *          add the first job in list into jobSequence
 *          sort the jobSequence according to increasing order of start time
 *          return jobSequence
 *
 *      b) Add the job with higher profit into jobSequence
 *          (i) Find the latest job with higher profit that does not conflict with the current job
 *          (ii) call recursive method findMaxProfitRec()
 *
 *      c) sort the jobSequence according to increasing order of start time
 *          return jobSequence
 * <p>
 * Time Complexity is O()
 * referenced from: https://www.geeksforgeeks.org/weighted-job-scheduling/
 **/

public class NaiveRecursiveMethod {

    public static List<Job> sequenceJobs(List<Job> list) {
        sortJob(list);
        int listSize = list.size();
        return findMaxProfitRec(list, new ArrayList<>(), listSize);
    }

    // Find the latest job list index that does not conflict with the current job
    // Return the index of the job in the list
    // Return -1 if no such job is found
    private static int latestNonConflict(List<Job> list, int listSize) {
        for (int j = listSize - 1; j >= 0; j--) {
            if (list.get(j).getEnd() <= list.get(listSize - 1).getStart())
                return j;
        }
        return -1;
    }

    // Recursive method that returns the maximum possible profit
    private static List<Job> findMaxProfitRec(List<Job> list, List<Job> jobSequence, int listSize) {
        // Base case
        if (listSize == 1) {
            jobSequence.add(list.get(0));
            jobSequence.sort((b, a) -> b.getStart() - a.getStart());
            return jobSequence;
        }

        // the last job is always selected and becomes current job
        jobSequence.add(list.get(listSize - 1));
        int i = latestNonConflict(list, listSize);

        if (i != -1) {
            if (i > 0) {
                if (list.get(i - 1).getProfit() > list.get(i).getProfit()) {
                    // Add job to jobSequence when job before current job has higher profit than current job
                    findMaxProfitRec(list, jobSequence, i);
                } else {
                    // Add job to jobSequence when current job has higher profit than job before current job
                    findMaxProfitRec(list, jobSequence, i + 1);
                }
            } else {
                // Add current job to jobSequence
                findMaxProfitRec(list, jobSequence, i + 1);
            }
        }

        return jobSequence;

    }

    public static void printJob(List<Job> list) {
        System.out.printf("%12s:", "Job");
        for (Job job : list) {
            System.out.printf("%7s ", job.getId());
        }
        System.out.printf("%n%12s:", "Start");
        for (Job job : list) {
            System.out.printf("%7s ", job.getStart());
        }
        System.out.printf("%n%12s:", "End");
        for (Job job : list) {
            System.out.printf("%7s ", job.getEnd());
        }
        System.out.printf("%n%12s:", "Mark");
        for (Job job : list) {
            System.out.printf("%7s ", job.getProfit());
        }
        System.out.println();
    }

    public static void sortJob(List<Job> list) {
        printJob(list);

        // Sort the jobs according to increasing order of end time
        list.sort((b, a) -> b.getEnd() - a.getEnd());
        // list.sort(Comparator.comparingInt(Job::getEnd));

        System.out.println("\nSorted List :");
        printJob(list);
    }
}
