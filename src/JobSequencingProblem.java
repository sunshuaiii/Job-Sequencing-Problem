import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class JobSequencingProblem {

    public static void main(String[] args) {

        List<Job> arraylist = new ArrayList<>();
        arraylist.add(new Assignment("PWDSA", 3, 20));
        arraylist.add(new Assignment("HCID", 1, 30));
        arraylist.add(new Assignment("OS", 3, 20));
        arraylist.add(new Assignment("SPM", 2, 35));
        arraylist.add(new Assignment("WAD", 2, 20));

        System.out.println("Using Greedy Algorithm:");
        System.out.print(
                "Final job sequence following maximum profit: ");
        printJobs(GreedyMethod.sequenceJobs(arraylist, 3));
        System.out.println();

        System.out.println("Using Branch And Bound Algorithm:");
        System.out.print(
                "Final job sequence following maximum profit: ");
        printJobs(BranchAndBound.sequenceJobs(arraylist, 3));
    }

    public static void printJobs(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

}