import java.util.*;
import java.io.File;

public class JobSequencingProblem {
    public static void main(String[] args) {
        List<Job> jobList = new ArrayList<>();
        Stack<Job> jobStack = new Stack<>();

        String fileName = askFileName();
        File file = new File(fileName);

        GreedyMethod greedyMethod = new GreedyMethod();
        NaiveRecursiveMethod naiveRecursiveMethod = new NaiveRecursiveMethod();

        if (readFile(file, jobList, jobStack)) {
            System.out.println("Reading data from " + fileName + "...");
            System.out.print("\nFinal job sequence following maximum profit: \n\n");

            // 1. Greedy Method
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Using Greedy Method:\n");
            greedyMethod.printJobs(jobList);
            greedyMethod.sortJobs(jobList);
            System.out.println("\nSorted List :");
            greedyMethod.printJobs(jobList);
            printJobSequencing(greedyMethod.sequenceJobs(jobList));

            // 2. Naive Recursive Method
            System.out.println("\n\n---------------------------------------------------------------------------------");
            System.out.println("Using Naive Recursive Method:\n");
            naiveRecursiveMethod.printJobs(jobStack);
            naiveRecursiveMethod.sortJobs(jobStack);
            System.out.println("\nSorted List :");
            naiveRecursiveMethod.printJobs(jobStack);
            printJobSequencing(naiveRecursiveMethod.sequenceJobs(jobStack));
        }
    }

    public static String askFileName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = in.nextLine() + ".txt";
        in.close();
        return fileName;
    }

    public static boolean readFile(File file, List<Job> jobList, Stack<Job> jobStack) {
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
                if (Integer.parseInt(words[1]) > Integer.parseInt(words[2])) {
                    System.out.println("Haiya, please check your file lah, start time kenot be later than end time lah");
                    in.close();
                    return false;
                }
                jobList.add(new Assignment(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]),
                        Integer.parseInt(words[3])));
                jobStack.push(new Assignment(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]),
                        Integer.parseInt(words[3])));
            }
            in.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void printJobSequencing(List<Job> list) {
        System.out.print("\nJob Sequencing: \n");
        int total = 0;
        Iterator<Job> iterator = list.iterator();
        if (list instanceof ArrayList) {
            while (iterator.hasNext()) {
                Job theJob = iterator.next();
                total += theJob.getProfit();
                System.out.print(theJob.getId() + " - " + theJob.getProfit() + " marks, ");
            }
            System.out.println("Total: " + total + " marks");
        } else if (list instanceof Stack) {
            while (!list.isEmpty()) {
                Job theJob = ( (Stack<Job>) list ).pop();
                total += theJob.getProfit();
                System.out.print(theJob.getId() + " - " + theJob.getProfit() + " marks, ");
            }
            System.out.println("Total: " + total + " marks");
        }
    }

}