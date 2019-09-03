// 2048 (easy)
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int[][] board = new int[N][N];
  static int[] dr={0,0,1,-1}, dc={1,-1,0,0};
  static Boolean[][] v = new Boolean[N][N];

  static int max(int[][] T) {
    int res = 0;
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(res < T[i][j]) {
          res = T[i][j];
        }
      }
    }
    return res;
  }

  static void DFS(int[][] V, int cnt) {
    for(int dir=0; dir<4; dir++) {
      if(dir==0) {
        while(true) {
          for(int i=0; i<N; i++) {
            for(int j=N-2; j>=0; j--) {
              if(board[i][j] == board[i][j+1]) {
                board[i][j+1] += board[i][j];
              }
              if()
            }
          }
        }
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        board[i][j] = in.nextInt();
      }
    }


  }
}