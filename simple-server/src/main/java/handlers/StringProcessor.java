package handlers;

/**
 * Created by dylan on 9/13/2017.
 */
public class StringProcessor {
    public static StringProcessor SINGLETON = new StringProcessor();

    private StringProcessor(){}

    public String toLowerCase(String input){
        return input.toLowerCase();
    }

    public String trim(String input){
        return input.trim();
    }

    public int parseInteger(String input){
        return Integer.parseInt(input);
    }
}
