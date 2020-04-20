import API.Api;
import News.NewsInfo;
import com.google.gson.Gson;

import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import Commands.*;
public class Jebnai {
    private static String nickname;
    private static String username;
    private static String realName;
    private static String channel;
    private static PrintWriter out;
    private static Scanner in;
    private static String line;
    private static String jsonString  = "";

    public static void main(String[] args) throws IOException{
        Scanner console = new Scanner(System.in);
        // entering details before connecting
        System.out.print("Enter a nickname: ");
        nickname = console.nextLine();
        System.out.print("Enter a username: ");
        username = console.nextLine();
        System.out.print("Enter a full name: ");
        realName = console.nextLine();
        System.out.print("Enter a channel: ");
        channel = console.nextLine();

        // creating an endpoint for communication between bot and server
        Socket socket = new Socket("selsey.nsqdc.city.ac.uk", 6667);

        // used to format and print objects into text
        out = new PrintWriter(socket.getOutputStream() ,true);
        in = new Scanner(socket.getInputStream());

        BasicCommands basic = new BasicCommands(line, out);
        
        basic.write("NICK", nickname);
        basic.write("USER", username + " 0 * :" + realName);
        basic.write("JOIN ","#" + channel);

        Api api = new Api("https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=cfe0d09f943b481dacef6cef3df82875");
        jsonString = api.JsonAsString(jsonString);
        NewsInfo article = new Gson().fromJson(jsonString, NewsInfo.class);


        while(in.hasNext()){
            line = in.nextLine();
            System.out.println("<<< " + line);
            if(line.startsWith("PING")){
               String thePing = line.split(" ", 2)[1];
               basic.write("PONG", thePing);
            }
            if(line.toLowerCase().endsWith("jebnai latest news")){
                article.returnArticles(basic);
            }
            basic.greetings(line);
            basic.kickMe(line);
            // write("PRIVMSG", "#cyberia no");
        }

        in.close();
        out.close();
        socket.close();
        System.out.println("Complete.");

    }
}