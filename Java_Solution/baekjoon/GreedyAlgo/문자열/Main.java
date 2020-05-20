import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");

        String input1 = input[0];
        String input2 = input[1];
        int diff = 51;
        int temp_diff = 0;

        for(int i=0; i<=input2.length() - input1.length(); i++) {
            temp_diff = 0;
            for(int j=i; j<input1.length()+i; j++) {
                if(input1.charAt(j-i) != input2.charAt(j)) {
                    temp_diff += 1;
                }
            }
            diff = Math.min(diff, temp_diff);
        }

        System.out.println(diff);
    }
} 