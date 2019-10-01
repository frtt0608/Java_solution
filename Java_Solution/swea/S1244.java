// 최대상금
import java.io.*;
import java.util.*;

public class S1244 {
  static int t, res;
  static String data[];
  
  static void comb(int index, LinkedList<Integer> combArray) {
    if(index>=t) return;
    
  }

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for(int tc=1; tc<=T; tc++) {
      data = sc.next().split("");
      t = sc.nextInt();
      res=0;
      System.out.println("#" + tc + " " + res);
    }
  }
}