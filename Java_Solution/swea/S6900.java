// 주혁이의 복권당첨

import java.io.*;
import java.util.*;

public class S6900 {
  static String[] lotto;
  static int[] cash;
  static String[] num;
  static int res;
  static int N;
  static int M;

  static int Raffle(String table) {
    Boolean flag = false;
    String[] temp1 = new String[8];
    String[] temp2 = new String[8];
    temp2 = table.split("");

    for(int i=0; i<N; i++) {
      temp1 = lotto[i].split("");

      for(int j=0; j<8; j++) {
        if(temp1[j].equals(temp2[j]) || temp1[j].equals("*")) {
          flag = true;
        } else {
          flag = false;
          break;
        }
      }
<<<<<<< HEAD
      if(flag == true) {
        lotto[i] = "xxxxxxxx";
=======
      if(flag==true) {
>>>>>>> f537d4c1728d1a6ac87070de66405b014f6bda7f
        return cash[i];
      }
    }
    return 0;
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N = in.nextInt();
      M = in.nextInt();

      lotto = new String[N];
      cash = new int[N];
      num = new String[M];
      res = 0;

      for(int i=0; i<N; i++) {
        lotto[i] = in.next();
        cash[i] = in.nextInt();
      }
      
      for(int j=0; j<M; j++) {
        num[j] = in.next();
      }

      for(int go=0; go<M; go++) {
        res += Raffle(num[go]);
        // System.out.println(Raffle(num[go]));
        // res += a;
      }

      System.out.println("#" + tc + " " + res);
    }
  }
}