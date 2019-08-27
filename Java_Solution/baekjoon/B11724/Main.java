// 연결 요소의 개수
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int M;
  static int[][] graph;
  static int[][] v;
  static int[] v_node;
  static int cnt;

  static void DFS(int node) {
    for(int i=1; i<N; i++) {
      if(graph[s][i]==1) {
        if(v[s][i]==0) {
          flag = true;
          v_node[s] = 1;
          v[s][i] = 1;
          DFS(i);
        }
      }
    }
    if(flag==true) {
      cnt += 1;
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt()+1;
    M = in.nextInt();
    graph = new int[N][N];
    v = new int[N][N];
    cnt=0;

    for(int i=0; i<M; i++) {
      int x = in.nextInt();
      int y = in.nextInt();
      graph[x][y] = 1;
      graph[y][x] = 1;
    }

    Boolean flag;
    for(int i=1; i<N; i++) {
      flag = false;
      DFS(i);
    }
    System.out.println(cnt);
  }
}