package lemory.requests;

import com.google.gson.Gson;
import lemory.schemas.callbacks.BasicCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteStatsRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    private Gson gson = new Gson();
    private String token;

    public DeleteStatsRequest(String token) {
        this.token = token;
    }

    public BasicCallback deleteStats() throws IOException {

        String url = "http://185.168.8.159:3001/api/v1/user/stats/reset";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("DELETE");

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

        BasicCallback callback = gson.fromJson(response.toString(), BasicCallback.class);

        return callback;

    }
}
