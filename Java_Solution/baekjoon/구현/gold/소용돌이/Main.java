import java.util.*;
import java.io.*;


/**
 * Main
 */
public class Main {
    static int r1, r2, c1, c2;
    static long map[][], res;
    static int[] dx={0,-1,0,1}, dy={1,0,-1,0};

    static boolean wall(int x, int y) {
        if(x<0 || x>=r2-r1+1 || y<0 || y>=c2-c1+1) return true;
        return false;
    }

    static void fillMap() {
        int x = 0;
        int y = 0;
        int dir = 0;
        int changeDir = 1;
        int cnt = 0;

        while(true) {
            if(!wall(x-r1,y-c1)) {
                map[x-r1][y-c1] = res;
            }
            res += 1;
            x += dx[dir];
            y += dy[dir];
            cnt += 1;

            if(cnt == changeDir) {
                if(dir==1 || dir==3) changeDir += 1;
                cnt = 0;
                dir = (dir+1)%4;
            }

            if(map[0][0] != 0 && map[0][c2-c1] != 0 && map[r2-r1][0] != 0 && map[r2-r1][c2-c1] != 0) break;
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        r1 = Integer.parseInt(input[0]);
        c1 = Integer.parseInt(input[1]);
        r2 = Integer.parseInt(input[2]);
        c2 = Integer.parseInt(input[3]);

        map = new long[r2-r1+1][c2-c1+1];
        res = 1;

        fillMap();

        int maxLen = (int)Math.log10(res);

        // for(int i=0; i<r2-r1+1; i++) {
        //     System.out.println(Arrays.toString(map[i]));
        // }
        
        for(int i=0; i<r2-r1+1; i++) {
            for(int j=0; j<c2-c1+1; j++) {

                for(int k=0; k<maxLen-Math.log10(map[i][j]); k++) {
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}   