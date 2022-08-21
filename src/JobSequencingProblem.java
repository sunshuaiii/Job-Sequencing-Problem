import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class JobSequencingProblem {

    public static void main(String[] args) {

        List<Job> arraylist = new ArrayList<>();

        try {
            Scanner in = new Scanner(new File("jobs.txt"));
            String line;
            while (in.hasNext()) {
                line = in.nextLine();
                String[] words = line.split(",");
                arraylist.add(new Assignment(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Using Greedy Algorithm:");
        System.out.print(
                "Final job sequence following maximum profit: ");
        printJobs(GreedyMethod.sequenceJobs(arraylist, 3));

        System.out.println("\nUsing Branch And Bound Algorithm:");
        System.out.print(
                "Final job sequence following maximum profit: ");
        // printJobs(BranchAndBound.sequenceJobs());
    }

    public static void printJobs(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

}