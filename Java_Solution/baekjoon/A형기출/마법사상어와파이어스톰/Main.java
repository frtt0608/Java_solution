import java.util.*;
import java.io.*;

public class Main {
    static int N, Q, maxIceSize;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int[][] ice, adjCnt;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void rotateIceArr(int x, int y, int tSize) {
        int[][] tIce = new int[tSize][tSize];

        for(int i=x; i<x+tSize; i++) {
            for(int j=y; j<y+tSize; j++) {
                tIce[i-x][j-y] = ice[x+tSize+y-j-1][y+i-x];
            }
        }

        for(int i=x; i<x+tSize; i++) {
            for(int j=y; j<y+tSize; j++) {
                ice[i][j] = tIce[i-x][j-y];
            }
        }
    }

    public static void divideIceArr(int L) {
        int tSize = (int) Math.pow(2, L);

        for(int x=0; x<N; x+=tSize) {
            for(int y=0; y<N; y+=tSize) {
                rotateIceArr(x, y, tSize);
            }
        }
    }

    public static boolean isWall(int x, int y) {
        return x<0 || x>=N || y<0 || y>=N;
    }

    public static void checkAdjCnt(int x, int y) {

        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(isWall(nx, ny) || ice[nx][ny] == 0) continue;

            adjCnt[x][y] += 1;           
        }
    }

    public static void initAdjCnt() {
        for(int i=0; i<N; i++) {
            Arrays.fill(adjCnt[i], 0);
        }
    }

    public static void checkAdjIceArr() {
        initAdjCnt();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(ice[i][j] > 0) {
                    checkAdjCnt(i, j);
                }
            }
        }
    }

    public static void meltingIceArr() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(ice[i][j] > 0 && adjCnt[i][j] < 3) {
                    ice[i][j] -= 1;
                }
            }
        }
    }

    public static int getTotalSumIce() {
        int iceSize = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                iceSize += ice[i][j];
            }
        }

        return iceSize;
    }

    public static void searchIceArea(int x, int y, boolean[][] visited) {
        int iceSize = 1;
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Node cur = que.poll();
            x = cur.x;
            y = cur.y;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || ice[nx][ny] == 0) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                iceSize += 1;
                que.offer(new Node(nx, ny));
            }

        }

        maxIceSize = Math.max(maxIceSize, iceSize);
    }

    public static void getMaxIceSize() {
        boolean[][] visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(ice[i][j] == 0 || visited[i][j]) continue;

                searchIceArea(i, j, visited);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);
        Q = Integer.parseInt(st.nextToken());

        ice = new int[N][N];
        adjCnt = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            divideIceArr(L);
            checkAdjIceArr();
            meltingIceArr();
        }
        
        getMaxIceSize();
        System.out.println(getTotalSumIce());
        System.out.println(maxIceSize);
    }
}