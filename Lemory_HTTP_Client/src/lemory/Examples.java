package lemory;

import lemory.exceptions.DateMissMatchException;
import lemory.exceptions.EmailMissMatchException;
import lemory.requests.*;
import lemory.schemas.LoginUser;
import lemory.schemas.RegisterUser;
import lemory.schemas.SubmitScore;
import lemory.schemas.callbacks.*;

import java.io.IOException;

public class Examples {

    public static void main(String[] args) {
        examples();
    }

    public static void examples() {


        /*
            REGISTER USER
         */

        AvailabilityCheckRequest check = new AvailabilityCheckRequest();

        boolean email_available = false;
        boolean username_available = false;
        try {
            email_available = check.checkEmail("tes34t@test.com");
            username_available = check.checkUsername("Hanz");
        } catch (Exception e) {
            e.printStackTrace();
        }

        RegisterUser registerUser = null;
        try {

             registerUser = new RegisterUser(email_available, username_available,"Hans","12390","tes34t@test.com","hugo","heinz","2001-12-31");
        } catch (EmailMissMatchException e) {
            e.printStackTrace();
        } catch (DateMissMatchException e) {
            e.printStackTrace();
        }

        RegisterRequest register = null;
        try {
            register = new RegisterRequest(registerUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginCallback registerCallback = null;
        try {
            registerCallback = register.registerUser();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!registerCallback.isSuccess()){
            System.out.println(registerCallback.getMessage()); //ERROR MESSAGE
        }
        if(registerCallback.isSuccess()){
            System.out.println(registerCallback.getToken()); //LOGIN TOKEN
        }

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

        /*
               Submit Score
         */

        String token = "token"; //TOKEN RECEIVED FORM LOGIN

        SubmitScore score = new SubmitScore(true,123);

        SubmitScoreRequest submitScore = new SubmitScoreRequest(score, token);

        BasicCallback submitCallback = null;
        try {
            submitCallback = submitScore.sbumitScore();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Success:"+submitCallback.isSuccess() + " Message:" + submitCallback.getMessage());


        /*
            Get Score
         */

        GetScoreRequest scoreRequest = new GetScoreRequest("token");

        GetScoreCallback getScoreCallback = null;

        try {
            getScoreCallback = scoreRequest.getScore();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!getScoreCallback.isSuccess()){
            System.out.println("Success:"+getScoreCallback.isSuccess()+" Message:"+getScoreCallback.getMessage());
        }
        else{
            System.out.println("Success:"+getScoreCallback.isSuccess()+" Win percentage:"+getScoreCallback.getWon()+" Average Time:"+getScoreCallback.getTime());
        }


        /*
            Get Times Played
         */

        GetTimesPlayedRequest timesPlayedRequest = new GetTimesPlayedRequest("token");

        GetTimesPlayedCallback timesPlayedCallback = null;


        try {
            timesPlayedCallback = timesPlayedRequest.getTimesPlayed();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(!getScoreCallback.isSuccess()){
            System.out.println("Success:"+timesPlayedCallback.isSuccess()+" Message:"+timesPlayedCallback.getMessage());
        }
        else{
            System.out.println("Success:"+timesPlayedCallback.isSuccess()+" Times Played:"+timesPlayedCallback.getPlayed());
        }


        /*
            Get Best Time
         */

        GetBestTimeRequest bestTimeRequest = new GetBestTimeRequest("token");

        GetBestTimeCallback bestTimeCallback = null;

        try {
            bestTimeCallback = bestTimeRequest.getBestTime();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!getScoreCallback.isSuccess()){
            System.out.println("Success:"+bestTimeCallback.isSuccess()+" Message:"+bestTimeCallback.getMessage());
        }
        else{
            System.out.println("Success:"+bestTimeCallback.isSuccess()+" Best Time:"+bestTimeCallback.getTime());
        }

    }


}
