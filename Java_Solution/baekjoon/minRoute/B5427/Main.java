// 불

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
    static int T, w, h, sx, sy, minTime;
    static int[] dx = {1,-1,0,0}, dy={0,0,1,-1};
    static int[][] fireTime;
    static int[][] goTime;
    static char[][] map;
    static boolean[][] fireVisited;
    static boolean[][] goVisited;
    static Queue<Node> fireList;

    static boolean wall(int x, int y) {
        if(x<0 || x>=h || y<0 || y>=w) return true;
        return false;
    }

    static void fire_BFS() {
        int x, y;

        while(!fireList.isEmpty()) {
            Node n = fireList.poll();
            x = n.x;
            y = n.y;
            
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(wall(nx, ny)) continue;
                if(fireVisited[nx][ny]) continue;
                if(map[nx][ny] == '#') continue;
                
                fireTime[nx][ny] = fireTime[x][y] + 1;
                fireVisited[nx][ny] = true;
                fireList.add(new Node(nx, ny));
            }
        }
    }

    static void go_BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(sx, sy));
        goVisited[sx][sy] = true;
        int x, y;

        while(!que.isEmpty()) {
            Node n = que.poll();
            x = n.x;
            y = n.y;

            if(x == 0 || y == 0 || x == h-1 || y == w-1) {
                minTime = Math.min(minTime, goTime[x][y]);
            }

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(wall(nx, ny)) continue;
                if(map[nx][ny] == '#' || map[nx][ny] == '*') continue;
                if(goVisited[nx][ny]) continue;
                if(fireTime[nx][ny] != 0 && fireTime[nx][ny] <= goTime[x][y] + 1) continue; // 불이 먼저 붙은 지점

                goTime[nx][ny] = goTime[x][y] + 1;
                goVisited[nx][ny] = true;
                que.add(new Node(nx, ny));

                if(nx == 0 || ny == 0 || nx == h-1 || ny == w-1) {
                    minTime = Math.min(minTime, goTime[nx][ny]);
                }
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++ ) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
    
            map = new char[h][w];
            fireTime = new int[h][w];
            goTime = new int[h][w];
            fireVisited = new boolean[h][w];
            goVisited = new boolean[h][w];
            fireList = new LinkedList<>();
            minTime = Integer.MAX_VALUE;
    
            for(int i=0; i<h; i++) {
                char[] input = br.readLine().toCharArray();
                for(int j=0; j<w; j++) {
                    map[i][j] = input[j];
                    if(map[i][j] == '@') {
                        sx = i;
                        sy = j;
                    } else if(map[i][j] == '*') {
                        fireList.add(new Node(i, j));
                        fireVisited[i][j] = true;
                    }
                }
            }
            fire_BFS();
            go_BFS();

            if(w == 1 || h == 1) {
                System.out.println(1);
            } else {
                System.out.println(minTime == Integer.MAX_VALUE ? "IMPOSSIBLE":minTime+1);
            }
        }
    }
}