// 녹색 옷 입은 애가 젤다지?

import java.util.*;
import java.io.*;

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static int[][] map, minRoute;

    static boolean wall(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= N) return true;
        return false;
    }

    static void BFS() {
        int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
        int x, y, nx, ny;
        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        que.add(new Node(0, 0));
        visited[0][0] = true;
        minRoute[0][0] = map[0][0];

        while(!que.isEmpty()) {
            Node n = que.poll();
            x = n.x;
            y = n.y;

            for(int dir=0; dir<4; dir++) {
                nx = x + dx[dir];
                ny = y + dy[dir];

                if(wall(nx, ny)) continue;
                if(visited[nx][ny]) {
                    if(minRoute[nx][ny] > minRoute[x][y] + map[nx][ny]) {
                        minRoute[nx][ny] = minRoute[x][y] + map[nx][ny];
                        que.add(new Node(nx, ny));
                    }
                } else {
                    visited[nx][ny] = true;
                    minRoute[nx][ny] = minRoute[x][y] + map[nx][ny];
                    que.add(new Node(nx, ny));
                }
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = 1;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N==0) break;

            map = new int[N][N];
            minRoute = new int[N][N];
    
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            BFS();

            System.out.println("Problem " + tc + ": " + minRoute[N-1][N-1]);
            tc += 1;
        }
    }
}