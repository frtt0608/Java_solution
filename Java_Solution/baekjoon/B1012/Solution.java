// 유기농 배추
import java.io.*;
import java.util.*;

public class Solution {
  static int[][] farm;
  static int[][] checkFarm;
  static int[] dx;
  static int[] dy;
  static Boolean flag;
  static int res;
  static int M;
  static int N;
  

  static Boolean checkWall(int x, int y) {
    if(x>=M || x<0 || y>=N || y<0 || checkFarm[x][y] == 1) { return false; }
    return true;
  }

  static void bfs(int r, int c) {
    LinkedList<Integer[]> qu = new LinkedList<Integer[]>();
    Integer[] temp = {r,c};
    checkFarm[r][c] = 1;
    flag = false;
  
    qu.add(temp);
    flag = true;
    while(!qu.isEmpty()) {
      int x = qu.peek()[0];
      int y = qu.peek()[1];
      qu.poll();
      for(int i=0; i<4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if(checkWall(nx, ny)) {
          if(farm[nx][ny]==0) continue;
          temp = new Integer[] {nx , ny};
          qu.add(temp);
          checkFarm[nx][ny] = 1;
          
        }
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);
    dx = new int[] {1, -1, 0, 0};
    dy = new int[] {0, 0, 1, -1};

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      M = in.nextInt();
      N = in.nextInt();
      farm = new int[M][N];
      checkFarm = new int[M][N];
      int K = in.nextInt();
      int res=0;

      for(int k=0; k<K; k++) {
        farm[in.nextInt()][in.nextInt()] = 1;
      }

      for(int a=0; a<M; a++) {
        for(int b=0; b<N; b++) {
          if(checkFarm[a][b]==1 || farm[a][b]==0) continue;
          bfs(a,b);
          if(flag==true) {
            res += 1;
          }
        }
      }
      System.out.println(res);
    }
  }
}