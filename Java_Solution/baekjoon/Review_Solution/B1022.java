import java.util.*;
import java.io.*;

/**
 * Main
 */
public class B1022 {
    static int r1, r2, c1, c2;
    static int R, C;
    static int[] dx={0,-1,0,1}, dy={1,0,-1,0};
    static long num, map[][];
    
    static boolean wall(int x, int y) {
        if(x<0 || x>=R || y<0 || y>=C) return true;
        return false;
    }

    static boolean checkFullMap() {
        if(map[0][0] != 0 && map[0][C-1] != 0 && map[R-1][0] != 0 && map[R-1][C-1] != 0) return true;
        return false;
    }

    static void fillMap() {
        int x = 0;
        int y = 0;
        int dir = 0;
        int changeDir = 1;
        int turn = 0;
        num = 1;

        while(true) {
            if(!wall(x-r1, y-c1)) map[x-r1][y-c1] = num;

            num += 1;
            x += dx[dir];
            y += dy[dir];
            turn += 1;

            if(turn == changeDir) {
                if(dir==1 || dir==3) changeDir += 1;

                turn = 0;
                dir = (dir+1)%4;
            }

            if(checkFullMap()) break;
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
        R = r2-r1+1;
        C = c2-c1+1;
        map = new long[R][C];
        
        fillMap();
        int maxLen = (int)Math.log10(num);

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {

                for(int empty=0; empty < maxLen-Math.log10(map[i][j]); empty++) {
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}