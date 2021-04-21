import java.util.*;
import java.io.*;

public class B15684 {
    static int N, M, H, minLadderCount;
    static int[][] map;
    static boolean isRight;

    public static boolean downToLadder() {

        for(int i=1; i<N; i++) {
            int num = i;
            int j = 1;

            while(j < H+1) {
                if(map[j][num] == 1) {
                    num += 1;
                } else if(map[j][num-1] == 1) {
                    num -= 1;
                }

                j += 1;
            }
            
            if(num != i) {
                return false;
            }
        }

        return true;
    }

    public static void setLadder(int needCnt, int curCnt, int si) {
        if(isRight) return;

        if(needCnt == curCnt) {
            if(downToLadder()) {
                isRight = true;
                minLadderCount = needCnt;
            }
            return;
        }

        for(int i=si; i<H+1; i++) {
            for(int j=1; j<N; j++) {
                if(map[i][j-1] == 1 || map[i][j] == 1 || map[i][j+1] == 1)
                    continue;
                
                map[i][j] = 1;
                setLadder(needCnt, curCnt+1, i);
                map[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        minLadderCount = 4;

        map = new int[H+1][N+1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
        }

        for(int cnt=0; cnt<=3; cnt++) {
            setLadder(cnt, 0, 1);
            if(isRight)
                break;
        }

        System.out.println(isRight ? minLadderCount : -1);
    }
}