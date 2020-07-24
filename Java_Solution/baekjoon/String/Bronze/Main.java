import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        int time = 0;

        for(int i=0; i<input.length; i++) {
            int num = input[i] - 'A';
            System.out.println(num + ", " + time);

            if(num >= 0 && num <=14) {
                time += (int)num/3 +2 +1;
            } else if(num >= 15 && num <= 18) {
                time += 8;
            } else if(num >= 19 && num <= 21) {
                time += 9;
            } else {
                time += 10;
            }
        }

        System.out.println(time);
    }
}