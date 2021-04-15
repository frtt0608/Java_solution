import java.util.*;
import java.io.*;

public class B13460 {
    static int N, M, rx, ry, bx, by, minMoveCount;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static char[][] map;
    static boolean[][][][] visited;

    static class Node implements Comparable<Node> {
        int rx, ry, bx, by;
        int cnt;

        Node(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node node) {
            return this.cnt - node.cnt;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    // need parameter
    // rx, ry, bx, by, cnt
    public static void moveNode() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            rx = node.rx;
            ry = node.ry;
            bx = node.bx;
            by = node.by;
            int moveCnt = node.cnt;
            

            if(map[bx][by] == 'O') continue;
            if(moveCnt > 10)
                continue;

            if(map[rx][ry] == 'O' && map[bx][by] != 'O') {
                minMoveCount = moveCnt;
                break;
            }

            for(int dir=0; dir<4; dir++) {
                int nRx = node.rx;
                int nRy = node.ry;
                int nBx = node.bx;
                int nBy = node.by;

                while(true) {

                    if(map[nRx][nRy] != 'O') {
                        nRx += dx[dir];
                        nRy += dy[dir];
                    }
                    
                    if(map[nBx][nBy] != 'O') {
                        nBx += dx[dir];
                        nBy += dy[dir];
                    }

                    // System.out.println(nRx+", "+nRy + " :: " + nBx + ", " + nBy);

                    if(map[nBx][nBy] == 'O')
                        break;

                    if(map[nRx][nRy] == 'O' && map[nBx][nBy] == '#') {
                        nBx -= dx[dir];
                        nBy -= dy[dir];
                        break;
                    }
                        

                    if(map[nRx][nRy] == '#' && map[nBx][nBy] == '#') {
                        nRx -= dx[dir];
                        nRy -= dy[dir];
                        nBx -= dx[dir];
                        nBy -= dy[dir];
                        break;
                    }

                    if(map[nRx][nRy] == '#') {
                        nRx -= dx[dir];
                        nRy -= dy[dir];
                        if(nRx == nBx && nRy == nBy) {
                            nBx -= dx[dir];
                            nBy -= dy[dir];
                            break;
                        }
                    }

                    if(map[nBx][nBy] == '#') {
                        nBx -= dx[dir];
                        nBy -= dy[dir];
                        if(nRx == nBx && nRy == nBy) {
                            nRx -= dx[dir];
                            nRy -= dy[dir];
                            break;
                        }
                    }
                }

                if(!visited[nRx][nRy][nBx][nBy]) {
                    visited[nRx][nRy][nBx][nBy] = true;
                    pq.offer(new Node(nRx, nRy, nBx, nBy, moveCnt+1));
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
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        minMoveCount = 11;

        for(int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = input[j];

                if(map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if(map[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        moveNode();
        if(minMoveCount == 11)
            minMoveCount = -1;

        System.out.println(minMoveCount);
    }
}