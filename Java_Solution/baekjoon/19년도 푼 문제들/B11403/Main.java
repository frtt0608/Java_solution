// 경로 찾기
import java.io.*;
import java.util.*;

public class Main {
    static int[][] table;
    static int N;
    static int[][] visit;
    static int[] v;

    static void DFS(int s) {
        for(int i=0; i<N; i++) {
            if(table[s][i]==1) {
                if(v[i]==0) { 
                    v[i] = 1;
                    DFS(i);
                }
            }
        }
    }
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        table = new int[N][N];
        visit = new int[N][N];
        

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                table[i][j] = in.nextInt();
            }
        }

        for(int x=0; x<N; x++) {
            v = new int[N];
            DFS(x);
            for(int y=0; y<N; y++) {
                visit[x][y] = v[y];
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(visit[i][j] + " ");
            }
            System.out.println();
        }
    }
}