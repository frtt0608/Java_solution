// 배열 값이 1이면 해당 위치에서 오른쪽으로 사다리가 이어진 것으로 생각하자!
// num과 num+1을 항상 고려하기

import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, M, H, ladder[][], minRes;
    static boolean flag;

    static void check_ladder(int x, int y, int cnt) {
        if(flag) return;

        if(cnt==minRes) {
            if(down_ladder()) flag = true;
            return;
        }

        for(int i=x; i<=H; i++) {
            for(int j=1; j<N; j++) {
                if(ladder[i][j]==1 || ladder[i][j+1]==1 || ladder[i][j-1]==1) continue;
                ladder[i][j] = 1;
                check_ladder(i,j,cnt+1);
                ladder[i][j] = 0;
            }
        }
    }

    static boolean down_ladder() {
        for(int startNum=1; startNum<N; startNum++) {
            int ladderNum = startNum;
            int rowNum = 1;
            while(rowNum<H+1) {
                if(ladder[rowNum][ladderNum]==1) {
                    ladderNum++;
                } else if(ladder[rowNum][ladderNum-1]==1) {
                    ladderNum--;
                }
                rowNum++;
            }
            if(ladderNum != startNum) return false;
        }
        return true;
    }
    static public void main(String agrs[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        M=sc.nextInt();
        H=sc.nextInt();
        ladder = new int[H+2][N+1];
        for(int i=0; i<M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            ladder[a][b]=1;
        }

        for(int cnt=0; cnt<=3; cnt++) {
            minRes = cnt;
            check_ladder(1,1,0);
            if(flag) break;
        }

        System.out.println(flag ? minRes:-1);
    }
}
