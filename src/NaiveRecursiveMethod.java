import java.util.List;
import java.util.Stack;

/**
 * Algorithm:
 * 1) Sort the jobs according to increasing order of end time
 *
 * 2) Now apply following recursive process.
 *      -Here list is a Stack of jobs
 *      -Here listSize is the size for the Stack of jobs
 *      -Here jobSequence is the Stack for storing the sequence of jobs
 *
 *      // Base case
 *      a) if (listSize == 1)
 *          push the first job in list into jobSequence
 *          return jobSequence
 *
 *      b) Add the job with higher profit into jobSequence
 *          (i) Find the latest job with higher profit that does not conflict with the current job
 *          (ii) call recursive method findMaxProfitRec()
 *
 *      c) return jobSequence
 *
 * <p>
 * Time Complexity is O()
 * referenced from: https://www.geeksforgeeks.org/weighted-job-scheduling/
 **/

public class NaiveRecursiveMethod {

    public static Stack<Job> sequenceJobs(Stack<Job> stack) {
        sortJob(stack);
        int stackSize = stack.size();
        return findMaxProfitRec(stack, new Stack<>(), stackSize);
    }

    // Find the latest job list index that does not conflict with the current job
    // Return the index of the job in the list
    // Return -1 if no such job is found
    private static int latestNonConflict(Stack<Job> list, int stackSize) {
        for (int j = stackSize - 1; j >= 0; j--) {
            if (list.get(j).getEnd() <= list.get(stackSize - 1).getStart())
                return j;
        }
        return -1;
    }

    // Recursive method that returns the maximum possible profit
    private static Stack<Job> findMaxProfitRec(Stack<Job> list, Stack<Job> jobSequence, int stackSize) {
        // Base case
        if (stackSize == 1) {
            jobSequence.push(list.get(0));
            return jobSequence;
        }

        // the last job is selected and becomes the current job
        jobSequence.push(list.get(stackSize - 1));
        int i = latestNonConflict(list, stackSize);

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

        System.out.println("\nSorted List :");
        printJob(list);
    }
}
