package lemory.schemas;

import com.google.common.hash.Hashing;
import lemory.exceptions.EmailMissMatchException;
import lemory.exceptions.DateMissMatchException;

import java.nio.charset.StandardCharsets;

public class RegisterUser {

    private boolean user_available;
    private boolean email_available;
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String birth_date;

    public RegisterUser(boolean email_available, boolean user_available,String username, String password, String email, String first_name, String last_name, String birth_date) throws EmailMissMatchException, DateMissMatchException {

        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        String dateRegex = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$";

        if (!email.matches(emailRegex)) {
            throw new EmailMissMatchException("Wrong Email Syntax");
        }
        if (!birth_date.matches(dateRegex)) {
            throw new DateMissMatchException("Wrong birth_date Syntax");
        }

        this.email_available = email_available;
        this.user_available = user_available;
        this.username = username;
        this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
    }

    public boolean isUser_available() {
        return user_available;
    }

    public boolean isEmail_available() {
        return email_available;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

}