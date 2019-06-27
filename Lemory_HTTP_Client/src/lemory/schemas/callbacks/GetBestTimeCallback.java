package lemory.schemas.callbacks;

public class GetBestTimeCallback {

    private boolean success;
    private String message;
    private int time;

    public GetBestTimeCallback(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.time = 0;
    }

    public GetBestTimeCallback(boolean successn, int time) {
        this.success = success;
        this.time = time;
        this.message = "success";
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getTime() {
        return time;
    }

}
