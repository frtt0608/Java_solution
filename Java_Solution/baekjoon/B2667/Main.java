// 2667. 단지번호 붙이기
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int[][] map;
  static int[][] v;
  static int[] dr;
  static int[] dc;
  static ArrayList res;

  static Boolean checkWall(int x, int y) {
    if(x>=N || x<0 || y>=N || y<0 || v[x][y]!=0) return false;
    return true;
  }

  static int BFS(int x, int y, int cnt) {
    int Acnt=1;
    LinkedList<Integer[]> qu = new LinkedList();
    Integer[] temp = new Integer[] {x, y};
    
    v[x][y] = cnt;
    qu.add(temp);
    while(!qu.isEmpty()) {
      int r = qu.peek()[0];
      int c = qu.peek()[1];
      qu.poll();
      for(int i=0; i<4; i++) {
        int nr = r+dr[i];
        int nc = c+dc[i];
        if(checkWall(nr, nc)) {
          if(map[nr][nc]==1) {
            v[nr][nc] = cnt;
            temp = new Integer[] {nr, nc};
            qu.add(temp);
            Acnt+=1;
          }
        }
      }
    }
    return Acnt;
  }
  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    dr = new int[] {0,0,1,-1};
    dc = new int[] {1,-1,0,0};

    N = in.nextInt();
    map = new int[N][N];
    v = new int[N][N];
    res = new ArrayList();

    for(int i=0; i<N; i++) {
      String[] temp = in.next().split("");
      for(int j=0; j<N; j++) {
        map[i][j] = Integer.parseInt(temp[j]);
      }
    }

    int cnt = 0;
    for(int x=0; x<N; x++) {
      for(int y=0; y<N; y++) {
        if(map[x][y]==1 && v[x][y]==0) {
          cnt += 1;
          res.add(BFS(x,y,cnt));
        }
      }
    }
    System.out.println(cnt);
    res.sort(null);
    for(int r=0; r<res.size(); r++)
      System.out.println(res.get(r));
  }
}