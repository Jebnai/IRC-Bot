import API.Api;
import News.NewsInfo;
import Numbers.NumberFacts;
import com.google.gson.Gson;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import Commands.*;

public class Jebnai {
    private static String nickname = "JebnaiBot";
    private static String username = "water";
    private static String realName = "no";
    private static String channel;
    private static PrintWriter out;
    private static Scanner in;
    private static String line;
    private static String jsonString  = "";

    public static void main(String[] args) throws IOException{
        Scanner console = new Scanner(System.in);
        // entering details before connecting
        /* System.out.print("Enter a nickname: ");
        nickname = console.nextLine();
        System.out.print("Enter a username: ");
        username = console.nextLine();
        System.out.print("Enter a full name: ");
        realName = console.nextLine(); */
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
        NumberFacts facts = new NumberFacts();


        while(in.hasNext()){
            line = in.nextLine();
            System.out.println("<<< " + line);
            if(line.endsWith("BST")){
                String time[] = line.split(" ");
                // time = line.substring(line.lastIndexOf(" ")+1);
                basic.write("PRIVMSG", "#" + channel + " :" + time[time.length-2] + time[time.length-1]);
            }
            if(line.startsWith("PING")){
               String thePing = line.split(" ", 2)[1];
               basic.write("PONG", thePing);
            }
            if(line.toLowerCase().endsWith(":jebnai latest news")){
                article.returnArticles(basic, channel);
            }
            facts.returnNumFact(basic, line, channel);
            basic.greetings(line, channel);
            basic.kickMe(line, channel);
            basic.queryTime(line, channel);
            basic.listChannels(line, channel);
        }

        in.close();
        out.close();
        socket.close();
        System.out.println("Complete.");

    }
}
