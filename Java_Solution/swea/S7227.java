// 사랑의 카운슬러
import java.io.*;
import java.util.*;

public class S7227 {
  static int N, X=0, Y=0;
  static int[][] worm;

  static int vector(int x, int y) {
    return x*x + y*y;
  }

  

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N=in.nextInt();
      worm = new int[N][2];
      for(int x=0; x<N; x++) {
        X=in.nextInt();
        Y=in.nextInt();
        worm[x][0] = X;
        worm[x][1] = Y;
      }


    }
  }
}