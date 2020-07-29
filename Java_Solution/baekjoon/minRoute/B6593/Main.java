// 상범 빌딩

import java.util.*;
import java.io.*;

public class Main {
    static int L, R, C, minRes;
    static char[][][] map;
    static int[][][] minTime;

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int h;
        int time;

        Node(int x, int y, int h, int time) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.time = time;
        }

        @Override
        public int compareTo(Node node) {
            return this.time - node.time;
        }
    }

    static boolean wall(int x, int y, int h) {
        if(x<0 || x>=R || y<0 || y>=C || h<0 || h>=L) return true;
        return false;
    }

    static void BFS(int sx, int sy, int sh) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(sx, sy, sh, 0));
        minTime[sh][sx][sy] = 0;
        int[] dx = {1,-1,0,0}, dy = {0,0,1,-1}, dh = {1,-1};
        int x, y, h, nx, ny, nh;

        while(!que.isEmpty()) {
            Node n = que.poll();
            x = n.x;
            y = n.y;
            h = n.h;

            if(minRes < n.time) continue;

            if(map[h][x][y] == 'E') {
                if(minRes > n.time) {
                    minRes = n.time;
                }
                break;
            }

            for(int dir=0; dir<6; dir++) {
                if(dir<4) {
                    nx = x + dx[dir];
                    ny = y + dy[dir];
                    nh = h;
                } else {
                    nx = x;
                    ny = y;
                    nh = h + dh[5-dir];
                }

                if(wall(nx, ny, nh)) continue;
                if(map[nh][nx][ny] == '#') continue;

                if(minTime[nh][nx][ny] > minTime[h][x][y] + 1) {
                    minTime[nh][nx][ny] = minTime[h][x][y] + 1;
                    que.offer(new Node(nx, ny, nh, n.time + 1));
                }
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L==0 && R==0 && C==0) break;
            
            map = new char[L][R][C];
            minTime = new int[L][R][C];
            int sx = 0;
            int sy = 0;
            int sh = 0;

            for(int h=0; h<L; h++) {

                for(int i=0; i<R; i++) {
                    char[] input = br.readLine().toCharArray();
                    Arrays.fill(minTime[h][i], Integer.MAX_VALUE);

                    for(int j=0; j<C; j++) {
                        map[h][i][j] = input[j];
                        if(map[h][i][j] == 'S') {
                            sx = i;
                            sy = j;
                            sh = h;
                        }
                    }
                }
                br.readLine();
            }

            minRes = Integer.MAX_VALUE;
            BFS(sx, sy, sh);

            if(minRes == Integer.MAX_VALUE) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + minRes + " minute(s).");
            }
        }
    }
}