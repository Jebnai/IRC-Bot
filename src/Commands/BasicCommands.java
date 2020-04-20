package Commands;
import java.io.PrintWriter;

public class BasicCommands {
    private String line;
    private static PrintWriter out;

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
    public void greetings(String line){
        if(line.toLowerCase().endsWith("hello jebnai")){
            write("PRIVMSG", "#cyberia Hello");
        }
    }

    public void kickMe(String line){
        if(line.toLowerCase().endsWith("kick me")){
            write("PRIVMSG", "jenai :jenai you are a monkey get kicked");
        }
    }
}
