// 문제 제목 붙이기
import java.io.*;
import java.util.*;

public class S7087 {
  static String[] title;
  static String[] alpha;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);
    String[] table = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                      "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      int N = in.nextInt();
      String[] temp;
      int res = 0;
      alpha = new String[N];
      title = new String[N];

      for(int i=0; i<N; i++) {
        title[i] = in.next();
        temp = new String[title[i].length()];
        temp = title[i].split("");
        alpha[i] = temp[0];
      }
      while(true) {
        if(res >= table.length) 
          break;
        if(Arrays.asList(alpha).contains(table[res])) {
          res+=1;
        } else 
          break;
      }
      System.out.println("#" + tc + " " + res);
    }
  }
}