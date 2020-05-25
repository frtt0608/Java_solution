import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = 0;
        char chrArr[][] = new char[5][];
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<5; i++) {
            chrArr[i] = br.readLine().toCharArray();
            length = Math.max(length, chrArr[i].length);
        }

        for(int i=0; i<length; i++) {
            for(int j=0; j<5; j++) {
                if(chrArr[j].length <= i) continue;
                else if(chrArr[j][i] == ' ') continue;
                sb.append(chrArr[j][i]);
            }
        }

        System.out.println(sb);
    }
}