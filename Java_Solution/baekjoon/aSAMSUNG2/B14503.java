import java.util.*;
import java.io.*;

public class B14503 {
    static int N, M, cleanCount;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};
    static int[][] map;

    static class Node {
        int x, y;
        int dir;

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    
    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void operatedRobotCleaner(int sX, int sY, int sDir) {
        // 현재 위치 청소
        // 현재 방향 기준 왼쪽 방향 탐색
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(sX, sY, sDir));
        boolean[][] visited = new boolean[N][M];
        int x, y, nx, ny, dir, checkCount;


        while(!que.isEmpty()) {
            checkCount = 0;
            Node cur = que.poll();
            x = cur.x;
            y = cur.y;

            // System.out.println("현재위치: " + x + ", " + y + " >> " + map[x][y]);
            if(!visited[x][y]) {
                visited[x][y] = true;
                cleanCount += 1;
            }
            
            for(int i=1; i<5; i++) {
                dir = (cur.dir + 4 - i)%4;
                nx = x + dx[dir];
                ny = y + dy[dir];

                if(map[nx][ny] == 0 && !visited[nx][ny]) {
                    que.offer(new Node(nx, ny, dir));
                    break;
                } else {
                    checkCount += 1;
                }
            }

            if(checkCount == 4) {
                dir = (cur.dir + 2)%4;
                nx = x + dx[dir];
                ny = y + dy[dir];

                if(map[nx][ny] == 1) {
                    return;
                } else {
                    que.offer(new Node(nx, ny, cur.dir));
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
        
        st = new StringTokenizer(br.readLine());
        int sX = Integer.parseInt(st.nextToken());
        int sY = Integer.parseInt(st.nextToken());
        int sDir = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        operatedRobotCleaner(sX, sY, sDir);
        System.out.println(cleanCount);
    }
}