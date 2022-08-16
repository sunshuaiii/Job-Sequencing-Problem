public class JobSequencingProblem {

    public static void main(String[] args) {
        Job PWDSA = new Assignment(3, 20);
        Job HCID = new Assignment(1, 30);
        Job OS = new Assignment(3, 20);
        Job SPM = new Assignment(2, 35);
        Job WAD = new Assignment(2, 20);

        // Expected results
        System.out.println("Final Job Sequence  : HCID, SPM, OS");
        System.out.println("Incompleted Jobs    : WAD, PWDSA");
        System.out.println("Total Marks         : 85 / 125");
    }

}