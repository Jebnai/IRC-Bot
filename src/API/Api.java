package API;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Api {
   private String link;

    public Api(String link){
        this.link = link;
    }

    public String JsonAsString(String inline) throws IOException{
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();

        if(responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        else{
            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()){
                inline+=scanner.nextLine();
            }
            scanner.close();
        }
        return inline;
    }
}
