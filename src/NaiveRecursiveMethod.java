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
        // Sort the jobs according to increasing order of finish time
        list.sort((b, a) -> b.getEnd() - a.getEnd());
//        list.sort(Comparator.comparingInt(Job::getEnd));
        for (Job job : list) {
            System.out.print(job.getId() + " ");
        }
        System.out.println();
        return findMaxProfitRec(list, listSize);
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
    private static List<Job> findMaxProfitRec(List<Job> list, int listSize) {
        // To store resulting sequence of jobs
        List<Job> jobs = new ArrayList<>();
        List<Job> inclProf = new ArrayList<>();
        List<Job> exclProf;

        // Base case
        if (listSize == 1) {
            jobs.add(list.get(0));
            return jobs;
        }

        // Find profit when current job is included
        inclProf.add(list.get(listSize - 1));
        int i = latestNonConflict(list, listSize);
        if (i != -1) {
            inclProf.add(list.get(i));
            findMaxProfitRec(list, i + 1);
        }

        // Find profit when current job is excluded
        exclProf = findMaxProfitRec(list, listSize - 1);

        int totalProfit = 0;
        for (Job job : inclProf) {
            totalProfit += job.getProfit();
        }
        int totalProfit2 = 0;
        for (Job job : exclProf) {
            totalProfit2 += job.getProfit();
        }

        if (totalProfit > totalProfit2) {
            return inclProf;
        } else {
            return exclProf;
        }
    }

    // To Sunshuai: here is start and finish instead of deadline
    // Another issue: returns only the optimal profit and not the sequence of jobs, so need to modify
//    public static void main(String[] args) {
//        List<Job> list = new ArrayList<>();
//        list.add(new Job("PWDSA", 1, 2, 50));
//        list.add(new Job("HCID", 3, 5, 20));
//        list.add(new Job("OS", 6, 19, 100));
//        list.add(new Job("SPM", 2, 100, 200));
//        list.add(new Job("WAD", 2, 100, 200));
//        List<Job> job = sequenceJobs(list, list.size());
//        for (Job s : job) {
//            System.out.print(s.getId() + " ");
//        }
//        System.out.println();
//    }
//
//    static class Job {
//        String id;
//        int start, end, profit;
//
//        Job(String id, int start, int end, int profit){
//            this.id = id;
//            this.start = start;
//            this.end = end;
//            this.profit = profit;
//        }
//        public String getId() {
//            return id;
//        }
//        public int getStart() {
//            return start;
//        }
//        public int getEnd() {
//            return end;
//        }
//        public int getProfit() {
//            return profit;
//        }
//    }

}
