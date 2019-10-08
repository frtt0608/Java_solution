// 최장 증가 부분 수열
import java.util.Scanner;
import java.io.*;

public class S3307 {
  static int visit[], N, table[], res;

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for(int tc=1;tc<=T;tc++) {
      N = sc.nextInt();
      table = new int[N];
      visit = new int[N];
      res = 0;
      for(int n=0; n<N; n++) {table[n] = sc.nextInt();}
      for(int i=0; i<N; i++) {
        
      }
      
      System.out.println("#"+tc+" "+res);
    }
  }
}