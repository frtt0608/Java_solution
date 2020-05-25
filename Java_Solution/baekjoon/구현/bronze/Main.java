import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mon[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String days[] = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        // 1월 1일은 월요일
        String input[] = br.readLine().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);

        int m = 1;
        int d = 1;
        String day = "MON";
        int idx = d;

        while(x != m || y != d) {
            d++;
            idx += 1;
            if(mon[m] < d) {
                m += 1;
                d = 1;
            }
            day = days[idx%days.length];
            // System.out.println(m+" / "+d + ", day:" + day);
        }

        System.out.println(day);
    }
}