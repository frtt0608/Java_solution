import java.io.*;
import java.util.*;

public class S7733re {
    static int[][] cheese;
    static int[][] v;
    static int N;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = in.nextInt();
            int max_cheese = 0;

            // cheese 이중배열 설정
            cheese = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    cheese[i][j] = in.nextInt();
                    if (max_cheese < cheese[i][j]) {
                        max_cheese = cheese[i][j];
                    }
                }
            }

            // 방문체크 이중배열초기화
            v = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    v[i][j] = 0;
                }
            }
            BFS(2,4,3);
            // 
        }
    }
    // BFS 너비탐색
    static void BFS(int x, int y, int day) {
        int[] dr = {0,0,1,-1};
        int[] dc = {1,-1,0,0};

        Queue<Integer[]> q = new LinkedList<> ();
        Integer[] temp = {x,y};
        q.add(temp);

        while(!q.isEmpty()) {
            int r = q.peek()[0];
            int c = q.peek()[1];
            q.poll();
            System.out.println((r) + " " + (c));
            for(int dir=0; dir<4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                if( nr<0 || nc<0 || nr>=N || nc>=N || v[nr][nc] == 1 ) continue;
                if( v[nr][nc] == 0 && cheese[nr][nc] - day > 0) {
                    temp = new Integer[] {nr, nc};
                    q.add(temp);
                    v[nr][nc] = 1;
                }
            }
        }
        
    }
}