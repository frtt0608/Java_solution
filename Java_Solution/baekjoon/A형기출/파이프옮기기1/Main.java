import java.util.*;
import java.io.*;


/**
 * Main
 */
public class Main {
    static int N, map[][], passCnt;
    static int[] dx = {0,1,1}, dy = {1,1,0}; // 0: 가로, 2: 세로, 1: 대각선

    static public class Node {
        int x;
        int y;
        int dir;

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static public boolean wall(int x, int y) {
        if(x>N || x<0 || y>N || y<0 || map[x][y]==1) return true;
        return false;
    }

    // static void moveBFS() {
    //     Deque<Node> que = new LinkedList<>();
    //     que.offer(new Node(1,2,0));
    //     boolean visited[][] = new boolean[N+1][N+1];
    //     visited[1][2] = true;

    //     while(!que.isEmpty()) {
    //         Node node = que.poll();
    //         int x = node.x;
    //         int y = node.y;
    //         int dir = node.dir;

    //         if(x==N && y==N) {
    //             passCnt += 1;
    //             continue;
    //         }
  
    //         for(int i=0; i<3; i++) {
    //             int nx = x + dx[i];
    //             int ny = y + dy[i];
    //             if(wall(nx, ny) || visited[nx][ny]) continue;
    //             if(dir==2 && i==0) continue;
    //             if(dir==0 && i==2) continue;
    //             if(i==1) {
    //                 if(wall(nx-1, ny) || wall(nx, ny-1)) continue;
    //             }
    //             que.offer(new Node(nx, ny, i));
    //         }
    //     }
    // }

    static void moveDFS(int x, int y, int pre_dir) {
        if(x == N && y == N) {
            passCnt += 1;
            return;
        }

        for(int dir=0; dir<3; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(wall(nx,ny)) continue;
            if(pre_dir==0 && dir==2) continue;
            if(pre_dir==2 && dir==0) continue;
            if(dir==1) {
                if(wall(nx-1, ny) || wall(nx, ny-1)) continue;
            }
            moveDFS(nx, ny, dir);
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moveDFS(1, 2, 0);
        // moveBFS();
        System.out.println(passCnt);
    }
}
