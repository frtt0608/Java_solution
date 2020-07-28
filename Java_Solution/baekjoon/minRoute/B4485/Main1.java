// 녹색 옷 입은 애가 젤다지?

import java.util.*;
import java.io.*;

public class Main1 {
    static int N, minRupee;
    static int[][] map, minRoute;

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int rupee;
    
        Node(int x, int y, int rupee) {
            this.x = x;
            this.y = y;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Node node) {
            return this.rupee - node.rupee;
        }
    }

    static boolean wall(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= N) return true;
        return false;
    }

    static void BFS() {
        int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
        int x, y, nx, ny, rupee;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, map[0][0]));
        minRoute[0][0] = map[0][0];

        while(!que.isEmpty()) {
            Node n = que.poll();
            x = n.x;
            y = n.y;
            rupee = n.rupee;

            if(minRupee < rupee) continue;

            if(x == N-1 && y == N-1) {
                minRupee = Math.min(minRupee, rupee);
                continue;
            }

            for(int dir=0; dir<4; dir++) {
                nx = x + dx[dir];
                ny = y + dy[dir];

                if(wall(nx, ny)) continue;
                if(minRoute[nx][ny] > minRoute[x][y] + map[nx][ny]) {
                    minRoute[nx][ny] = minRoute[x][y] + map[nx][ny];
                    que.add(new Node(nx, ny, minRoute[nx][ny]));
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
                Arrays.fill(minRoute[i], Integer.MAX_VALUE);

                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minRupee = Integer.MAX_VALUE;
            BFS();

            System.out.println("Problem " + tc + ": " + minRupee);
            tc += 1;
        }
    }
}