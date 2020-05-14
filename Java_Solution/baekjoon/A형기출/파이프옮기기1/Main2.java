import java.util.*;
import java.io.*;

/**
 * Main2
 */
public class Main2 {
    static int N, map[][], passCnt;
    static int dpMap[][][]; // 0: 가로, 1: 세로, 2: 대각선

    static public void moveDP() {
        dpMap[1][2][0] = 1;

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(map[i][j]==1) continue;

                // 가로!
                if(dpMap[i][j-1][0] > 0)
                    dpMap[i][j][0] += dpMap[i][j-1][0];
                
                // 세로!
                if(dpMap[i-1][j][1] > 0)
                    dpMap[i][j][1] += dpMap[i-1][j][1];
            
                // 대각선에서 가로 or 세로!
                if(dpMap[i-1][j][2] > 0)
                    dpMap[i][j][1] += dpMap[i-1][j][2];
                if(dpMap[i][j-1][2] > 0)
                    dpMap[i][j][0] += dpMap[i][j-1][2];
                
                // 대각선!
                if(map[i-1][j]==1 || map[i][j-1]==1 || map[i-1][j-1]==1) continue;
                for(int dir=0; dir<3; dir++) {
                    if(dpMap[i-1][j-1][dir] > 0) {
                        dpMap[i][j][2] += dpMap[i-1][j-1][dir];
                    }
                }
            }
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        dpMap = new int[N+1][N+1][3];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moveDP();
        for(int dir=0; dir<3; dir++) {
            passCnt += dpMap[N][N][dir];
        }

        System.out.println(passCnt);
    }
}