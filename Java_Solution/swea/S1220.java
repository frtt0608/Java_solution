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
      StringTokenizer st = null;
      st = in.next();
      System.out.println(st);
      // st.nextToken();
    }
  }
}