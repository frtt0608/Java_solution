// 부분 수열의 합
import java.io.*;
import java.util.*;

public class S2817 {
  static int N, K, cnt;
  static int[] A;

  static void subset() {
    for(int i=1; i<(1<<N); i++) {
      int sum = 0;
      for(int j=0; j<N; j++) {
        if((i & (1 << j))!=0) {
          sum += A[j];
        }
      }
      if(sum==K) {
        cnt += 1;
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N=in.nextInt();
      K=in.nextInt();
      A = new int[N];
      cnt=0;

      for(int a=0; a<N; a++) {
        A[a] = in.nextInt();  
      }

      subset();
      System.out.println("#" + tc + " " + cnt);
    } 
  }
}