// N개의 세로선, M개의 가로선
// 각각의 세로선마다 가로선을 놓을 수 있는 위치 개수는 H
// 모든 세로선이 같은 위치를 갖는다.
// 가로선은 인접, 연속x
// 조작 - i번 세로선의 결과가 i번이 나와야한다.
// 이때 추가하는 가로선 개수의 최솟값은?


// ladder 배열에 1값은 해당 위치에서 오른쪽으로 사다리가 존재한다고 생각하자.
// 따라서 인접한 열에 1이 있지않도록 모든 경우의 수 체크

// 오른쪽에 1이 있으면 ->
// 왼쪽에 1이 있으면 <-


import java.util.*;
import java.io.*;


/**
 * Main
 */
public class Main {
    static int N, M, H, minRes, ladder[][];
    static boolean flag;

    static boolean down_laddder() {
        for(int startNum=1; startNum<=N; startNum++) {
            int rowNum = 1;
            int ladderNum = startNum;
            while(rowNum < H+1) {
                if(ladder[rowNum][ladderNum]==1)
                    ladderNum++;
                else if(ladder[rowNum][ladderNum-1]==1)
                    ladderNum--;
                rowNum++;
            }
            if(startNum != ladderNum) return false;
        }
        return true;
    }

    static void perm(int x, int y, int cnt) {
        if(flag) return;

        if(minRes == cnt) {
            if(down_laddder()) flag=true;
            return;
        }

        for(int i=x; i<=H; i++) {
            for(int j=1; j<N; j++) {
                if(ladder[i][j]==1 || ladder[i][j+1]==1 || ladder[i][j-1]==1) continue;
                ladder[i][j] = 1;
                perm(i, j, cnt+1);
                ladder[i][j] = 0;
            }
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        M=sc.nextInt();
        H=sc.nextInt();
        minRes = 4;
        ladder = new int[H+2][N+1];
        
        for(int i=0; i<M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            ladder[a][b] = 1;
        }

        for(int i=1; i<=H; i++) {
            for(int j=1; j<=N; j++) {
                System.out.print(ladder[i][j]);
            }
            System.out.println();
        }

        for(int i=0; i<=3; i++) {
            minRes = i;
            perm(1,1,0);
            if(flag) break;
        }

        System.out.println(flag ? minRes:-1);
    }
}