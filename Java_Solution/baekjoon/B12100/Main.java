// 2048 (easy)
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int[][] board;
  static int[] dr={0,0,1,-1}, dc={1,-1,0,0};
  static Boolean[][] v;
  static int res;

  static int max(int[][] T) {
    int result = 0;
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(result < T[i][j]) {
          result = T[i][j];
        }
      }
    }
    return result;
  }

  static void DFS(int[][] map, int cnt) {
    // System.out.println(max(map));
    if(cnt>=5) {
      if(res < max(map)) {
        // System.out.println("****변경 전****");
        // System.out.println(res);
        res = max(map);
        // System.out.println("****변경 후****");
        // System.out.println(res);
      }
      return;
    }
    if(max(map) == 2048) {
      res = 2048;
      return;
    }
    if(res > max(map)) return;

    Boolean flag = false;
    for(int dir=0; dir<4; dir++) {
      flag = false;
      int[][] table = new int[N][N];
      for(int c=0; c<N; c++) {
        System.arraycopy(map[c], 0, table[c], 0, N);
        // System.out.println(Arrays.toString(table[c]));
      }
      // System.out.println(max(table));
      // System.out.println("**************************");
      if(dir==0) {
        while(true) {
          Boolean Rflag = false;
          for(int i=0; i<N; i++) {
            for(int j=N-2; j>=0; j--) {
              if(table[i][j]==0) continue;
              if(table[i][j] == table[i][j+1]) {
                Rflag = true;
                flag = true;
                table[i][j+1] += table[i][j];
                table[i][j] = 0;
              } else if(table[i][j+1] == 0) {
                Rflag = true;
                flag = true;
                table[i][j+1] = table[i][j];
                table[i][j] = 0;
              }
            }
          }
          if(flag==false) break;
          if(Rflag==false && flag==true){
            DFS(table, cnt+1);
            break;
          }
        }
      }
      else if(dir==1) {
        while(true) {
          Boolean Lflag = false;
          for(int i=0; i<N; i++) {
            for(int j=1; j<N; j++) {
              if(table[i][j]==0) continue;
              if(table[i][j] == table[i][j-1]) {
                Lflag = true;
                flag = true;
                table[i][j-1] += table[i][j];
                table[i][j] = 0;
              }
              else if(table[i][j-1] == 0) {
                Lflag = true;
                flag = true;
                table[i][j-1] = table[i][j];
                table[i][j] = 0;
              }
            }
          }
          if(flag==false) break;
          if(Lflag==false && flag==true){
            DFS(table, cnt+1);
            break;
          }
        }
      }
      else if(dir==2) {
        while(true) {
          Boolean Bflag = false;
          for(int i=0; i<N; i++) {
            for(int j=N-2; j>=0; j--) {
              if(table[j][i]==0) continue;
              if(table[j][i] == table[j+1][i]) {
                Bflag = true;
                flag = true;
                table[j+1][i] += table[j][i];
                table[j][i] = 0;
              }
              else if(table[j+1][i] == 0) {
                Bflag = true;
                flag = true;
                table[j+1][i] = table[j][i];
                table[j][i] = 0;
              }
            }
          }
          if(flag==false) break;
          if(Bflag==false && flag==true) {
            DFS(table, cnt+1);
            break;
          }
        }
      }
      else if(dir==3) {
        while(true) {
          Boolean Tflag = false;
          for(int i=0; i<N; i++) {
            for(int j=1; j<N; j++) {
              if(table[j][i]==0) continue;
              if(table[j][i] == table[j-1][i]) {
                Tflag = true;
                flag = true;
                table[j-1][i] += table[j][i];
                table[j][i] = 0;
              }
              else if(table[j-1][i] == 0) {
                Tflag = true;
                flag = true;
                table[j-1][i] = table[j][i];
                table[j][i] = 0;
              }
            }
          }
          if(flag==false) break;
          if(Tflag==false && flag==true) {
            DFS(table,cnt+1);
            break;
          }
        }
      }
    }
    if(flag==false) {
      if(res < max(map))
        res = max(map);
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    board = new int[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        board[i][j] = in.nextInt();
      }
    }
    
    res = 0;
    DFS(board, 0);
    System.out.println(res);
  }
}