// 2048 (easy)
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int[][] board;
  static int[] dr={0,0,1,-1}, dc={1,-1,0,0};
  static int[][] v;
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
    if(cnt==5) {
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

    for(int dir=0; dir<4; dir++) {
      int[][] table = new int[N][N];
      for(int c=0; c<N; c++) {
        System.arraycopy(map[c], 0, table[c], 0, N);
        // System.out.println(Arrays.toString(table[c]));
      }
      // System.out.println(max(table));
      // System.out.println("**************************");
      if(dir==0) {
        Boolean Rflag = false;
        for(int i=0; i<N; i++) {
          v = new int[N][N];
          for(int j=N-2; j>=0; j--) {
            int temp_t = table[i][j];
            if(temp_t==0) continue;
            for(int z=j+1; z<N; z++) {
              if(table[i][z]==0) {
                Rflag = true;
                table[i][z] = temp_t;
                table[i][z-1] = 0;
              }
              else if(table[i][z]==temp_t) {
                if(v[i][z]==0) {
                  Rflag = true;
                  table[i][z] += temp_t;
                  table[i][z-1] = 0;
                  v[i][z] = 1;
                }
              }
              else break;
            }
          }
        }
        if(Rflag==false) continue;
        if(Rflag==true){
          DFS(table, cnt+1);
        }
      }
      else if(dir==1) {
        Boolean Lflag = false;
        for(int i=0; i<N; i++) {
          v = new int[N][N];
          for(int j=1; j<N; j++) {
            int temp_t = table[i][j];
            if(temp_t==0) continue;
            for(int z=j-1; z>=0; z--) {
              if(table[i][z]==0) {
                Lflag = true;
                table[i][z] = temp_t;
                table[i][z+1] = 0;
              }
              else if(table[i][z] == temp_t) {
                if(v[i][z]==0) {
                  Lflag = true;
                  table[i][z] += temp_t;
                  table[i][z+1] = 0;
                  v[i][z] = 1;
                }
              }
              else break;
            }
          }
        }
        if(Lflag==false) continue;
        if(Lflag==true){
          DFS(table, cnt+1);
        }
      }
      else if(dir==2) {
        Boolean Bflag = false;
        for(int i=0; i<N; i++) {
          v = new int[N][N];
          for(int j=N-2; j>=0; j--) {
            int temp_t = table[j][i];
            if(table[j][i]==0) continue;
            for(int z=j+1; z<N; z++) {
              if(table[z][i]==0) {
                Bflag = true;
                table[z][i] = temp_t;
                table[z-1][i] = 0;
              }
              else if(table[z][i]==temp_t) {
                if(v[z][i]==0) {
                  Bflag = true;
                  table[z][i] += temp_t;
                  table[z-1][i] = 0;
                  v[z][i] = 1;
                }
              }
              else break;
            }
          }
        }
        if(Bflag==false) continue;
        if(Bflag==true) {
          DFS(table, cnt+1);
        }
      }
      else if(dir==3) {
        Boolean Tflag = false;
        for(int i=0; i<N; i++) {
          v = new int[N][N];
          for(int j=1; j<N; j++) {
            int temp_t = table[j][i];
            if(table[j][i]==0) continue;
            for(int z=j-1; z>=0; z--) {
              if(table[z][i] == 0) {
                Tflag = true;
                table[z][i] = temp_t;
                table[z+1][i] = 0;
              }
              else if(table[z][i] == temp_t) {
                if(v[z][i]==0) {
                  Tflag = true;
                  table[z][i] += temp_t;
                  table[z+1][i] = 0;
                  v[z][i] = 1;
                }
              }
              else break;
            }
          }
        }
        if(Tflag==false) continue;
        if(Tflag==true) {
          DFS(table,cnt+1);
        }
      }
    }
    if(res < max(map))
      res = max(map);
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    board = new int[N][N];
    v = new int[N][N];

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