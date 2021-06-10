import java.util.*;
import java.io.*;

public class Main {
    static int N, M, T;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int[][] circle;
    static boolean sameFlag;
    static boolean[][] totalVisited;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void rotateCW(int x, int k) {
        int temp;

        while(k-- > 0) {
            temp = circle[x][M-1];
            for(int i=M-1; i>0; i--) {
                circle[x][i] = circle[x][i-1];
            }

            circle[x][0] = temp;
        }
    }

    public static void rotateCCW(int x, int k) {
        int temp;

        while(k-- > 0) {
            temp = circle[x][0];
            for(int i=0; i<M-1; i++) {
                circle[x][i] = circle[x][i+1];
            }

            circle[x][M-1] = temp;
        }
    }

    public static void rotateCircle(int x, int d, int k) {
        k %= M;
        for(int i=x; i<N+1; i+=x) {
            if(d == 0) {
                rotateCW(i, k);
            } else {
                rotateCCW(i, k);
            }
        }
    }

    public static void initVisited() {
        for(int i=1; i<N+1; i++) {
            Arrays.fill(totalVisited[i], false);
        }
    }

    public static boolean isWall(int x, int y) {
        return x<1 || x>N || y<0 || y>=M;
    }

    public static void searchSameNumber(int x, int y) {
        int target = circle[x][y];
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        
        while(!que.isEmpty()) {
            Node cur = que.poll();
            x = cur.x;
            y = cur.y;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(ny == -1) ny = M-1;
                else if(ny == M) ny = 0;

                if(isWall(nx, ny) || totalVisited[nx][ny]) continue;

                if(target == circle[nx][ny]) {
                    totalVisited[nx][ny] = true;
                    circle[nx][ny] = -1;
                    sameFlag = true;

                    que.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static void updateCircleNumber(float avg) {
        for(int i=1; i<N+1; i++) {
            for(int j=0; j<M; j++) {
                if(circle[i][j] == -1 || circle[i][j] == avg) continue;

                circle[i][j] = circle[i][j] > avg ? circle[i][j]-1:circle[i][j]+1;
            }
        }
    }

    public static void getAvgNumber() {
        float totalNum = 0;
        float totalCnt = 0;

        for(int i=1; i<N+1; i++) {
            for(int j=0; j<M; j++) {
                if(circle[i][j] == -1) continue;

                totalNum += circle[i][j];
                totalCnt += 1;
            }
        }

        updateCircleNumber(totalNum/totalCnt);
    }
    

    public static void searchTotalNumber() {
        initVisited();
        sameFlag = false;

        for(int i=1; i<N+1; i++) {
            for(int j=0; j<M; j++) {
                if(circle[i][j] == -1 || totalVisited[i][j]) continue;
                searchSameNumber(i, j);
            }
        }
    }

    public static int getTotalSumNumber() {
        int totalNumber = 0;

        for(int i=1; i<N+1; i++) {
            for(int j=0; j<M; j++) {
                if(circle[i][j] == -1) continue;
                
                totalNumber += circle[i][j];
            }
        }

        return totalNumber;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circle = new int[N+1][M];
        totalVisited = new boolean[N+1][M];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            rotateCircle(x, d, k);
            searchTotalNumber();
            
            if(!sameFlag) {
                getAvgNumber();
            }
        }
        
        System.out.println(getTotalSumNumber());
    }
}