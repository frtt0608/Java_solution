import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int totalScore = 0;

        for(int i=0; i<5; i++) {
            int score = Integer.parseInt(br.readLine());
            if(score <= 40) {
                totalScore += 40;
            } else totalScore += score;
        }

        System.out.println(totalScore/5);
    }
}