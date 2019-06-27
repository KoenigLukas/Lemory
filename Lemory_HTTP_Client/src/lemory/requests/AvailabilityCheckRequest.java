package lemory.requests;

import com.google.gson.Gson;
import lemory.exceptions.EmailNotAvailableException;
import lemory.exceptions.UsernameNotAvailableException;
import lemory.schemas.callbacks.AvailabiltyCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AvailabilityCheckRequest {


    private final String USER_AGENT = "Mozilla/5.0";

    public boolean checkEmail(String email) throws Exception {

        String url = "http://185.168.8.159:3001/api/v1/user/register/checkEmail/"+email;


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        AvailabiltyCallback available = new Gson().fromJson(response.toString(), AvailabiltyCallback.class);

        if (!available.isSuccess() || responseCode != 200) {
            throw new IOException();
        }

        if (!available.isAvailable()) {
            throw new EmailNotAvailableException("Email not Available");
        }

        return available.isAvailable();

    }

    public boolean checkUsername(String username) throws Exception {

        String url = "http://185.168.8.159:3001/api/v1/user/register/checkEmail/"+username;


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);


        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        AvailabiltyCallback available = new Gson().fromJson(response.toString(), AvailabiltyCallback.class);

        if (!available.isSuccess() || responseCode != 200) {
            throw new IOException();
        }

        if (!available.isAvailable()) {
            throw new UsernameNotAvailableException("Username not available");
        }

        return true;

    }


}
