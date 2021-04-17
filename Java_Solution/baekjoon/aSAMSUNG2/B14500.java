import java.util.*;
import java.io.*;

public class B14500 {
    static int N, M, maxSumResult;
    static int[] dx={1,0,-1,0}, dy={0,1,0,-1};
    static int[][] map;
    static boolean[][] visited;

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void initVisited() {
        for(int i=0; i<N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    public static void setTetromino(int x, int y, int ox, int oy, int cnt, int sumResult) {
        if(cnt == 4) {
            maxSumResult = Math.max(maxSumResult, sumResult);
            return;
        } else if(cnt == 3) {
            for(int dir=0; dir<4; dir++) {
                int nx = ox + dx[dir];
                int ny = oy + dy[dir];
    
                if(isWall(nx, ny) || visited[nx][ny]) continue;
                
                maxSumResult = Math.max(maxSumResult, sumResult+map[nx][ny]);
            }
        }

        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(isWall(nx, ny) || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            setTetromino(nx, ny, x, y, cnt+1, sumResult+map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
    
    public static void getMaxTetromino() {
        for(int x=0; x<N; x++) {
            for(int y=0; y<M; y++) {
                initVisited();
                visited[x][y] = true;
                setTetromino(x, y, x, y, 1, map[x][y]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getMaxTetromino();
        System.out.println(maxSumResult);
    }
}