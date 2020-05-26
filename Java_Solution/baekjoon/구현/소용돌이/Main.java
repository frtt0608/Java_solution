import java.util.*;
import java.io.*;


/**
 * Main
 */
public class Main {
    static int r1, r2, c1, c2, size;
    static long map[][], res;
    static int[] dx={0,-1,0,1}, dy={-1,0,1,0};

    static boolean wall(int x, int y) {
        if(x >= size || x<0 || y >= size || y<0) return true;
        return false;
    }

    static void fillMap(int dir, int x, int y) {
        while(true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(wall(nx, ny) || map[nx][ny] !=0 ) {
                if(res != 1)
                    fillMap((dir+1)%4, x, y);
                return;
            }
            res -= 1;
            map[nx][ny] = res;
            x = nx;
            y = ny;
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
        size = -2*r1 +1;
        map = new long[r2-r1+1][c2-c1+1];
        r2 -= r1;
        c2 -= c1;
        res = (long)Math.pow(size, 2);
        int maxLen = (int)Math.log10(res);
        map[size-1][size-1] = res;

        fillMap(0, size-1, size-1);

        for(int i=0; i<=r2; i++) {
            for(int j=0; j<=c2; j++) {

                for(int k=0; k<maxLen-Math.log10(map[i][j]); k++) {
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}   