import java.util.*;
import java.io.*;

/**
 * S5656
 */
public class S5656 {
    static int N, W, H;

    static class Node {
        int x;
        int y;
    
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] downTobrick(int map[][]) {
        int zeroIdx = H-1;
        int temp = 0;
        for(int j=0; j<W; j++) {
            zeroIdx = H-1;
            for(int i=H-1; i>=0; i--) {
                if(map[i][j] > 0) {
                    temp = map[i][j];
                    map[i][j] = 0;
                    map[zeroIdx][j] = temp;
                    zeroIdx++;
                }
            }
        }
        
        return map;
    }

    static void startBrickGame(int cnt) {
        if(cnt==N) {
            
            return;
        }

        for(int i=0; i<N; i++) {

        }
    }

    static void findBrick(int idx, int map[][]) {
        for(int i=0; i<N; i++) {
            if(map[i][idx] == 1) {
                map[i][idx] = 0;
            } else if(map[i][idx] > 1) {

            }
        }
    }

    static void breakBrick(int i, int idx, int map[][]) {
        int dx[] = {1,-1,0,0};
        int dy[] = {0,0,1,-1};
        int x = i;
        int y = idx;
        
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(x,y));
        while(!que.isEmpty()) {
            Node n = que.poll();
            
            for(int dir=0; dir<4; dir++) {
                for(int cnt=1; cnt<map[n.x][n.y]; cnt++) {
                    
                }                
            }
        }
    }

    static boolean wall(int x, int y) {
        if(x<0 || x>=H || y<0 || y>=W) return true;
        return false;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            String input[] = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            H = Integer.parseInt(input[2]);

            int map[][] = new int[H][W];
        }
    }
}