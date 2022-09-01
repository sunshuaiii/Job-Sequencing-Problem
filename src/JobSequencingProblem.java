import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class JobSequencingProblem {
    public static void main(String[] args) {
        List<Job> jobList = new ArrayList<>();
        File file = new File("jobs.txt");

        System.out.print("Final job sequence following maximum profit: \n");
        if (readFile(file, jobList)) {
            // 1. Greedy Method
            System.out.println("Using Greedy Method:");
            printJobs(GreedyMethod.sequenceJobs(jobList, jobList.size()));

            // 2. Naive Recursive Method
            System.out.println("\n\nUsing Naive Recursive Method:");
            printJobs(NaiveRecursiveMethod.sequenceJobs(jobList, jobList.size()));
        }
    }

    public static boolean readFile(File file, List<Job> jobList) {
        try {
            Scanner in = new Scanner(file);
            String line;
            if (!in.hasNext()) {
                System.out.println("File is empty!");
                in.close();
                return false;
            }
            while (in.hasNext()) {
                line = in.nextLine();
                String[] words = line.split(",");
                jobList.add(new Assignment(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]),
                        Integer.parseInt(words[3])));
            }
            in.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void printJobs(List<Job> list) {
        System.out.print("\nJob Sequencing: \n");
        Iterator<Job> iterator = list.iterator();
        while (iterator.hasNext()) {
            Job theJob = iterator.next();
            System.out.print(theJob.getId() + " " + theJob.getProfit() + " marks, ");
        }
        System.out.println();
    }

}