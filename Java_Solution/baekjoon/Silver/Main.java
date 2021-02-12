import java.io.*;
import java.util.*;

public class Main {
    static int a, b, c;
    static int[][][] map;
    
    public static int w(int a, int b, int c) {
        if(a<=0 || b<=0 || c<=0) return 1;
        if(a>20 || b>20 || c>20) return w(20, 20, 20);
        if(map[a][b][c] != 0) return map[a][b][c];

        if(a<b && b<c) {
            map[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        } else {
            map[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
        }

        return map[a][b][c];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[21][21][21];
    
        while(true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a==-1 && b==-1 && c==-1) break;

            int result = w(a, b, c);

            System.out.println(String.format("w(%d, %d, %d) = %d", a, b, c, result));
        }
    }
}
