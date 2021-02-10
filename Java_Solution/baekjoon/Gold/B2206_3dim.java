import java.io.*;
import java.util.*;

public class B2206_3dim {

    static int N, M, minRoute;
    static char[][] map;

    static class Node {
        int x, y, routeCnt;
        int smashFlag;

        Node(int x, int y, int routeCnt, int smashFlag) {
            this.x = x;
            this.y = y;
            this.routeCnt = routeCnt;
            this.smashFlag = smashFlag;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void moveToMapAndSmash() {
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, 1, 0));
        boolean[][][] visited = new boolean[2][N][M];

        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            int routeCnt = node.routeCnt;
            int smashFlag = node.smashFlag;

            if(x == N-1 && y == M-1) {
                minRoute = Math.min(minRoute, routeCnt);
                return;
            }
        
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny)) continue;
                if(visited[smashFlag][nx][ny]) continue;

                if(map[nx][ny] == '1') {
                    if(smashFlag == 0) {
                        visited[1][nx][ny] = true;
                        que.add(new Node(nx, ny, routeCnt+1, smashFlag+1));
                    }
                } else {
                    visited[smashFlag][nx][ny] = true;
                    que.add(new Node(nx, ny, routeCnt+1, smashFlag));
                }
            }
        }

        if(minRoute == Integer.MAX_VALUE) {
            minRoute = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        minRoute = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        moveToMapAndSmash();
        System.out.println(minRoute);
    }
}