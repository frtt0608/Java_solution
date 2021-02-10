import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] map;
    static Queue<Node> que;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void managedTomatoFarm() {
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};

        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny)) continue;
                
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
        
        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        
    }
}
