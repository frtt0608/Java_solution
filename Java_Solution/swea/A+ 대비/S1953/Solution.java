import java.util.*;
import java.io.*;

// 1번 상하좌우
// 2번 상하
// 3번 좌우
// 4번 상우
// 5번 하우
// 6번 하좌
// 7번 상좌

public class Solution {
    static int N, M, R, C, L, totalCnt;
    static int[][] tunnel;
    static int[][] pipeX = {{0},
                            {-1,0,1,0},
                            {-1,1},
                            {0,0},
                            {-1,0},
                            {1,0},
                            {1,0},
                            {-1,0}};
    static int[][] pipeY = {{0},
                            {0,1,0,-1},
                            {0,0},
                            {-1,1},
                            {0,1},
                            {0,1},
                            {0,-1},
                            {0,-1}};

    

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void moveTheif() {
        Queue<Node> tq = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        tq.offer(new Node(R, C, 1));
        visited[R][C] = true;

        while(!tq.isEmpty()) {
            Node n = tq.poll();
            int x = n.x;
            int y = n.y;
            int time = n.time;
            // System.out.println(x + ", " + y + ": " + time);
            if(time == L) continue;

            int pi = tunnel[x][y];
            for(int dir=0; dir<pipeX[pi].length; dir++) {
                int nx = x + pipeX[pi][dir];
                int ny = y + pipeY[pi][dir];

                if(isWall(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(tunnel[nx][ny] == 0) continue;
                
                // 연결된 통로인지 확인하기
                // 역으로 이동할 수 있다면 연결된 통로
                int tpi = tunnel[nx][ny];
                for(int tdir=0; tdir<pipeX[tpi].length; tdir++) {
                    int tx = nx + pipeX[tpi][tdir];
                    int ty = ny + pipeY[tpi][tdir];
                    if(isWall(tx, ty)) continue;
                    if(tx == x && ty == y) {
                        totalCnt += 1;
                        visited[nx][ny] = true;
                        tq.offer(new Node(nx, ny, time+1));
                        break;
                    }
                }
            }            
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            tunnel = new int[N][M];
            totalCnt = 1;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    tunnel[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            moveTheif();
            System.out.println("#"+tc+" "+totalCnt);
        }
    }
}

class Node {
    int x;
    int y;
    int time;
    
    Node(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}