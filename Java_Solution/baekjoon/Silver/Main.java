import java.io.*;
import java.util.*;

public class Main {
    static final int SIZE = 20;
    static int a, b, c;
    static int[][][] map;
    
    public static int w(int a, int b, int c) {
        if(a<=0 || b<=0 || c<=0) 
            return 1;
        if(a>20 || b>20 || c>20) 
            return w(20, 20, 20);
        if(a<b && b<c) 
            return map[a][b][c-1] + map[a][b-1][c-1] - map[a][b-1][c];
        
        return map[a-1][b][c] + map[a-1][b-1][c] + map[a-1][b][c-1] - map[a-1][b-1][c-1];
    }

    public static void setMap() {
        map = new int[SIZE+1][SIZE+1][SIZE+1];

        for(int a=0; a<=SIZE; a++) {
            for(int b=0; b<=SIZE; b++) {
                for(int c=0; c<=SIZE; c++) {
                    map[a][b][c] = w(a, b, c);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        setMap();

        while(true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a==-1 && b==-1 && c==-1) break;

            int result = w(a, b, c);
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c)
                .append(") = ").append(result).append("\n");
        }

        System.out.println(sb.toString());
    }
}
