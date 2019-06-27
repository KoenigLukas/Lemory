package lemory.schemas.callbacks;

public class GetTimesPlayedCallback {

    private boolean success;
    private String message;
    private int played;

    public GetTimesPlayedCallback(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.played = 0;
    }

    public GetTimesPlayedCallback(boolean successn, int played) {
        this.success = success;
        this.played = played;
        this.message = "success";
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getPlayed() {
        return played;
    }

}
