package schemas;

public class registerUser {

    private boolean user_available;
    private boolean email_available;
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String birth_date;


    public boolean isUser_available() {
        return user_available;
    }

    public void setUser_available(boolean user_available) {
        this.user_available = user_available;
    }

    public boolean isEmail_available() {
        return email_available;
    }

    public void setEmail_available(boolean email_available) {
        this.email_available = email_available;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }
}
