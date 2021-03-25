import java.io.*;
import java.util.*;

public class B17406 {
    static int N, M, K, minValue;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int[][] A, map, rcs;
    static boolean[] visited;

    public static int[][] copyMap(int[][] map) {
        int[][] tempMap = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        return tempMap;
    }

    public static void calculateMinValue(int[][] map) {
        int total;

        for(int i=0; i<N; i++) {
            total = 0;
            for(int j=0; j<M; j++) {
                total += map[i][j];
            }
            minValue = Math.min(minValue, total);
        }
    }

    public static void rotateMap(int r, int c, int s) {
        int nx, ny, x, y;
        int initValue;

        // (r-s, c-s) ~ (r+s, c+s)
        while(r-s < r+s) {
            x = r-s;
            y = c-s;
            initValue = map[x][y];

            for(int dir=0; dir<4; dir++) {
                nx = x + dx[dir];
                ny = y + dy[dir];

                while(nx>=r-s && nx<=r+s && ny>=c-s && ny<=c+s) {
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;
                    nx = x + dx[dir];
                    ny = y + dy[dir];
                }
            }

            map[x][y+1] = initValue;
            s -= 1;
        }
    }

    public static void applyCaseInMap(int[] rcsCase) {
        int r, c, s;
        map = copyMap(A);

        for(int idx: rcsCase) {
            r = rcs[idx][0];
            c = rcs[idx][1];
            s = rcs[idx][2];

            rotateMap(r, c, s);
        }

        calculateMinValue(map);
    }

    public static void combinationTotalCase(int idx, int[] rcsCase) {
        if(idx == K) {
            applyCaseInMap(rcsCase);
            return;
        }

        for(int i=0; i<K; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            rcsCase[idx] = i;
            combinationTotalCase(idx+1, rcsCase);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        visited = new boolean[K];
        rcs = new int[K][3];
        minValue = 1000000000;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            rcs[i][0] = Integer.parseInt(st.nextToken())-1;
            rcs[i][1] = Integer.parseInt(st.nextToken())-1;
            rcs[i][2] = Integer.parseInt(st.nextToken());
        }

        combinationTotalCase(0, new int[K]);
        System.out.println(minValue);
    }
}
