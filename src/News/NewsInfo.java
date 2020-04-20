package News;
import java.util.List;
import Commands.*;

import static java.lang.String.valueOf;

public class NewsInfo {
    private List<Articles> articles;
    private String jsonString;
    private String link = "https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=cfe0d09f943b481dacef6cef3df82875";

    public void returnArticles(BasicCommands basic){
        basic.write("PRIVMSG", "#cyberia :HERE ARE THE LATEST HEADLINES");
        for(int i = 0; i < 6; i++){
            basic.write("PRIVMSG", "#cyberia :" + valueOf(i + 1) + ") " + articles.get(i).title);
            basic.write("PRIVMSG", "#cyberia :" + articles.get(i).description);
        }
    }
}
