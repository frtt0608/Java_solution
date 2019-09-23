// 사랑의 카운슬러
import java.io.*;
import java.util.*;

public class S7227 {
  static int N, X=0, Y=0;
  static long res;
  static int[][] worm;
  static int[] v;

  static void comb(int n, int r) {
    if(r==N/2) {
      long x=0, y=0;
      for(int i=0; i<N; i++) {
        if(v[i]==1) {
          x += worm[i][0];
          y += worm[i][1];
        } else {
          x -= worm[i][0];
          y -= worm[i][1];
        }
      }
      // System.out.println(x+", "+y);
      res = Math.min(res, x*x + y*y);
      // System.out.println(res);
    }
    for(int i=n; i<N; i++) {
      if(v[i]==0) {
        v[i] = 1;
        comb(i+1,r+1);
        v[i] = 0;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N = in.nextInt();
      worm = new int[N][2];
      v = new int[N];

      for(int x=0; x<N; x++) {
        X=in.nextInt();
        Y=in.nextInt();
        worm[x][0] = X;
        worm[x][1] = Y;
      }
      res = Long.MAX_VALUE;
      comb(0,0);
      System.out.println("#" + tc + " " + res);
    }
    in.close();
  }
}
