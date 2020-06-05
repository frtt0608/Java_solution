// Magnetic
import java.io.*;
import java.util.*;

public class S1220 {
  static int[][] table;
  static int cnt;
  static int temp;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);
    long start = System.currentTimeMillis();

    for (int tc = 1; tc < 11; tc++) {
      int N = in.nextInt();
      cnt = 0;
      table = new int[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          table[i][j] = in.nextInt();
        }
      }

      for (int x = 0; x < N; x++) {
        temp = table[0][x];

        for (int y = 1; y < N; y++) {
          if (table[y][x] != 0) {
            if (temp == 1 && table[y][x] == 2) {
              cnt++;
            }
            temp = table[y][x];
          }
        }
      }
      System.out.println("#" + tc + " " + cnt);
    }
    long end = System.currentTimeMillis();
    System.out.println((end - start) / 1000.0);
  }
}