// 새샘이의 7-3-5 게임
import java.io.*;
import java.util.*;

public class S5948 {
  static int[] table;
  static int res;

  
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      table = new int[7];
      res=0;

      for(int i=0; i<7; i++) {
        table[i] = in.nextInt();
      }


    }
  }
}