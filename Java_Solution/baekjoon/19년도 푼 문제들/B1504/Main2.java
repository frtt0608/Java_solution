
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

public class Main2 {
  static int N, E, n1, n2;
  static ArrayList<Node>[] map;
  static int[][] visit;
  static final int INF = 10000000;

  static void Dij(int s, int id) {
    PriorityQueue<Node> qu = new PriorityQueue<>();
    qu.add(new Node(s, 0));
    visit[id][s] = 0;

    while (!qu.isEmpty()) {
      Node n = qu.poll();
      int now = n.start;

      if (visit[id][now] < n.val) continue;
      for (int i = 0; i < map[now].size(); i++) {
        int cur = map[now].get(i).start;
        int i_val = map[now].get(i).val;
        if (visit[id][cur] > visit[id][now] + i_val) {
          visit[id][cur] = visit[id][now] + i_val;
          qu.add(new Node(cur, visit[id][cur]));
        }
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    E = in.nextInt();
    map = new ArrayList[N+1];
    visit = new int[3][N+1];

    for(int i=0; i<3; i++) {
      Arrays.fill(visit[i], INF);
    }

    for(int i=0; i<=N; i++) {
      map[i] = new ArrayList<Node>();
    }

    for(int i=0; i<E; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int c = in.nextInt();

      map[a].add(new Node(b,c));
      map[b].add(new Node(a,c));
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