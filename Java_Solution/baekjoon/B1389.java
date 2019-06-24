import java.io.*;
import java.util.*;

public class B1389 {
    static int[][] arr;
    static int[] chk;
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();

        // 인접행렬 생성
        arr = new int[N+1][N+1];
        for(int n=0; n<M; n++) {
            int x = in.nextInt();
            int y = in.nextInt();
            arr[x][y] = 1;
            arr[y][x] = 1;
        }

        // 인접행렬 출력
        // for(int i=0; i<N+1; i++) {
        //     for(int j=0; j<N+1; j++) {
        //         System.out.print(arr[i][j]);
        //     }
        //     System.out.println();
        // }

        for(int i=1; i<N+1; i++) {
            BFS(i);
        }
    }

    static void BFS(int num) {
        
    }
}