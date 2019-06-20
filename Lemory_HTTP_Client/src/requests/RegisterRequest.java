package requests;

import com.google.gson.Gson;
import exceptions.EmailNotAvailableException;
import exceptions.UsernameNotAvailableException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import schemas.RegisterUser;
import schemas.callbacks.LoginCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RegisterRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    private RegisterUser user;
    private Gson gson = new Gson();

    public RegisterRequest(RegisterUser user) throws Exception {
        this.user = user;

        if(!user.isEmail_available()){
            throw new EmailNotAvailableException("Email not Available");
        }
        if(!user.isUser_available()){
            throw new UsernameNotAvailableException("Username not Available");
        }
    }

    public LoginCallback registerUser() throws IOException {

        String url = "http://185.168.8.159:3001/api/v1/user/register";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", USER_AGENT);
        post.addHeader("content-type", "application/json");

        StringEntity params = new StringEntity(gson.toJson(user),ContentType.APPLICATION_JSON);

        post.setEntity(params);

        HttpResponse response = client.execute(post);
        response.getStatusLine().getStatusCode();

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        LoginCallback login = gson.fromJson(result.toString(), LoginCallback.class);

        return login;

    }

}
