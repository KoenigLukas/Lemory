import exceptions.DateMissMatchException;
import exceptions.EmailMissMatchException;
import requests.AvailabilityCheck;
import requests.LoginRequest;
import requests.RegisterRequest;
import schemas.LoginUser;
import schemas.RegisterUser;
import schemas.callbacks.AvailabiltyCallback;
import schemas.callbacks.LoginCallback;

import java.io.IOException;

public class Examples {

    public static void main(String[] args) {
        examples();
    }

    public static void examples() {


        /*
            REGISTER USER
         */

//        AvailabilityCheck check = new AvailabilityCheck();
//
//
//        boolean email_available = false;
//        boolean username_available = false;
//        try {
//            email_available = check.checkEmail("tes34t@test.com");
//            username_available = check.checkUsername("Hanz");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        RegisterUser registerUser = null;
//        try {
//
//             registerUser = new RegisterUser(email_available, username_available,"Hans","12390","tes34t@test.com","hugo","heinz","2001-12-31");
//        } catch (EmailMissMatchException e) {
//            e.printStackTrace();
//        } catch (DateMissMatchException e) {
//            e.printStackTrace();
//        }
//
//        RegisterRequest register = null;
//        try {
//            register = new RegisterRequest(registerUser);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        LoginCallback registerCallback = null;
//        try {
//            registerCallback = register.registerUser();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(!registerCallback.isSuccess()){
//            System.out.println(registerCallback.getMessage()); //ERROR MESSAGE
//        }
//        if(registerCallback.isSuccess()){
//            System.out.println(registerCallback.getToken()); //LOGIN TOKEN
//        }

        /*
            LOGIN USER
         */

        LoginUser loginUser = new LoginUser("Hans","12390");

        LoginRequest loginRequest = new LoginRequest(loginUser);

        LoginCallback loginCallback = null;
        try {
            loginCallback = loginRequest.loginUser();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!loginCallback.isSuccess()){
            System.out.println(loginCallback.getMessage()); //ERROR MESSAGE
        }
        if(loginCallback.isSuccess()){
            System.out.println(loginCallback.getToken()); //LOGIN TOKEN
        }

    }


}
