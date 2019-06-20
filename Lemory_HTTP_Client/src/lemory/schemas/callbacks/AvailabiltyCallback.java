package lemory.schemas.callbacks;

public class AvailabiltyCallback {

    private boolean success;
    private boolean available;
    private String message;

    public AvailabiltyCallback(boolean success, boolean available, String message) {
        this.success = success;
        this.available = available;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getMessage() {
        return message;
    }
}
