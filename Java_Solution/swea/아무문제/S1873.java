// 상호의 배틀필드
// .	평지(전차가 들어갈 수 있다.)
// *	벽돌로 만들어진 벽
// #	강철로 만들어진 벽
// -	물(전차는 들어갈 수 없다.)
// ^	위쪽을 바라보는 전차(아래는 평지이다.)
// v	아래쪽을 바라보는 전차(아래는 평지이다.)
// <	왼쪽을 바라보는 전차(아래는 평지이다.)
// >	오른쪽을 바라보는 전차(아래는 평지이다.)
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class S1873 {
  static int H,W,N, r,c, dir;
  static String map[][], command[], RLDU[]={">", "<", "v", "^"};
  static int dr[]={0,0,1,-1}, dc[]={1,-1,0,0}; // 0:R, 1:L, 2:D, 3:U

  static Boolean check(int x, int y) {
    if(x>=H || x<0 || y>=W || y<0) return true;
    return false;
  }
  static void Go(int dir) {
    int nr = r+dr[dir];
    int nc = c+dc[dir];
    if(check(nr,nc)||map[nr][nc].equals("#")) {
      map[r][c] = RLDU[dir];
    }
    else if(map[nr][nc].equals(".")) {
      map[r][c]=".";
      r=nr; c=nc;
    }
    map[r][c] = RLDU[dir];
    return;
  }
  static void shoot(int dir, int sr, int sc) {
    int nr = sr+dr[dir];
    int nc = sc+dc[dir];
    if(check(nr,nc) || map[nr][nc].equals("#")) return;
    else if(map[nr][nc].equals("*")) { 
      map[nr][nc] = ".";
    } else {
      shoot(dir, nr, nc);
    }
    return; 
  }
  static void GAME(String str) {
    if(str.equals("U")) {
      dir = 3; Go(dir);
      
    } else if(str.equals("D")) {
      dir = 2; Go(dir);

    } else if(str.equals("L")) {
      dir = 1; Go(dir);

    } else if(str.equals("R")) {
      dir = 0; Go(dir);

    } else {
      shoot(dir, r, c);
    }
  }
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for(int tc=1; tc<=T; tc++) {
      H=sc.nextInt();
      W=sc.nextInt();
      map=new String[H][W];
      for(int i=0; i<H; i++) {
        String data[] = sc.next().split("");
        for(int j=0; j<W; j++) {
          map[i][j] = data[j];
          if(data[j].equals("<")) {
            dir=1;
            r = i; c = j;
          } else if(data[j].equals(">")) {
            dir=0;
            r = i; c = j;
          } else if(data[j].equals("^")) {
            dir=3;
            r = i; c = j;
          } else if(data[j].equals("v")) {
            dir=2;
            r = i; c = j;
          }
        }
      }
      N=sc.nextInt();
      command = sc.next().split("");
      for(String str:command) {
        GAME(str);
      }
      System.out.print("#"+tc+" ");
      for(int i=0; i<H; i++) {
        for(int j=0; j<W; j++) {
          System.out.print(map[i][j]);
        }
        System.out.println();
      } 
    }
  }
}