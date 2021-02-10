import java.io.*;
import java.util.*;

public class B2206_2dim {

    static int N, M, minRoute;
    static int[][] map, isSmash;

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
        isSmash[0][0] = 0;

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

                if(isSmash[nx][ny] > smashFlag) {
                    if(map[nx][ny] == 0) {
                        isSmash[nx][ny] = smashFlag;
                        que.add(new Node(nx, ny, routeCnt+1, smashFlag));
                    } else {
                        if(smashFlag == 0) {
                            isSmash[nx][ny] = smashFlag + 1;
                            que.add(new Node(nx, ny, routeCnt+1, smashFlag+1));
                        }
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
        map = new int[N][M];
        isSmash = new int[N][M];
        minRoute = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                isSmash[i][j] = Integer.MAX_VALUE;
            }
        }

        moveToMapAndSmash();

        if(minRoute == Integer.MAX_VALUE) {
            minRoute = -1;
        }

        System.out.println(minRoute);
    }
}