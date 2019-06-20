package schemas.callbacks;

public class LoginCallback {

    private boolean success;
    private String token;
    private String message;

    public LoginCallback(boolean success, String string) {
        this.success = success;
        if(success){
            this.token = string;
            this.message = null;
        }
        else{
            this.message = string;
            this.token = null;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

}
