import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input_num = 1;
        int visited[] = new int[10];
        int idx = 0;

        for(int i=0; i<3; i++) {
            input_num *= Integer.parseInt(br.readLine());
        }

        while(input_num > 10) {
            idx = input_num%10;
            visited[idx] += 1;
            input_num /= 10;
        }
        visited[input_num] += 1;

        for(int v:visited) {
            System.out.println(v);
        }
    }
}