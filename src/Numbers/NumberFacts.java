package Numbers;

import API.Api;
import Commands.BasicCommands;

import java.io.IOException;
import java.io.PrintWriter;

public class NumberFacts {
    private Api trivia;
    private Api year;
    private Api date;
    private Api math;

    public void returnNumFact(BasicCommands bc, String line, String channel) throws IOException{
        String fact = "";
        if(line.toLowerCase().endsWith(":jebnai random trivia")){
            fetchFact("http://numbersapi.com/random/trivia", fact, bc, channel);
        }else if(line.toLowerCase().endsWith("jebnai random year")){
            fetchFact("http://numbersapi.com/random/year", fact, bc, channel);
        }else if(line.toLowerCase().endsWith("jebnai random date")){
            fetchFact("http://numbersapi.com/random/date", fact, bc, channel);
        }else if(line.toLowerCase().endsWith("jebnai random math")){
            fetchFact("http://numbersapi.com/random/math", fact, bc, channel);
        }
    }

    public void fetchFact(String link, String fact, BasicCommands bc, String channel) throws IOException{
        bc.write("PRIVMSG", "#" + channel + " :" + "Fetching...");
        trivia = new Api(link);
        fact = trivia.JsonAsString(fact);
        bc.write("PRIVMSG", "#" + channel + " :" + fact);
    }
}
