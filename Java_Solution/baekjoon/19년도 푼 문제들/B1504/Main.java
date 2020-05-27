// 특정한 최단 경로
import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
  int start;
  int val;

  Node(int start, int val) {
      this.start = start;
      this.val = val;
  }

  @Override
  public int compareTo(Node node) {
      return (this.val > node.val) ? 1 : -1;
  }
}

public class Main {
  static int N, E, n1, n2;
  static int[][] map;
  static int[][] visit;
  static final int INF = 10000000;

  static void Dij(int x, int id) {
    PriorityQueue<Node> qu = new PriorityQueue<>();
    qu.add(new Node(x, 0));
    visit[id][x] = 0;

    while(!qu.isEmpty()) {
      Node n = qu.poll();
      int now = n.start;
      int val = n.val;
      if(visit[id][now] < val) continue;
      for(int i=1; i<=N; i++) {
        if(map[now][i]!=0) { 
          if(visit[id][i] > visit[id][now] + map[now][i]) {
            visit[id][i] = visit[id][now] + map[now][i];
            qu.add(new Node(i, visit[id][i]));
          }
        }
      }
    }
  }
  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    E = in.nextInt();
    visit = new int[3][N+1];
    map = new int[N+1][N+1];

    for(int i=0; i<3; i++) {
      Arrays.fill(visit[i], INF);
    }

    for(int i=0; i<E; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int c = in.nextInt();

      map[a][b] = c;
      map[b][a] = c;
    }

    n1 = in.nextInt();
    n2 = in.nextInt();
    
    Dij(1,0);
    Dij(n1,1);
    Dij(n2,2);

    int res = Math.min(visit[0][n1] + visit[1][n2] + visit[2][N],
                      visit[0][n2] + visit[2][n1] + visit[1][N]);

    System.out.println(res >= INF ? -1:res);
  }
}
