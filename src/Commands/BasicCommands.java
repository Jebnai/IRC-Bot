package Commands;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BasicCommands {
    private String line;
    private static PrintWriter out;
    private List<String> channels = new ArrayList<>();
    private boolean isChannelList = false;

    public BasicCommands(String line, PrintWriter out){
        this.line = line;
        this.out = out;
    }

    // executes IRC commands
    public void write(String command, String message) {
        String full = command + " " + message;
        System.out.println(">>> " + full);
        out.print(full + "\r\n");
        out.flush();
    }

    // reply to greetings
    public void greetings(String line, String channel){
        if(line.toLowerCase().endsWith(":hello jebnai")){
            write("PRIVMSG", "#" + channel + " :" + "Hello");
        }
    }


    // kicks user from channel if bot has privileges
    public void kickMe(String line, String channel){
        String user = line.substring(line.lastIndexOf(" ")+1);
        if(line.toLowerCase().endsWith(":jebnai kick " + user)){
            write("KICK", "#" + channel + " :" + user);
        }
    }

    // returns the local time of the server
    public void queryTime(String line, String channel){
        String time;
        if(line.toLowerCase().endsWith(":jebnai time")) {
            write("TIME", "selsey.nsqdc.city.ac.uk");
        }
    }

    public void listChannels(String line, String channel){
        if(line.toLowerCase().endsWith(":jebnai channels")){
            write("LIST", "");
        }
    }

    public void queryNames(String line, String channel){
        if(line.toLowerCase().endsWith(":jebnai all names")){
            write("NAMES", "");
        }else if(line.toLowerCase().endsWith(":jebnai names here")){
            write("NAMES", "#" + channel);
        }
    }
}
