package requests;


import com.google.gson.Gson;
import schemas.callbacks.AvailabiltyCallback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static jdk.internal.net.http.HttpRequestImpl.USER_AGENT;

public class RegisterRequest {

    private boolean user_available;
    private boolean email_available;
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String birth_date;

    public RegisterRequest(boolean user_available, boolean email_available, String username, String password, String email, String first_name, String last_name, String birth_date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
    }

    private void checkUsername() throws Exception {

        String url = "185.168.8.159:3001/api/v1/user/register/checkUsername/"+username;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}
