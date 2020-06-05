// N-queen
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class S2806 {
  static int N, table[][], res;

  static Boolean check(int x, int y) {
    for(int i=0; i<N; i++) {
      if(x==i) continue;
      if(table[i][y]==1) return false;
    }
    for(int i=0; i<N; i++) {
      if(y==i) continue;
      if(table[x][i]==1) return false;
    }
    int temp=1;
    while(true) {
      if(x+temp>=N || y+temp>=N) break;
      if(table[x+temp][y+temp]==1) return false;
      temp+=1;
    }
    temp=1;
    while(true) {
      if(x-temp<0 || y-temp<0) break;
      if(table[x-temp][y-temp]==1) return false;
      temp+=1;
    } 
    temp=1;
    while(true) {
      if(x+temp>=N || y-temp<0) break;
      if(table[x+temp][y-temp]==1) return false;
      temp+=1;
    } 
    temp=1;
    while(true) {
      if(x-temp<0 || y+temp>=N) break;
      if(table[x-temp][y+temp]==1) return false;
      temp+=1;
    }
    return true;
  }

  static void queen(int cnt, int row) {
    if(cnt==N) {
      res+=1;
      return;
    }
    for(int i=0; i<N; i++) {
      if(check(row, i)) {
        table[row][i]=1;
        queen(cnt+1, row+1);
        table[row][i]=0;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N=sc.nextInt();
      table = new int[N][N];
      res=0;
      queen(0, 0);
      System.out.println("#"+tc+" "+res);
    }
  }
}