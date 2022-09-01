public abstract class Job {

    String id;
    private int start;
    private int end;
    private int profit;

    public Job() {
    }

    public Job(String id, int start, int end, int profit) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.profit = profit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDuration() {
        return end - start;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

}