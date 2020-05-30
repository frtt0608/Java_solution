import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, M, Front[], Right[], maxMap[][], minMap[][];
    static int minCnt = 0, maxCnt = 0;
    
    static void findmaxMap() {
        for(int i=0; i<M; i++) {
            for(int j=N-1; j>=0; j--) {
                if(Right[i] <= Front[j]) {
                    maxMap[M-1-i][j] = Right[i];
                } else {
                    maxMap[M-1-i][j] = Front[j];
                }
                maxCnt += maxMap[M-1-i][j];
            }
        }

        for(int i=0; i<M; i++) {
            System.out.println(Arrays.toString(maxMap[i]));
        }
        System.out.println("maxCnt: " + maxCnt);
    }

    static void findminMap() {
        boolean Front_v[] = new boolean[N];
        boolean Right_v[] = new boolean[M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(maxMap[M-j-1][i] == Front[i]) {
                    if(maxMap[M-j-1][i] == Right[j]) {
                        minMap[M-j-1][i] = Front[i];
                        minCnt += Front[i];
                        Right_v[j] = true;
                        Front_v[i] = true;
                    } else if(maxMap[M-j-1][i] < Right[j]) {
                        if(Front_v[i]) continue;
                        else {
                            for(int k=i; k<N; k++) {
                                if(maxMap[M-j-1][k] == Right[j]) {
                                    minMap[M-j-1][i] = Front[i];
                                    minCnt += minMap[M-j-1][i];
                                    Front_v[i] = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i=0; i<M; i++) {
            System.out.println(Arrays.toString(minMap[i]));
        }
        System.out.println("minCnt: " + minCnt);
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        Front = new int[N];
        Right = new int[M];
        maxMap = new int[M][N];
        minMap = new int[M][N];

        for(int i=0; i<N; i++) {
            Front[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<M; i++) {
            Right[i] = Integer.parseInt(br.readLine());
        }

        findmaxMap();
        findminMap();
    }
}