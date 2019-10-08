// 최장 증가 부분 수열
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class S3307 {
  static int count[], N, table[], res;

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for(int tc=1;tc<=T;tc++) {
      N = sc.nextInt();
      table = new int[N];
      count = new int[N];
      res = 0;
      for(int n=0; n<N; n++) {table[n] = sc.nextInt();}
      for(int i=0; i<N; i++) {
        if(N-i<res) break;
        for(int j=i+1; j<N; j++) {
          if(table[i] > table[j]) continue;
          if(count[i]+1 <= count[j]) continue;
          count[j] = count[i] + 1;
          if(res<count[j]) res=count[j];
        }
      }
      res+=1;
      System.out.println("#"+tc+" "+res);
    }
    sc.close();
  }
}