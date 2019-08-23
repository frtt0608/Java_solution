// 단지번호 붙이기
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int[][] map;
  static int[][] v;
  static int[] dr;
  static int[] dc;

  static void BFS(int x, int y) {
    LinkedList<Integer[]> qu = new LinkedList();
    Integer[] temp = new Integer[] {x, y};
    qu.add(temp);
    while(!qu.isEmpty()) {
      int r = qu.peek()[0];
      int c = qu.peek()[1];
      qu.poll();
      
    }
  }
  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    dr = new int[] {0,0,1,-1};
    dc = new int[] {1,-1,0,0};

    N = in.nextInt();
    map = new int[N][N];
    v = new int[N][N];

    for(int i=0; i<7; i++) {
      String[] temp = in.next().split("");
      for(int j=0; j<7; j++) {
        map[i][j] = Integer.parseInt(temp[j]);
      }
    }

    
  }
}