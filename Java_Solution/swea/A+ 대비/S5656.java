import java.util.*;
import java.io.*;

/**
 * S5656
 */
public class S5656 {
    static int N, W, H;
    static int minBrick;

    static class Node {
        int x;
        int y;
    
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void downTobrick(int copy_map[][]) {
        int zeroIdx = H-1;

        for(int j=0; j<W; j++) {
            zeroIdx = H-1;
            for(int i=H-1; i>=0; i--) {
                if(copy_map[i][j] > 0) {
                    int temp = copy_map[i][j];
                    copy_map[i][j] = 0;
                    copy_map[zeroIdx][j] = temp;
                    zeroIdx--;
                }
            }
        }
    }

    static void copyMap(int origin[][], int copy_map[][]) {
        for(int i=0; i<W; i++) {
            for(int j=0; j<H; j++) {
                copy_map[j][i] = origin[j][i];
            }
        }
    }

    static void setMarble(int cnt, int[][] map) {
        if(cnt==N) {
            minBrick = Math.min(minBrick, findBrick(map));
            return;
        }
        
        int copy_map[][] = new int[H][W];
        for(int i=0; i<W; i++) {
            for(int j=0; j<H; j++) {
                if(map[j][i]==0) continue;
                
                copyMap(map, copy_map);
                breakBrick(j, i, copy_map);
                downTobrick(copy_map);
                setMarble(cnt+1, copy_map);
                break;
            }
        }

        // minBrick = Math.min(minBrick, findBrick(map));
    }

    static void breakBrick(int j, int i, int copy_map[][]) {
        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, 1, -1};
        int x = j;
        int y = i;
        int num = copy_map[x][y];
        
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(x,y));

        while(!que.isEmpty()) {
            Node n = que.poll();
            num = copy_map[n.x][n.y];
            copy_map[n.x][n.y] = 0;
            
            for(int dir=0; dir<4; dir++) {
                for(int cnt=1; cnt<num; cnt++) {
                    x = n.x + dx[dir] * cnt;
                    y = n.y + dy[dir] * cnt;
                    if(wall(x,y)) break;
                    if(copy_map[x][y]==0) continue;
                    else {
                        que.add(new Node(x, y));
                    }
                }
            }
        }
    }

    static int findBrick(int[][] copy_map) {
        int cntBrick = 0;
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(copy_map[i][j]!=0) cntBrick += 1;
            }
        }

        return cntBrick;
    }

    static boolean wall(int x, int y) {
        if(x<0 || x>=H || y<0 || y>=W) return true;
        return false;
    }

    static void printMap(int[][] copy_map) {
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                System.out.print(copy_map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
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
            
            int[][] map = new int[H][W];
            minBrick = Integer.MAX_VALUE;

            for(int i=0; i<H; i++) {
                input = br.readLine().split(" ");
                for(int j=0; j<W; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                }
            }

            setMarble(0, map);
            
            if(minBrick == Integer.MAX_VALUE) minBrick = 0;
            System.out.println("#"+tc+" "+minBrick);
        }
    }
}