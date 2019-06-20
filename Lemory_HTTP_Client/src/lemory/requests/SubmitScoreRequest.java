package lemory.requests;

import com.google.gson.Gson;
import lemory.schemas.SubmitScore;
import lemory.schemas.callbacks.BasicCallback;
import lemory.schemas.callbacks.LoginCallback;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubmitScoreRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    private Gson gson = new Gson();
    private SubmitScore score;
    private String token;

    public SubmitScoreRequest(SubmitScore score, String token) {
        this.score = score;
        this.token = token;
    }

    public BasicCallback sbumitScore() throws IOException {

        String url = "http://185.168.8.159:3001/api/v1/user/stats/put";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("token",token);
        post.addHeader("content-type", "application/json");

        StringEntity params = new StringEntity(gson.toJson(score), ContentType.APPLICATION_JSON);

        post.setEntity(params);

        HttpResponse response = client.execute(post);
        response.getStatusLine().getStatusCode();

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        BasicCallback callback = gson.fromJson(result.toString(), BasicCallback.class);

        return callback;

    }

}
