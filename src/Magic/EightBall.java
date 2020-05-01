package Magic;
import Commands.BasicCommands;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class EightBall {

    public void response(String line, String channel, BasicCommands bc){
        try{
            Scanner answer = new Scanner(new File("text/8ballresponses.txt"));
            if(line.toLowerCase().contains(":jebnai 8ball")){
                if(line.toLowerCase().endsWith(":jebnai 8ball")){
                    bc.write("PRIVMSG", "#" + channel + " :" + "You need to follow up with a question.");
                }else{
                    String reply = "";
                    int x = (int)(Math.random() * (20 - 1) + 1);
                    for(int i = 1; i <= x; i++) {
                        reply = answer.nextLine();
                    }
                    bc.write("PRIVMSG", "#" + channel + " :" + reply);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
