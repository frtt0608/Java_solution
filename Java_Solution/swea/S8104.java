// 조 만들기
import java.io.*;
import java.util.*;

public class S8104 {
  static int[] team;
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      int N = in.nextInt();
      int K = in.nextInt();
      team = new int[K];

      for(int n=0; n<N; n++) {
        for(int k=0; k<K; k++) {
          if(n%2==0) {
            team[k] += K*n+k+1;
          } else {
            team[k] += K*(n+1)-k;
          }
        }
      }
      System.out.print("#" + tc + " ");
      for(int i:team) {
        System.out.print(i + " ");
      } System.out.println();
    }
  }
}