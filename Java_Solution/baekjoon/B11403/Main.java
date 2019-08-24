// 경로 찾기
import java.io.*;
import java.util.*;

public class Main {
    static int[][] table;
    static int N;
    static int[][] visit;
    static int[] v;

    static void BFS(int start, int node) {
        LinkedList<Integer[]> qu = new LinkedList();
        Integer[] temp = new Integer[] {start, node};
        qu.add(temp);

        while(!qu.isEmpty()) {
            int s = qu.peek()[0];
            int n = qu.peek()[1];
            // System.out.println(s + "," + n);
            qu.poll();
            for(int i=0; i<N; i++) {
                if(table[n][i]==1) {
                    if(visit[s][i] == 0) { 
                        visit[s][i] = 1;
                        qu.add(new Integer[] {s,i});
                    }
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
        v = new int[N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                table[i][j] = in.nextInt();
            }
        }

        for(int t=0; t<N; t++) {
            if(v[t]==0) {
                BFS(t, t);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(visit[i][j]);
            }
            System.out.println();
        }
    }
}