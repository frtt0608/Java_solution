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
    
    public static void moveNode() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(rx, ry, bx, by, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            rx = node.rx;
            ry = node.ry;
            bx = node.bx;
            by = node.by;
            int moveCnt = node.cnt;

            if(visited[rx][ry][bx][by]) continue;
            visited[rx][ry][bx][by] = true;

            if(moveCnt > 10)
                return;

            for(int dir=0; dir<4; dir++) {
                int nRx = rx;
                int nRy = ry;
                int nBx = bx;
                int nBy = by;

                while(!(map[nRx + dx[dir]][nRy + dy[dir]] == '#')) {
                    nRx += dx[dir];
                    nRy += dy[dir];

                    if(map[nRx][nRy] == 'O')
                        break;
                }

                while(!(map[nBx + dx[dir]][nBy + dy[dir]] == '#')) {
                    nBx += dx[dir];
                    nBy += dy[dir];

                    if(map[nBx][nBy] == 'O')
                        break;
                }

                if(map[nBx][nBy] == 'O')
                    continue;

                if(map[nRx][nRy] == 'O') {
                    minMoveCount = moveCnt + 1;
                    return;
                }

                if(nRx == nBx && nRy == nBy) {
                    switch(dir) {
                        case 0:
                            if(rx < bx) nRx -= 1;
                            else nBx -= 1;
                            break;
                        case 1:
                            if(ry < by) nRy -= 1;
                            else nBy -= 1;
                            break;
                        case 2:
                            if(rx < bx) nBx += 1;
                            else nRx += 1;
                            break;
                        case 3:
                            if(ry < by) nBy += 1;
                            else nRy += 1;
                            break;
                    }
                }

                
                if(!visited[nRx][nRy][nBx][nBy]) {
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
        if(minMoveCount > 10)
            minMoveCount = -1;

        System.out.println(minMoveCount);
    }
}