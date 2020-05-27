// 연결 요소의 개수
import java.io.*;
import java.util.*;

public class Main2 {
  static int N;
  static int M;
  static int[][] graph;
  static int[] v_node;
  static int cnt;

  static void DFS(int s) {
    v_node[s] = 1;
    for(int i=1; i<N; i++) {
      if(graph[s][i]==1 && v_node[i]==0) {
        DFS(i);
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt()+1;
    M = in.nextInt();
    graph = new int[N][N];
    v_node = new int[N];
    cnt=0;

    for(int i=0; i<M; i++) {
      int x = in.nextInt();
      int y = in.nextInt();
      graph[x][y] = 1;
      graph[y][x] = 1;
    }

    for(int i=1; i<N; i++) {
      if(v_node[i]==0) {
          DFS(i);
          cnt++;
      }
    }

    System.out.println(cnt);
  }
}