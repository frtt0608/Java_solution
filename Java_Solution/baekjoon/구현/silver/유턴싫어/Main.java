import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int R, C;
    static String map[][];
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};

    static int checkMap() {
        int nx, ny=0;
        int checkCnt = 0;
        for(int x=0; x<R; x++) {
            for(int y=0; y<C; y++) {
                if(map[x][y].equals("X")) continue;
                checkCnt = 0;

                for(int dir=0; dir<4; dir++) {
                    nx = x + dx[dir];
                    ny = y + dy[dir];
                    if(nx >= R || nx < 0 || ny >= C || ny < 0 || map[nx][ny].equals("X")) {
                        checkCnt++;
                        if(checkCnt==3) return 1;
                        continue;
                    }
                }
            }
        }

        return 0;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new String[R][C];
        for(int i=0; i<R; i++) {
            input = br.readLine().split("");
            for(int j=0; j<C; j++) {
                map[i][j] = input[j];
            }
        }
        
        System.out.println(checkMap());
    }
}