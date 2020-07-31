// 가스관

import java.util.*;
import java.io.*;

public class Main {
    static int R, C, sx, sy, resX, resY, pipeCnt;
    static char resPipe;
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
    static boolean[][] visited;

    static boolean wall(int x, int y) {
        if(x<0 || x>=R || y<0 || y>=C) return true;
        return false;
    }

    static boolean check(int x, int y, int nx, int ny, char nblock) {
        if(nx - x == 1) {
            if(nblock == '1' || nblock == '4' || nblock == '-') return false;
        } else if(nx - x == -1) {
            if(nblock == '2' || nblock == '3' || nblock == '-') return false;
        }

        if(ny - y == 1) {
            if(nblock == '1' || nblock == '2' || nblock == '|') return false;
        } else if(ny - y == -1) {
            if(nblock == '3' || nblock == '4' || nblock == '|') return false;
        }

        return true;
    }

    static void findPipe(int x, int y) {
        Set<String> dirSet = new HashSet<>();

        for(int dir=0; dir<4; dir++ ) {
            int nx = x + dx[2][dir];
            int ny = y + dy[2][dir];

            if(wall(nx,ny)) continue;
            if(map[nx][ny] == '.') continue;

            // System.out.println(map[nx][ny] + ", " + dir);
            if(dir == 0) {
                if(map[nx][ny] == '2' || map[nx][ny] == '3' || map[nx][ny] == '|' || map[nx][ny] == '+') {
                    dirSet.add("D");
                }
            } else if(dir == 1) {
                if(map[nx][ny] == '1' || map[nx][ny] == '4' || map[nx][ny] == '|' || map[nx][ny] == '+') {
                    dirSet.add("U");
                }
            } else if(dir == 2) {
                if(map[nx][ny] == '3' || map[nx][ny] == '4' || map[nx][ny] == '-' || map[nx][ny] == '+') {
                    dirSet.add("R");
                }
            } else if(dir == 3) {
                if(map[nx][ny] == '1' || map[nx][ny] == '2' || map[nx][ny] == '-' || map[nx][ny] == '+') {
                    dirSet.add("L");
                }
            }
        }

        if(dirSet.size() == 4) {
            resPipe = '+';
        } else {
            if(dirSet.contains("U") && dirSet.contains("D")) {
                resPipe = '|';
            } else if(dirSet.contains("U") && dirSet.contains("R")) {
                resPipe = '2';
            } else if(dirSet.contains("U") && dirSet.contains("L")) {
                resPipe = '3';
            } else if(dirSet.contains("D") && dirSet.contains("R")) {
                resPipe = '1';
            } else if(dirSet.contains("D") && dirSet.contains("L")) {
                resPipe = '4';
            } else if(dirSet.contains("R") && dirSet.contains("L")) {
                resPipe = '-';
            }
        }
    }

    static void gas_BFS(int bx, int by, char sblock) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(bx, by, sblock));

        visited[sx][sy] = true;
        visited[bx][by] = true;
        int nx = 0;
        int ny = 0;

        while(!que.isEmpty()) {
            Node n = que.poll();
            int x = n.x;
            int y = n.y;
            char block = n.block;

            if(map[x][y] == 'Z') continue;
            
            int idx = pipeList.indexOf(block);

            for(int dir=0; dir<dx[idx].length; dir++) {
                nx = x + dx[idx][dir];
                ny = y + dy[idx][dir];

                if(wall(nx, ny)) continue;
                if(visited[nx][ny]) continue;

                if(map[nx][ny] == '.') {
                    findPipe(nx, ny);
                    resX = nx + 1;
                    resY = ny + 1;
                    return;
                }

                if(check(x, y, nx, ny, map[nx][ny])) {
                    que.offer(new Node(nx, ny, map[nx][ny]));
                    visited[nx][ny] = true;
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
        visited = new boolean[R][C];
        pipeList = new ArrayList<>();
        
        for(char chr : pipes) {
            pipeList.add(chr);
        }

        pipeCnt = 1;

        for(int i=0; i<R; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                map[i][j] = input[j];
                if(pipeList.contains(map[i][j])) pipeCnt += 1;
                if(map[i][j] == 'M') {
                    sx = i;
                    sy = j;
                }
            }
        }

        for(int dir=0; dir<4; dir++) {
            int nx = sx + dx[2][dir];
            int ny = sy + dy[2][dir];

            if(wall(nx, ny)) continue;
            if(map[nx][ny] == '.') continue;

            gas_BFS(nx, ny, map[nx][ny]);
            break;
        }

        System.out.println(resX + " " + resY + " " + resPipe);
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