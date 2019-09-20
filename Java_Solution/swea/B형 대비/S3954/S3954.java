// 파이의 합
// 에라토스테네스의 체 역 이용.
// a[1] + ... + a[n] = S[n]
// S[n] - a[n-1] = S[n-1]
import java.io.*;
import java.util.*;

public class S3954 {
  static int a,b,res;

  static void PHI(int N) {
    
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      a = in.nextInt();
      b = in.nextInt();
      res=0;

      for(int i=a; i<=b; i++) {

      }

      System.out.println("#" + tc + " " + res);
    }
  }
}