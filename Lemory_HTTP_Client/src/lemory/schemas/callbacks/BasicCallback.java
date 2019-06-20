package lemory.schemas.callbacks;

public class BasicCallback {

    private boolean success;
    private String message;

    public BasicCallback(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
