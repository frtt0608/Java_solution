import java.util.*;
import java.io.*;

public class Main {
    static int x, y;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            int dist = y-x;
            int n = (int) Math.sqrt(dist);

            int minCount = 2*(n-1) + 1;
            long currentDist = n*n;

            while(true) {
                if(currentDist < dist) {
                    minCount += 1;
                    currentDist += n;
                } else break;
            }

            System.out.println(minCount);
        }
    }
}