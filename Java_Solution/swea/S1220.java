// Magnetic
import java.io.*;
import java.util.*;

public class S1220 {
  static int[][] table;
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    for(int tc=1; tc<2; tc++) {
      int N = in.nextInt();
      System.out.println(N);
      table = new int[N][N];

      for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
          table[i][j] = in.nextInt();
          System.out.print(table[i][j]);
        }
        System.out.println();
      }
    }
  }
}