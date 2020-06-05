
// 새샘이의 7-3-5 게임
import java.io.*;
import java.util.*;

public class S5948 {
  static int[] table;
  static int[] perCheck;
  static LinkedList<Integer> perArr;
  static int res;
  static LinkedList<Integer> sumArr;

  static void perm(int n, int r, LinkedList<Integer> perArr, int[] perCheck) {
    if(r == 0) {
      int sum=0;
      for(int i : perArr) {
        sum+=i;
      }
      if(!sumArr.contains(sum))
        sumArr.add(sum);
      return;
    }

    for(int i=0; i<7; i++) {
      if(perCheck[i]==0) {
        perArr.add(table[i]);
        perCheck[i] = 1;
        perm(n, r-1, perArr, perCheck);
        perCheck[i] = 0;
        perArr.removeLast();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for (int tc = 1; tc <= T; tc++) {
      table = new int[7];
      perArr = new LinkedList<Integer>();
      sumArr = new LinkedList<Integer>();
      perCheck = new int[7];
      res = 0;

      for (int i = 0; i < 7; i++) {
        table[i] = in.nextInt();
      }
      perm(7, 3, perArr, perCheck);
      Collections.sort(sumArr);
      // System.out.println(sumArr);

      res = sumArr.get(sumArr.size()-5); 
      System.out.println("#" + tc + " " + res);
    }
  }
}