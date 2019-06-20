package lemory.schemas;

public class SubmitScore {

    private boolean won;
    private int time;

    public SubmitScore(boolean won, int time) {
        this.won = won;
        this.time = time;
    }

    public boolean isWon() {
        return won;
    }

    public int getTime() {
        return time;
    }
}
