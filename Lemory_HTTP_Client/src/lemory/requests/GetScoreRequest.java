package lemory.requests;

import com.google.gson.Gson;
import lemory.schemas.callbacks.GetScoreCallback;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetScoreRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    private Gson gson = new Gson();
    private String token;

    public GetScoreRequest(String token) {
        this.token = token;
    }

    public GetScoreCallback getScore() throws IOException {

        String url = "http://185.168.8.159:3001/api/v1/user/stats/get";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("token",token);
        post.addHeader("content-type", "application/json");

        HttpResponse response = client.execute(post);
        response.getStatusLine().getStatusCode();

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        GetScoreCallback callback = gson.fromJson(result.toString(), GetScoreCallback.class);

        return callback;

    }

}
