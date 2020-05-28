import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int visited[] = new int[26];
        boolean flag = true;
        
        for(int tc=0; tc<T; tc++) {
            String input = br.readLine();
            visited = new int[26];
            flag = true;
            for(int i=0; i<input.length(); i++) {
                int idx = input.charAt(i) - 'A';
                visited[idx] += 1;
                if(visited[idx] == 3) {
                    if(i+1 >= input.length() || input.charAt(i+1) - 'A' != idx) {
                        flag = false;
                        break;
                    } else {
                        visited[idx] = 0;
                        i++;
                    }
                }
            }
            // System.out.println(Arrays.toString(visited));
            if(!flag) System.out.println("FAKE");
            else System.out.println("OK");
        }
    }
}