// 주사위 굴리기
import java.io.*;
import java.util.*;

public class Main {
  static int N, M, x, y, K;
  static int[][] map;
  static int[][] dice;
  static int[] commend;
  static int[] dx={0,0,0,-1,1}, dy={0,1,-1,0,0};

  static Boolean chkwall(int x, int y) {
    if(x>=N || x<0 || y>=M || y<0) return true;
    return false;
  }

  static void Dice(int D) {
    int temp = 0;
    if(D==1) {
      temp = dice[1][2];
      dice[1][2] = dice[1][1];
      dice[1][1] = dice[1][0];
      dice[1][0] = dice[3][1];
      dice[3][1] = temp;
    }
    else if(D==2) {
      temp = dice[1][0];
      dice[1][0] = dice[1][1];
      dice[1][1] = dice[1][2];
      dice[1][2] = dice[3][1];
      dice[3][1] = temp;
    }
    else if(D==3) {
      temp = dice[0][1];
      dice[0][1] = dice[1][1];
      dice[1][1] = dice[2][1];
      dice[2][1] = dice[3][1];
      dice[3][1] = temp;
    }
    else if(D==4) {
      temp = dice[3][1];
      dice[3][1] = dice[2][1];
      dice[2][1] = dice[1][1];
      dice[1][1] = dice[0][1];
      dice[0][1] = temp;
    }
  }

  static void Move(int x, int y) {
    for(int k=0; k<K; k++) {
      int dir = commend[k];
      int nx = x+dx[dir];
      int ny = y+dy[dir];
      if(chkwall(nx, ny)) continue;
      Dice(dir);
      // System.out.println(nx+ ", " +ny);
      if(map[nx][ny]!=0) {
        dice[3][1] = map[nx][ny];
        map[nx][ny] = 0;
      } else  {
        map[nx][ny] = dice[3][1];
      }
      x = nx;
      y = ny;
      // for(int i=0; i<4; i++) {
      //   System.out.println(Arrays.toString(dice[i]));
      // }
      // System.out.println("**********************");
      System.out.println(dice[1][1]);
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N=in.nextInt();
    M=in.nextInt();
    x=in.nextInt();
    y=in.nextInt();
    K=in.nextInt();

    map = new int[N][M];
    dice = new int[4][3];
    commend = new int[K];

    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        map[i][j] = in.nextInt();
      }
    }

    for(int k=0; k<K; k++) {
      commend[k] = in.nextInt();
    }

    Move(x, y);
  }
}