import com.google.common.hash.Hashing;
import exceptions.DateMissMatchException;
import exceptions.EmailMissMatchException;
import requests.AvailabilityCheck;
import requests.RegisterRequest;
import schemas.RegisterUser;
import schemas.callbacks.LoginCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Register {

    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String birth_date;

    public Register(String username, String password, String email, String first_name, String last_name, String birth_date) {
        this.username = username;
        this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        registeruser3();
    }


    private void registeruser3(){
        AvailabilityCheck check = new AvailabilityCheck();

        boolean email_available = false;
        try {
            email_available = check.checkEmail(this.email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean username_available = false;
        try {
            username_available = check.checkUsername(this.username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RegisterUser user = null;
        try {
            user = new RegisterUser(email_available,username_available,this.username,this.password,this.email,this.first_name,this.last_name,this.birth_date);
        } catch (EmailMissMatchException e) {
            e.printStackTrace();
        } catch (DateMissMatchException e) {
            e.printStackTrace();
        }

        RegisterRequest register = null;
        try {
            register = new RegisterRequest(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginCallback login = null;
        try {
            login = register.registerUser();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!login.isSucess()){
            System.out.println(login.getMessage()); //ERROR MESSAGE
        }
        if(login.isSucess()){
            System.out.println(login.getToken()); //LOGIN TOKEN
        }
    }
}
