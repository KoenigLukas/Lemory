package lemory.requests;

import com.google.gson.Gson;
import lemory.schemas.callbacks.GetScoreCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetScoreRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    private Gson gson = new Gson();
    private String token;

    public GetScoreRequest(String token) {
        this.token = token;
    }

    public GetScoreCallback getScore() throws IOException {

        String url = "http://185.168.8.159:3001/api/v1/user/stats/get";

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

        GetScoreCallback callback = gson.fromJson(response.toString(), GetScoreCallback.class);

        return callback;

    }

}
