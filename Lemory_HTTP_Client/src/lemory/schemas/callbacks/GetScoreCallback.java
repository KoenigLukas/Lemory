package lemory.schemas.callbacks;

public class GetScoreCallback {

    private boolean success;
    private String message;
    private int won;
    private int time;

    public GetScoreCallback(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.won = 0;
        this.time = 0;
    }

    public GetScoreCallback(boolean success, int won, int time) {
        this.success = success;
        this.won = won;
        this.time = time;
        this.message = "success";
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getWon() {
        return won;
    }

    public int getTime() {
        return time;
    }
}
