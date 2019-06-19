package schemas.callbacks;

public class LoginCallback {

    private boolean sucess;
    private String token;
    private String message;

    public LoginCallback(boolean sucess, String string) {
        this.sucess = sucess;
        if(sucess){
            this.token = string;
            this.message = null;
        }
        else{
            this.message = string;
            this.token = null;
        }
    }

    public boolean isSucess() {
        return sucess;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
