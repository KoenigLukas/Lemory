import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadToken {

    public String token() throws IOException {
        String token="1";
        String line;
        BufferedReader br = new BufferedReader(new FileReader("token.tok"));
        token=br.readLine();
        br.close();

        return token;
    }

}
