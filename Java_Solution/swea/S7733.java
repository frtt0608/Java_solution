import java.io.*;
import java.util.*;

public class S7733 {
    static int N;
    static int[][] v;
    static int[][] cheese;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};

    static void BFS(int x, int y, int time) {
        Queue<XY> q = new LinkedList<>();
        v[x][y] = 1;
        q.add(new XY(x, y));
        
        while(!q.isEmpty()) {
            int r = q.peek().x;
            int c = q.peek().y;
            q.poll();
            for(int dir=0; dir<4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                if(nr<0 || nc<0 || nr>=N || nc>=N || v[nr][nc] == 1) continue;
                if(cheese[nr][nc]-time > 0 && v[nr][nc] == 0) {
                    // System.out.println((nr) + " " + (nc));
                    v[nr][nc] = 1;
                    q.add(new XY(nr, nc));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = in.nextInt();
            // System.out.println(N);
            cheese = new int[N][N];

            int max = 0;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    cheese[i][j] = in.nextInt();
                    // System.out.print(cheese[i][j]);
                    if(max < cheese[i][j]) {
                        max = cheese[i][j];
                    }
                }
                // System.out.println();
            }
            // System.out.println(max);
            int cnt = 0;
            int max_cnt = 0;
            v = new int[N][N]; // 방문체크

            // 방문체크리스트 초기화
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    v[i][j] = 0;
                }
            }

            for(int k=0; k<=max; k++) {
            // BFS를 돌리면서 치즈덩어리 갯수 카운트
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        if(v[i][j] == 1 || cheese[i][j]-k <= 0) continue;
                        BFS(i, j, k);
                            // for(int a=0; a<N; a++) {
                            //     for(int b=0; b<N; b++) {
                            //         System.out.print(v[i][j]);
                            //     }
                            //     System.out.println();
                            // }
                        cnt += 1;
                    }
                }
                // System.out.println(cnt);
                if(max_cnt < cnt) {
                    max_cnt = cnt;
                }
                cnt=0;

                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        v[i][j] = 0;
                    }
                }
            }
            System.out.println("#" + (tc) + " " + (max_cnt));
        }
    }

    static class XY{
        int x;
        int y;
        XY(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
}