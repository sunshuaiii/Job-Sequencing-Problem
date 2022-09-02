import java.util.List;
import java.util.Stack;

/**
 * Algorithm:
 * 1) Sort the jobs according to increasing order of end time
 *
 * 2) Now apply following recursive process.
 *      -Here stack is a Stack of jobs
 *      -Here stackSize is the size for the Stack of jobs
 *      -Here jobSequence is the Stack for storing the sequence of jobs
 *
 *      // Base case
 *      a) if (stackSize == 1)
 *          push the first job in stack into jobSequence
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
    private static int latestNonConflict(Stack<Job> stack, int stackSize) {
        for (int j = stackSize - 1; j >= 0; j--) {
            if (stack.get(j).getEnd() <= stack.get(stackSize - 1).getStart())
                return j;
        }
        return -1;
    }

    // Recursive method that returns the maximum possible profit
    private static Stack<Job> findMaxProfitRec(Stack<Job> stack, Stack<Job> jobSequence, int stackSize) {
        // Base case
        if (stackSize == 1) {
            jobSequence.push(stack.get(0));
            return jobSequence;
        }

        // the last job is selected and becomes the current job
        jobSequence.push(stack.get(stackSize - 1));
        int i = latestNonConflict(stack, stackSize);

        if (i != -1) {
            if (i > 0) {
                if (stack.get(i - 1).getProfit() > stack.get(i).getProfit()) {
                    // Add job to jobSequence when job before current job has higher profit than current job
                    findMaxProfitRec(stack, jobSequence, i);
                } else {
                    // Add job to jobSequence when current job has higher profit than job before current job
                    findMaxProfitRec(stack, jobSequence, i + 1);
                }
            } else {
                // Add current job to jobSequence
                findMaxProfitRec(stack, jobSequence, i + 1);
            }
        }

        return jobSequence;

    }

    public static void printJob(Stack<Job> stack) {
        System.out.printf("%12s:", "Job");
        for (Job job : stack) {
            System.out.printf("%7s ", job.getId());
        }
        System.out.printf("%n%12s:", "Start");
        for (Job job : stack) {
            System.out.printf("%7s ", job.getStart());
        }
        System.out.printf("%n%12s:", "End");
        for (Job job : stack) {
            System.out.printf("%7s ", job.getEnd());
        }
        System.out.printf("%n%12s:", "Mark");
        for (Job job : stack) {
            System.out.printf("%7s ", job.getProfit());
        }
        System.out.println();
    }

    public static void sortJob(Stack<Job> stack) {
        printJob(stack);

        // Sort the jobs according to increasing order of end time
        stack.sort((b, a) -> b.getEnd() - a.getEnd());

        System.out.println("\nSorted List :");
        printJob(stack);
    }
}
