// 유기농 배추
import java.io.*;
import java.util.*;

public class Solution {
  static int[][] farm;
  static int[][] checkFarm;
  static int[] dx;
  static int[] dy;

  static Boolean checkWall(int x, int y) {
    if(x>=M || x<0 || y>=N || y<0 || checkFarm[x][y] == 0) { return false; }
    return true;
  }

  static void dfs(Integer[] num) {
    Stack<Integer[]> st = new Stack<integer[]>();
    Integer[] temp = num;
    st.push(temp);
    while(st) {
      int x, y = st.pop();
      for(int i=0; i<4; i++) {
        nx = x + dx[i];
        ny = y + dy[i];
        if(checkWall(nx, ny)) {
          
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
      int M = in.nextInt();
      int N = in.nextInt();
      farm = new int[M][N];
      checkFarm = new int[M][N];
      int K = in.nextInt();

      for(int k=0; k<K; k++) {
        farm[in.nextInt()][in.nextInt()] = 1;
      }


    }
  }
}