import java.util.*;
import java.io.*;


/**
 * Solution
 */
public class Solution1 {
    static int N, M, maxHome;
    static int[][] map;
    static ArrayList<Node> homeList;

    static class Node {
        int x;
        int y;
    
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void checkPrice(int i, int j) {
        int price;
        int x;
        int y;
        int dist;
        int money = 0;

        for(int k=1; k<=N+1; k++) {
            price = k*k + (k-1)*(k-1);
            money = 0;
            
            for(int idx=0; idx<homeList.size(); idx++) {
                x = homeList.get(idx).x;
                y = homeList.get(idx).y;
                dist = Math.abs(x-i) + Math.abs(y-j);
                if(dist <= k-1) {
                    money += M;
                }
            }

            if(price <= money) {
                maxHome = Math.max(maxHome, money/M);
            }
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
            homeList = new ArrayList<>();
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]==1) {
                        homeList.add(new Node(i, j));
                    }
                }
            }

            maxHome = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    checkPrice(i, j);
                }
            }

            System.out.println("#"+tc+" "+maxHome);
        }
    }
}