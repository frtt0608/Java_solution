
// 뱀
import java.io.*;
import java.util.*;

class Node {
  int t;
  String D;

  Node(int t, String D) {
    this.t = t;
    this.D = D;
  }
}

class Snake {
  int X;
  int Y;
  int D;

  Snake(int X, int Y, int D) {
    this.X = X;
    this.Y = Y;
    this.D = D;
  }
}

public class Main {
  static int N, K, L, cnt = 0, t_len = 1;
  static int[][] board;
  static int[][] v_chk, v_dir;
  static LinkedList<Node> change;
  static int[] dr = { 0, 0, 1, -1 }, dc = { 1, -1, 0, 0 }; // dir [0:R, 1:L, 2:D, 3:U]

  static int heading(int x, String d) {
    if (d.equals("X")) return x;
    if (x == 0) {
      return d.equals("L") ? 3 : 2;
    } else if (x == 1) {
      return d.equals("L") ? 2 : 3;
    } else if (x == 2) {
      return d.equals("L") ? 0 : 1;
    } else {
      return d.equals("L") ? 1 : 0;
    }
  }

  static Boolean chkwall(int x, int y) {
    if (x >= N || x < 0 || y >= N || y < 0)
      return true;
    return false;
  }

  static void BFS(int x, int y) {
    Deque<Snake> qu = new ArrayDeque<>();
    qu.add(new Snake(x, y, 0));
    v_chk[x][y] = 1;
    while (!change.isEmpty()) {
      Node n = change.poll();
      while (cnt != n.t) {
        cnt += 1;

        Snake s = qu.peek();
        // System.out.println(s.X + ", " + s.Y + ": " + s.D);
        int nhx = s.X + dr[s.D];
        int nhy = s.Y + dc[s.D];
        if (chkwall(nhx, nhy))
          return;
        if (v_chk[nhx][nhy] == 1)
          return;
        if (board[nhx][nhy] == 9) {
          board[nhx][nhy] = 0;
          qu.addFirst(new Snake(nhx, nhy, s.D));
          v_chk[s.X][s.Y] = 1;

        } else if (board[nhx][nhy] == 0) {
          qu.addFirst(new Snake(nhx, nhy, s.D));
          Snake d = qu.removeLast();
          v_chk[d.X][d.Y] = 0;
        }
        v_chk[nhx][nhy] = 1;
      }

      int dir = heading(qu.peek().D, n.D);
      Snake s = qu.poll();
      v_dir[s.X][s.Y] = dir;
      s.D = dir;
      qu.addFirst(s);
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    K = in.nextInt();
    board = new int[N][N];
    change = new LinkedList<>();
    v_chk = new int[N][N];
    v_dir = new int[N][N];

    for (int k = 0; k < K; k++) {
      int x = in.nextInt() - 1;
      int y = in.nextInt() - 1;
      board[x][y] = 9;
    }

    L = in.nextInt();
    for (int l = 0; l < L; l++) {
      int t = in.nextInt();
      String s = in.next();
      change.add(new Node(t, s));
    }
    change.add(new Node(10000, "X"));

    BFS(0, 0);

    // for(int i=0; i<N; i++) {
    //   System.out.println(Arrays.toString(v_chk[i]));
    // }
    System.out.println(cnt);
  }
}
