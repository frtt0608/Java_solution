import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, M, map[][], target[][], minCnt = 0;

    static boolean checkMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static void reverseMap(int x, int y) {
        for(int i=x; i<x+3; i++) {
            for(int j=y; j<y+3; j++) {
                if(map[i][j] == 1) map[i][j] = 0;
                else map[i][j] = 1;
            }
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        target = new int[N][M];

        for(int i=0; i<2*N; i++) {
            String input[] = br.readLine().split("");
            for(int j=0; j<M; j++) {
                if(i<N)
                    map[i][j] = Integer.parseInt(input[j]);
                else
                    target[i-N][j] = Integer.parseInt(input[j]);
            }
        }


        if(N >= 3 && M >= 3) {
            for(int i=0; i<=N-3; i++) {
                for(int j=0; j<=M-3; j++) {
                    if(map[i][j] != target[i][j]) {
                        reverseMap(i, j);
                        minCnt++;
                    }
                }
            }
        }

        if(!checkMap()) minCnt = -1;

        System.out.println(minCnt);
    }
}