import java.util.*;
import java.io.*;

public class B21610 {
    static int N, M;
    static int[] dx = {0,0,-1,-1,-1,0,1,1,1}, dy = {0,-1,-1,0,1,1,1,0,-1};
    static int[][] waterMap;
    static Queue<Node> curClouds, removeNodes;
    static boolean[][] removeVisited;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<=0 || x>N || y<=0 || y>N) return true;
        return false;
    }

    public static void makeCloudsAndMinusWater() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(removeVisited[i][j]) continue;

                if(waterMap[i][j] >= 2) {
                    waterMap[i][j] -= 2;
                    curClouds.offer(new Node(i, j));
                }
            }
        }
    }

    public static void checkBasketAndPlusWater() {
        while(!removeNodes.isEmpty()) {
            Node cur = removeNodes.poll();
            int x = cur.x;
            int y = cur.y;
            int waterCnt = 0;

            for(int dir=2; dir<=8; dir+=2) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny)) continue;

                if(waterMap[nx][ny] > 0)
                    waterCnt += 1;
            }

            waterMap[x][y] += waterCnt;
        }
    }

    public static void moveClouds(int d, int s) {
        s %= N;

        while(!curClouds.isEmpty()) {
            Node curCloud = curClouds.poll();
            int x = curCloud.x;
            int y = curCloud.y;
            int moveCnt = 0;

            while(moveCnt < s) {
                moveCnt += 1;
                x += dx[d];
                y += dy[d];

                if(x == N+1) x = 1;
                else if(x == 0) x = N;

                if(y == N+1) y = 1;
                else if(y == 0) y = N;
            }

            waterMap[x][y] += 1;
            removeVisited[x][y] = true;
            removeNodes.offer(new Node(x, y));
        }
    }

    public static void initClouds() {
        curClouds = new LinkedList<>();

        curClouds.offer(new Node(N-1, 1));
        curClouds.offer(new Node(N-1, 2));
        curClouds.offer(new Node(N, 1));
        curClouds.offer(new Node(N, 2));
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        waterMap = new int[N+1][N+1];
        
        removeNodes = new LinkedList<>();

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<N+1; j++) {
                waterMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initClouds();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            removeVisited = new boolean[N+1][N+1];
        
            moveClouds(d, s);
            checkBasketAndPlusWater();
            makeCloudsAndMinusWater();
        }

        System.out.println(getTotalWater());
    }

    public static int getTotalWater() {
        int totalWater = 0;
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                totalWater += waterMap[i][j];
            }
        }

        return totalWater;
    }
}