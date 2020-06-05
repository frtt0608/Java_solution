// 계산기
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;

public class S8338 {
  static int N, data[], res;
  
  static void DFS(int index, int total) {
    if(index==N) {
      if(res<total) 
        res=total;
      return;
    }

    DFS(index+1, total+data[index]);
    if(data[index]!=0 && total != 0)
      DFS(index+1, total*data[index]);
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T=sc.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N = sc.nextInt();
      data = new int[N];
      res=0;
      for(int i=0; i<N; i++) {
        data[i] = sc.nextInt();
      }
      DFS(0,0);
      System.out.println("#"+tc+" "+res);
    }
  }
}