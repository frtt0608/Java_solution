import java.util.*;
import java.io.*;

public class Main {
    static int N, M, minRoute;
    static char[][] map;
    static boolean[][][] visited;

    static class Node {
        int x, y, distance;
        int canSmash;

        Node(int x, int y, int distance, int canSmash) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.canSmash = canSmash;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void searchMinRoute() {
        int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while(!que.isEmpty()) {
            Node nd = que.poll();

            if(nd.x == N-1 && nd.y == M-1) {
                minRoute = nd.distance;
                return;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = nd.x + dx[dir];
                int ny = nd.y + dy[dir];

                if(isWall(nx, ny)) continue;
                if(visited[nd.canSmash][nx][ny]) continue;

                if(map[nx][ny] == '0') {
                    visited[nd.canSmash][nx][ny] = true;
                    que.offer(new Node(nx, ny, nd.distance+1, nd.canSmash));
                } else {
                    if(nd.canSmash == 0) {
                        visited[1][nx][ny] = true;
                        que.offer(new Node(nx, ny, nd.distance+1, 1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[2][N][M];
        map = new char[N][M];
        minRoute = -1;

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        searchMinRoute();

        System.out.println(minRoute);
    }
}