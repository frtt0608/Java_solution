import java.io.*;
import java.util.*;

public class B7562 {
    static int N, sx, sy, tx, ty, minCount;
    static int[][] chess, countMap;

    static class Node {
        int x, y;
        int count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static void moveKnight() {
        int[] dx = {-2,-1,1,2,2,1,-1,-2};
        int[] dy = {1,2,2,1,-1,-2,-2,-1};
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(sx, sy, 0));
        countMap[sx][sy] = 0;

        while(!que.isEmpty()) {
            Node nd = que.poll();

            if(nd.x == tx && nd.y == ty) {
                minCount = countMap[tx][ty];
                return;
            }

            for(int dir=0; dir<8; dir++) {
                int nx = nd.x + dx[dir];
                int ny = nd.y + dy[dir];

                if(isWall(nx, ny)) continue;

                if(countMap[nx][ny] > countMap[nd.x][nd.y] + 1) {
                    countMap[nx][ny] = countMap[nd.x][nd.y] + 1;
                    que.add(new Node(nx, ny, countMap[nx][ny]));
                }
            }
        }
    }

    public static void setCountMap() {
        for(int i=0; i<N; i++) {
            Arrays.fill(countMap[i], Integer.MAX_VALUE);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
    
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            chess = new int[N][N];
            countMap = new int[N][N];
            minCount = 0;

            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            tx = Integer.parseInt(st.nextToken());
            ty = Integer.parseInt(st.nextToken());

            if(sx != tx || sy != ty) {
                setCountMap();
                moveKnight();
            }

            System.out.println(minCount);
        }
    }
}
