import java.io.*;
import java.util.*;

public class B7569 {
    static int M, N, H, allTime;
    static int[][][] tomatoFarm;
    static Queue<Node> tomatoQue;

    static class Node {
        int x, y, h;
        int time;

        Node(int x, int y, int h, int time) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.time = time;
        }
    }

    public static void checkTomatoFarm() {
        for(int h=0; h<H; h++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(tomatoFarm[i][j][h] == 0) {
                        allTime = -1;
                        return;
                    }
                }
            }
        }
    }

    public static boolean isWall(int x, int y, int h) {
        if(x<0 || x>=N || y<0 || y>=M || h<0 || h>=H) return true;
        return false;
    }

    public static void managedTomatoFarm() {
        int[] dx = {1,0,-1,0,0,0};
        int[] dy = {0,1,0,-1,0,0};
        int[] dh = {0,0,0,0,1,-1};

        while(!tomatoQue.isEmpty()) {
            Node node = tomatoQue.poll();
            int x = node.x;
            int y = node.y;
            int h = node.h;
            int time = node.time;
            
            allTime = Math.max(allTime, time);

            for(int dir=0; dir<6; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                int nh = h + dh[dir];

                if(isWall(nx, ny, nh)) continue;
                
                if(tomatoFarm[nx][ny][nh] == 0) {
                    tomatoFarm[nx][ny][nh] = 1;
                    tomatoQue.add(new Node(nx, ny, nh, time+1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        tomatoFarm = new int[N][M][H];
        tomatoQue = new LinkedList<>();
        allTime = 0;

        for(int h=0; h<H; h++) {
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    tomatoFarm[i][j][h] = Integer.parseInt(st.nextToken());
                    if(tomatoFarm[i][j][h] == 1) {
                        tomatoQue.add(new Node(i, j, h, 0));
                    }
                }
            }
        }

        managedTomatoFarm();
        checkTomatoFarm();

        System.out.println(allTime);
    }
}
