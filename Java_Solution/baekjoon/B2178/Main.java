// 미로탐색
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int M;
  static int[][] maze;
  static int[][] v;
  static int[] dr;
  static int[] dc;
  static int res;
  
  static Boolean checkWall(int x, int y) {
    if(x>=N || x<0 || y>=M || y<0 || v[x][y] != 0) return false;
    return true;
  }

  static void BFS(int x, int y) {
    LinkedList<Integer[]> qu = new LinkedList();
    Integer[] temp = new Integer[] {x,y};
    qu.add(temp);
    while(!qu.isEmpty()) {
      int r = qu.peek()[0];
      int c = qu.peek()[1];
      qu.poll();
      for(int i=0; i<4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        if(checkWall(nr,nc)) {
          if(maze[nr][nc]==0) continue;
          v[nr][nc] = v[r][c] + 1;
          if(nr==N-1 && nc==M-1) return;
          temp = new Integer[] {nr, nc};
          qu.add(temp);
          
        }
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);
    dr = new int[] {0,0,1,-1};
    dc = new int[] {1,-1,0,0};

    N = in.nextInt();
    M = in.nextInt();
    maze = new int[N][M];
    v = new int[N][M];
    
    for(int i=0; i<N; i++) {
      String[] temp;
      temp = in.next().split("");
      for(int j=0; j<M; j++) {
        maze[i][j] = Integer.parseInt(temp[j]);
      }

    }
    v[0][0] = 1;
    BFS(0,0);
    System.out.println(v[N-1][M-1]);
  }
}