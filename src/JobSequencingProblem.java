import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class JobSequencingProblem {
    public static void main(String[] args) {

        List<Job> jobList = new ArrayList<>();
        File file = new File("jobs.txt");

        readFile(file, jobList);

        // 1. Greedy Method
        System.out.println("Using Greedy Method:");
        System.out.print("Final job sequence following maximum profit: ");
        printJobs(GreedyMethod.sequenceJobs(jobList, jobList.size()));

        // 1. Naive Recursive Method
        System.out.println("\n\nUsing Naive Recursive Method:");
        System.out.print("Final job sequence following maximum profit: ");
        printJobs(NaiveRecursiveMethod.sequenceJobs(jobList, jobList.size()));
    }

    public static void readFile(File file, List<Job> arraylist) {
        try {
            Scanner in = new Scanner(file);
            String line;
            if (!in.hasNext()) {
                System.out.println("File is empty!");
                return;
            }
            while (in.hasNext()) {
                line = in.nextLine();
                String[] words = line.split(",");
                arraylist.add(new Assignment(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3])));
            }
        } catch (FileNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void printJobs(List<Job> list) {
        Iterator<Job> iterator = list.iterator();
        while(iterator.hasNext()){
            Job theJob = iterator.next();
            System.out.print(theJob.getId() + " " + theJob.getProfit() + " marks, ");
        }
        System.out.println();
    }

}