import java.util.*;

import java.io.*;

public class Solution {
    static int N, K, maxHeight, maxStreet;
    static int[] dx = {1,0,-1,0}, dy = {0,-1,0,1};
    static int[][] map;
    static LinkedList<Node> maxHeightList;

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static void makeStreet(int[][] copyMap, Node n, boolean[][] visited) {
        int x = n.x;
        int y = n.y;
        int stL = n.streetLen;
        boolean usedK = n.usedK;

        maxStreet = Math.max(maxStreet, stL);

        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(isWall(nx, ny)) continue;
            if(visited[nx][ny]) continue;

            if(copyMap[x][y] > copyMap[nx][ny]) {
                visited[nx][ny] = true;
                makeStreet(copyMap, new Node(nx, ny, stL+1, usedK), visited);
                visited[nx][ny] = false;
            } else if(usedK == false && copyMap[x][y] > copyMap[nx][ny]-K) {
                usedK = true;
                visited[nx][ny] = true;
                int temp = copyMap[nx][ny];
                copyMap[nx][ny] = copyMap[x][y] - 1;
                makeStreet(copyMap, new Node(nx, ny, stL+1, usedK), visited);
                usedK = false;
                copyMap[nx][ny] = temp;
                visited[nx][ny] = false;
            }
        }
    }


    public static int[][] copyMap() {
        int[][] copyMap = new int[N][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        return copyMap;
    }

    public static void getMaxHeightXY() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == maxHeight) {
                    maxHeightList.add(new Node(i, j, 1, false));
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
            K = Integer.parseInt(st.nextToken());
            maxHeight = 0;
            maxStreet = 1;
            map = new int[N][N];
            maxHeightList = new LinkedList<>();

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }

            getMaxHeightXY();
            
            while(maxHeightList.size() > 0) {
                Node node = maxHeightList.remove(0);
                int[][] copyMap = copyMap();
                boolean[][] visited = new boolean[N][N];
                visited[node.x][node.y] = true;
                makeStreet(copyMap, node, visited);
            }


            System.out.println("#"+tc+" "+maxStreet);
        }
    }

    public static class Node {
        int x;
        int y;
        int streetLen;
        boolean usedK;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int streetLen, boolean usedK) {
            this.x = x;
            this.y = y;
            this.streetLen = streetLen;
            this.usedK = usedK;
        }
    }
}