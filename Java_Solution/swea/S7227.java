// 사랑의 카운슬러
import java.io.*;
import java.util.*;

public class S7227 {
  static int N, X=0, Y=0, res=999999;
  static int[][] worm, rep;
  static int[] v;

  // static void comb(int r, int cnt, int nx, int ny, int[][] total) {
  //   if(r==2) {
  //     total[cnt][0] = nx;
  //     total[cnt][1] = ny;
  //     nx = 200009;
  //     ny = 200009;
  //     r=0;
  //     cnt+=1;
  //   }
    // if(cnt==N/2) {
    //   int x=0,y=0;
    //   for(int[] i:total) {
    //     x+=i[0];
    //     y+=i[1];
    //   }
    //   res = Math.min(res, x*x+y*y);
    // }
    // for(int i=0; i<N; i++) {
    //   if(v[i]==0) {
    //     v[i] = 1;
    //     if(nx==200009 || ny==200009)
    //       comb(r+1, cnt, worm[i][0], worm[i][1], total);
    //     else
    //       comb(r+1, cnt, nx-worm[i][0], ny-worm[i][1], total);
    //     v[i] = 0;
    //     comb(r, cnt, nx, ny, total);
    //   }
    // }
  // }

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

  //   int T = in.nextInt();
  //   for(int tc=1; tc<=T; tc++) {
  //     N = in.nextInt();
  //     worm = new int[N][2];
  //     rep = new int[N][2];
  //     v = new int[N];
  //     for(int x=0; x<N; x++) {
  //       X=in.nextInt();
  //       Y=in.nextInt();
  //       worm[x][0] = X;
  //       worm[x][1] = Y;
  //     }

  //     // comb(0,0,0,0,rep);
  //     System.out.println(res);
  //   }
  //   in.close();
  }
}
