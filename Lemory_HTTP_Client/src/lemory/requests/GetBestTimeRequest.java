package lemory.requests;

import com.google.gson.Gson;
import lemory.exceptions.UsernameNotAvailableException;
import lemory.schemas.callbacks.AvailabiltyCallback;
import lemory.schemas.callbacks.GetBestTimeCallback;
import lemory.schemas.callbacks.GetScoreCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetBestTimeRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    private Gson gson = new Gson();
    private String token;

    public GetBestTimeRequest(String token) {
        this.token = token;
    }

    public GetBestTimeCallback getBestTime() throws IOException {

        String url = "http://185.168.8.159:3001/api/v1/user/stats/getbest";


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("token", token);


        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        GetBestTimeCallback callback = gson.fromJson(response.toString(), GetBestTimeCallback.class);

        return callback;
    }

}
