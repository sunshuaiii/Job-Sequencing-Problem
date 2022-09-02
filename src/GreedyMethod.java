import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm:
 * 1) Sort all jobs in decreasing order of profit.
 *
 * 2) Iterate on jobs in decreasing order of profit.
 *      For each job , do the following :
 *      a) Find a time slot i, such that slot is empty and i < duration and i is greatest.
 *      Put the job in this slot and mark this slot filled.
 *      b) If no such i exists, then ignore the job.
 *
 * 3) Sort job sequence in increasing order of duration.
 *
 * <p>
 * Time Complexity is O(n^2)
 * referenced from: https://www.geeksforgeeks.org/job-sequencing-problem/
 **/

public class GreedyMethod {

    public static List<Job> sequenceJobs(List<Job> list) {
        sortJob(list);
        int listSize = list.size();

        // To keep track of free time slots
        boolean[] result = new boolean[listSize];

        // To store resulting sequence of jobs
        List<Job> jobSequence = new ArrayList<>();

        // Iterating through all given jobs
        for (Job value : list) {
            // Find a free slot for this job starting from the last possible slot
            for (int j = Math.min(listSize - 1, value.getDuration() - 1); j >= 0; j--) {
                // Free slot found
                if (!result[j]) { // If the slot is currently empty
                    result[j] = true; // Set the slot to be filled
                    jobSequence.add(value); // Store the job into the resulting sequence
                    break; // Move on to the next job in the list
                }
            }
        }
        jobSequence.sort((a, b) -> a.getDuration() - b.getDuration());
        return jobSequence;
    }

    private static void printJob(List<Job> list) {
        System.out.printf("%12s:", "Job");
        for (Job job : list) {
            System.out.printf("%7s ", job.getId());
        }
        System.out.printf("%n%12s:", "Duration");
        for (Job job : list) {
            System.out.printf("%7s ", job.getDuration());
        }
        System.out.printf("%n%12s:", "Mark");
        for (Job job : list) {
            System.out.printf("%7s ", job.getProfit());
        }
        System.out.println();
    }

    private static void sortJob(List<Job> list) {
        printJob(list);

        // Sort the jobs according to decreasing order of profit
        list.sort((a, b) -> b.getProfit() - a.getProfit());

        System.out.println("\nSorted List :");
        printJob(list);
    }

}