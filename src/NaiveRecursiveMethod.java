import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Algorithm:
 * 1)
 * <p>
 * Time Complexity is O()
 * referenced from: https://www.geeksforgeeks.org/weighted-job-scheduling/
 **/

public class NaiveRecursiveMethod {

    public static List<Job> sequenceJobs(List<Job> list, int listSize) {
        sortJob(list);
        return findMaxProfitRec(list, new ArrayList<>(), listSize);
    }

    // Find the latest job that does not conflict with the current job
    // Return -1 if no such job is found
    private static int latestNonConflict(List<Job> list, int listSize) {
        for (int j = listSize - 1; j >= 0; j--) {
            if (list.get(j).getEnd() <= list.get(listSize - 1).getStart())
                return j;
        }
        return -1;
    }

    // Recursive method that returns the maximum possible profit
    private static List<Job> findMaxProfitRec(List<Job> list, List<Job> inclProf, int listSize) {
        // Base case
        if (listSize == 1) {
            inclProf.add(list.get(0));
            inclProf.sort((b, a) -> b.getStart() - a.getStart());
            return inclProf;
        }

        // Find job when current job is included
        inclProf.add(list.get(listSize - 1));   // the last job is always selected - current
        int i = latestNonConflict(list, listSize);
        if (i != -1) {
            findMaxProfitRec(list, inclProf, i + 1);
        }

        inclProf.sort((b, a) -> b.getStart() - a.getStart());
        return inclProf;

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

        // Sort the jobs according to increasing order of finish time
        list.sort((b, a) -> b.getEnd() - a.getEnd());
//        list.sort(Comparator.comparingInt(Job::getEnd));

        System.out.println("\nSorted List :");
        printJob(list);
    }

}
