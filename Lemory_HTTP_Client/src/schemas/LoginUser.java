package schemas;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class LoginUser {

    private String username;
    private String password;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
