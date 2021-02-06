import java.io.*;
import java.util.*;

public class B1018_boolean {
    static int minCount;
    static boolean[][] chess;

    static public void paintColor(int x, int y) {
        int count = 0;
        boolean BW = chess[x][y];

        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                if(BW != chess[i][j]) {
                    count += 1;
                }

                BW = !BW;
            }
            BW = !BW;
        }

        count = Math.min(count, 64-count);
        minCount = Math.min(minCount, count);
    }


    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        chess = new boolean[N][M];
        minCount = 64;

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++) {
                if(input[j].equals("W")) {
                    chess[i][j] = true;
                } else {
                    chess[i][j] = false;
                }
            }
        }

        for(int i=0; i<=N-8; i++) {
            for(int j=0; j<=M-8; j++) {
                paintColor(i, j);
            }
        }

        System.out.println(minCount);
    }
}
