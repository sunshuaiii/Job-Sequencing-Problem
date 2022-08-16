public abstract class Job {

    private int deadline;
    private int profit;

    public Job(int deadline, int profit) {
        this.deadline = deadline;
        this.profit = profit;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

}