import java.util.*;
import java.io.*;

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
/**
 * Solution
 */
public class Solution {
    static int N, M, maxHome;
    static int[][] map;
    static int[] dx={0,0,1,-1}, dy={1,-1,0,0};

    static void findHome() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                checkPrice(i, j);
            }
        }
    }

    static boolean wall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    static void checkPrice(int x, int y) {
        boolean[][] visited = new boolean[N][N];
        Queue<Node> que = new LinkedList<>();
        Queue<Node> tempQue = new LinkedList<>();
        que.add(new Node(x, y));
        visited[x][y] = true;
        int price = 0;
        int money = 0;
        int benefit = 0;

        for(int k=1; k<=N+1; k++) {
            price = k*k + (k-1)*(k-1);
            tempQue.clear();

            while(!que.isEmpty()) {
                Node n = que.poll();
                if(map[n.x][n.y]==1) money += M;
                
                for(int dir=0; dir<4; dir++) {
                    int nx = n.x + dx[dir];
                    int ny = n.y + dy[dir];
                    if(wall(nx, ny) || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    tempQue.add(new Node(nx, ny));
                }
            }

            benefit = money - price;
            if(benefit >= 0) {
                maxHome = Math.max(maxHome, money/M);
            }
            // System.out.println(money+", "+price);
            que.addAll(tempQue);
        }
    }


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxHome = 0;
            findHome();

            System.out.println("#"+tc+" "+maxHome);
        }
    }
}