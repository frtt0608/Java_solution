import java.util.*;

import java.io.*;

public class B9184 {
    static int[][][] map;

    public static int w(int a, int b, int c) {
        if(a<=0 || b<=0 || c<=0) {
            return 1;
        } else if(a>20 || b>20 || c>20) {
            return w(20, 20, 20);
        } else if(a<b && b<c) {
            return map[a][b][c-1] + map[a][b-1][c-1] - map[a][b-1][c];
        } else {
            return map[a-1][b][c] + map[a-1][b-1][c] + map[a-1][b][c-1] - map[a-1][b-1][c-1];
        }
    }

    public static void setMap() {
        map = new int[21][21][21];

        for(int a=0; a<21; a++) {
            for(int b=0; b<21; b++) {
                for(int c=0; c<21; c++) {
                    map[a][b][c] = w(a, b, c);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        setMap();

        StringBuilder sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a==-1 && b==-1 && c==-1) break;

            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c)
                .append(") = ").append(w(a, b, c)).append("\n");
        }

        System.out.println(sb.toString());
    }
}