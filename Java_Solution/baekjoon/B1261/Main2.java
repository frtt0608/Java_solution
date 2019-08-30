import java.io.*;
import java.util.*;

class Wall implements Comparable<Wall>{
  int X;
  int Y;
  int cnt;

  Wall(int X, int Y, int cnt) {
      this.X = X;
      this.Y = Y;
      this.cnt = cnt;
  }

  @Override
  public int compareTo(Wall wall) {
      return (this.cnt > wall.cnt) ? 1:-1;
  }
}

public class Main2 {
  static int M;
  static int N;
  static int[][] maze;
  static int[][] v;
  static int[] dr;
  static int[] dc;

  static void BFS(int s, int e, int cnt) {
    ArrayDeque<Wall> qu = new ArrayDeque<>();
    qu.add(new Wall(s,e,cnt));
    v[s][e] = 0;
    while(!qu.isEmpty()) {
      Wall node = qu.poll();
      int r = node.X;
      int c = node.Y;
      int val = node.cnt;
      if(r==N-1 && c==M-1) {
        System.out.println(val);
        return;
      }
      // if(v[r][c] < val) continue;
      for(int i=0; i<4; i++){
        int nr = r+dr[i];
        int nc = c+dc[i];
        if(nr>=N || nr<0 || nc>=M || nc<0) continue;
        if(maze[nr][nc]==1) {
          if(v[nr][nc] > v[r][c] + 1){
            v[nr][nc] = v[r][c] + 1;
            qu.addLast(new Wall(nr,nc,v[nr][nc]));
          }
        } else {
          if(v[nr][nc] > v[r][c]) {
            v[nr][nc] = v[r][c];
            qu.addFirst(new Wall(nr,nc,v[nr][nc]));
          }
        }
      }
    }
  }
  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);
    dr = new int[] {0,0,1,-1};
    dc = new int[] {1,-1,0,0};
    M = in.nextInt();
    N = in.nextInt();
    maze = new int[N][M];
    v = new int[N][M];

    for(int i=0; i<N; i++) {
      String[] temp = in.next().split("");
      Arrays.fill(v[i], Integer.MAX_VALUE);
      for(int j=0; j<M; j++) {
        maze[i][j] = Integer.parseInt(temp[j]);
      }
    }

    BFS(0,0,0);
  }
}