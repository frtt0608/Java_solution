// 가스관

import java.util.*;
import java.io.*;

public class Main {
    static int R, C, sx, sy;
    static char[][] map;
    static int[][] dx = {{1,-1},
                        {0,0},
                        {1,-1,0,0},
                        {0,1},
                        {0,-1},
                        {-1,0},
                        {1,0}};
                         
    static int[][] dy = {{0,0},
                        {1,-1},
                        {0,0,1,-1},
                        {1,0},
                        {1,0},
                        {0,-1},
                        {0,-1}};


    static char[] pipes = {'|', '-', '+', '1', '2', '3', '4'};
    static ArrayList<Character> pipeList;

    static boolean wall(int x, int y) {
        if(x<0 || x>=R || y<0 || y>=C) return true;
        return false;
    }

    static boolean check(int x, int y, int nx, int ny, int nblock) {
        if(nx-x == 1) {
            if(nblock == '1' || nblock == '4' || nblock == '-') return false;
        } else if(nx-x == -1) {
            if(nblock == '2' || nblock == '3' || nblock == '-') return false;
        }

        if(ny-y == 1) {
            if(nblock == '1' || nblock == '2' || nblock == '|') return false;
        } else if(ny - y == -1) {
            if(nblock == '3' || nblock == '4' || nblock == '|') return false;
        }

        return true;
    }

    static void gass_BFS(int bx, int by, char sblock) {
        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        visited[sx][sy] = true;
        visited[bx][by] = true;
        que.offer(new Node(bx, by, sblock));
        boolean flag = false;
        int nx = 0;
        int ny = 0;

        while(!que.isEmpty()) {
            Node n = que.poll();
            int x = n.x;
            int y = n.y;
            char block = n.block;
            flag = false;

            if(map[x][y] == 'Z') return;
            
            int idx = pipeList.indexOf(block);
            for(int dir=0; dir<dx[idx].length; dir++) {
                nx = x + dx[idx][dir];
                ny = y + dy[idx][dir];

                if(wall(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == '.') continue;
                if(check(x, y, nx, ny, map[nx][ny])) {
                    que.offer(new Node(nx, ny, map[nx][ny]));
                    visited[nx][ny] = true;
                    flag = true;
                    break;
                }
            }
            
            if(!flag) {
                for(int dir=0; dir<4; dir++) {
                    nx = x + dx[2][dir];
                    ny = y + dy[2][dir];

                    if(wall(nx, ny)) continue;
                    if(visited[nx][ny]) continue;
                    
                    if(map[nx][ny] == '.') {

                    }
                }
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0; i<R; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                map[i][j] = input[j];
                if(map[i][j] == 'M') {
                    sx = i;
                    sy = j;
                }
            }
        }

        pipeList = new ArrayList<>();
        
        for(char chr : pipes) {
            pipeList.add(chr);
        }

        System.out.println(pipeList.indexOf('4'));
        // for(int dir=0; dir<4; dir++) {
        //     int nx = sx + dx[2][dir];
        //     int ny = sy + dy[2][dir];

        //     if(wall(nx, ny)) continue;
        //     if(map[nx][ny] == '.') continue;

        //     gass_BFS(nx, ny, map[nx][ny]);
        // }
    }

    static class Node {
        int x;
        int y;
        char block;

        Node(int x, int y, char block) {
            this.x = x;
            this.y = y;
            this.block = block;
        }
    }
}