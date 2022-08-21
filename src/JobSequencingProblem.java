import java.util.ArrayList;

public class JobSequencingProblem {

    public static void main(String[] args) {

        ArrayList<Job> arraylist = new ArrayList<>();
        arraylist.add(new Assignment("PWDSA", 3, 20));
        arraylist.add(new Assignment("HCID", 1, 30));
        arraylist.add(new Assignment("OS", 3, 20));
        arraylist.add(new Assignment("SPM", 2, 35));
        arraylist.add(new Assignment("WAD", 2, 20));

        System.out.print(
                "Final job sequence following maximum profit: ");
        printJobs();
    }

    public static void printJobs() {
    }

}