// 동철이의 프로그래밍 대회
import java.io.*;
import java.util.*;

public class S6958 {
  static int[][] table;
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      int N = in.nextInt();
      int M = in.nextInt();
      int max_res = 0;
      int person = 0;

      table = new int[N][M];
      for(int i=0; i<N; i++) {
        int cnt = 0;
        for(int j=0; j<M; j++) {
          table[i][j] = in.nextInt();
          cnt += table[i][j];
        }
        if(max_res < cnt) {
          max_res = cnt;
          person = 1;
        } else if(max_res == cnt) {
          person += 1;
        }
      }
      System.out.println("#" + tc + " " + person + " " + max_res);
    }

  }
}