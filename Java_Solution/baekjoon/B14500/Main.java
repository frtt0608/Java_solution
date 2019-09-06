// 테트로미노
import java.io.*;
import java.util.*;

public class Main {
  static int N, M, cnt=0;
  static int[][] map;
  static int[][] v;
  static int[] dr={0,0,1,-1}, dc={1,-1,0,0};

  static Boolean chkwall(int x, int y) {
    if(x>=N || x<0 || y>=M || y<0) return true;
    return false;
  }
  static int DFS(int x, int y, int cnt) {
    if(cnt>=4) {
      return 0;
    }
    int res = 0;
    for(int i=0; i<4; i++) {
      int nr = x + dr[i];
      int nc = y + dc[i];
      if(chkwall(nr,nc)) continue;
      if(v[nr][nc]==1) continue;
      // System.out.println(nr+", " +nc + ": "+ map[nr][nc] + "," + cnt);
      v[x][y] = 1;
      res = Math.max(res, DFS(nr,nc,cnt+1) + map[x][y]);
      v[x][y] = 0;
    }
    return res;
  }

  static int O(int x, int y, int d) {
    int res = 0;
    switch(d) {
      case 1:
        res += map[x][y] + map[x][y-1] + map[x][y+1] + map[x-1][y];
        break;
      case 2:
        res += map[x][y] + map[x][y-1] + map[x][y+1] + map[x+1][y];
        break;
      case 3:
        res += map[x][y] + map[x-1][y] + map[x+1][y] + map[x][y+1];
        break;
      case 4:
        res += map[x][y] + map[x-1][y] + map[x+1][y] + map[x][y-1];
        break;
    }
    return res;
  }

  static int side(int x, int y) {
    int res = 0;
    if(x==0 && y==0) return 0;
    if(x==0 && y==M-1) return 0;
    if(x==N-1 && y==0) return 0;
    if(x==N-1 && y==M-1) return 0;

    
    // ㅗ ㅜ ㅏ ㅓ
    if((y>=1 && y<=M-2) && x==0) {
      res = Math.max(res, O(x,y,2));      
    } else if((y>=1 && y<=M-2) && x==N-1) {
      res = Math.max(res, O(x,y,1));
    } else if((x>=1 && x<=N-2) && y==0) {
      res = Math.max(res, O(x,y,3));
    } else if((x>=1 && x<=N-2) && y==M-1) {
      res = Math.max(res, O(x,y,4));
    } else {
      res = Math.max(res, O(x,y,1));
      res = Math.max(res, O(x,y,2));
      res = Math.max(res, O(x,y,3));
      res = Math.max(res, O(x,y,4));
    }
    return res;
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    M = in.nextInt();
    map = new int[N][M];
    v = new int[N][M];


    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        map[i][j] = in.nextInt();
      }
    }

    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        int temp = Math.max(DFS(i,j,0), side(i,j));
        if(cnt<temp)
          cnt = temp;
      }
    } 
    System.out.println(cnt);
  }
}