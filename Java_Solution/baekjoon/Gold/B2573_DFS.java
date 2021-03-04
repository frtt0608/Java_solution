import java.io.*;
import java.util.*;

public class B2573_DFS {
    static int N, M, firstYear, iceCnt;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int[][] northPole;
    static boolean[][] visited;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void meltingIceBlock(int x, int y) {
        visited[x][y] = true;

        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(visited[nx][ny]) continue;
            if(northPole[nx][ny] == 0) {
                if(northPole[x][y] > 0) {
                    northPole[x][y] -= 1;
                }
            } else {
                meltingIceBlock(nx, ny);
            }
        }
    }

    public static boolean checkIceBlockCount() {
        iceCnt = 0;
        for(int i=0; i<N; i++) {
            Arrays.fill(visited[i], false);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[i][j] || northPole[i][j] == 0)
                    continue;

                if(northPole[i][j] > 0) {
                    if(iceCnt == 1) 
                        return false;
                        
                    meltingIceBlock(i, j);
                    iceCnt += 1;
                }
            }
        }

        if(iceCnt == 0) {
            firstYear = 0;
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        northPole = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                northPole[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            if(!checkIceBlockCount()) {
                break;
            }

            firstYear += 1;
        }

        System.out.println(firstYear);
    }
}