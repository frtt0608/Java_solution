// 영준이의 카드 카운팅

import java.io.*;
import java.util.*;

public class S4047 {
  static String data;
  static String[] cards;
  static int[][] SDHC;

  static int type(String s) {
    if(s.equals("S"))
      return 0;
    else if(s.equals("D"))
      return 1;
    else if(s.equals("H"))
      return 2;
    else
      return 3;
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      data = in.next();
      int L = data.length();
      cards = new String[L];
      cards = data.split("");
      SDHC = new int[4][14];
      
      int[] res = {13, 13, 13, 13};

      String style = "";
      int num = 0;
      int key = 0;
      Boolean flag = true;

      for(int i=0; i<L; i=i+3) {
        style = cards[i];
        num = Integer.parseInt(cards[i+1] + cards[i+2]);
        key = type(style);
        if(SDHC[key][num] == 1) {
          flag = false;
          break;
        }
        SDHC[key][num] = 1;
        res[key] -= 1; 

      }
      if(flag == true) {
        System.out.print("#" + tc + " ");
        for(int i:res) {
          System.out.print(i + " ");
        }
      } else {
        System.out.print("#" + tc + " " + "ERROR");  
      }
      System.out.println();
    }
  }
}