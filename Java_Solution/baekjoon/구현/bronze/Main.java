import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String input[] = br.readLine().split(" ");
        int hour = Integer.parseInt(input[0]);
        int minute = Integer.parseInt(input[1]);

        minute -= 45;
        if(minute < 0) {
            hour -= 1;
            minute += 60;
            if(hour < 0) {
                hour = 23;
            }
        }

        System.out.println(hour+" "+minute);
    }
}