// 오셀로 게임
// 1 흑돌, 2 백돌
// 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    static int N,M, table[][], x, y, z, whiteCnt, blackCnt;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N=sc.nextInt();
            M=sc.nextInt();
            whiteCnt=0; blackCnt=0;
            table = new int[N+1][N+1];

            for(int m=0; m<M; m++) {
                x = sc.nextInt();
                y = sc.nextInt();
                z = sc.nextInt();
                
            }
        }
    }
}