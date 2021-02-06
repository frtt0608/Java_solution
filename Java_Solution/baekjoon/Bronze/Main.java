import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        int res = 0;

        for(int i=1; i<MAX; i++) {
            int constructor = i;
            int num = i;
            while(num != 0) {
                constructor += num%10;
                num /= 10;
            }

            if(constructor == N) {
                res = i;
                break;
            }
        }
        
        System.out.println(res);
    }
}