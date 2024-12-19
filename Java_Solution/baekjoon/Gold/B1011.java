import java.io.*;
import java.util.*;

public class B1011 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int distance = y-x;
            int count = getMinOperationCount(distance);
            System.out.println(count);
        }

    }

    public static int getMinOperationCount(int distance) {

        if(distance == 1) return 1;
        if(distance == 2) return 2;
        if(distance <= 4) return 3;

        int count = 0;
        int max = (int) Math.sqrt(distance);

        if(max == Math.sqrt(distance)) {
            count = 2*max - 1;

        } else if(distance > max*max && distance <= max*max + max) {
            count = 2*max;
            
        } else {
            count = 2*max + 1;
        }

        return count;
    }
}
 