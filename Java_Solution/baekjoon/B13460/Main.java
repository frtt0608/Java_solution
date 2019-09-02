
// 구슬 탈출2
import java.io.*;
import java.util.*;

class Node {
  int RX;
  int RY;
  int BX;
  int BY;
  int cnt;

  Node(int RX, int RY, int BX, int BY, int cnt) {
    this.RX = RX;
    this.RY = RY;
    this.BX = BX;
    this.BY = BY;
    this.cnt = cnt;
  }
}

public class Main {
  static int N;
  static int M;
  static String[][] board;
  static int[] dr = { 0, 0, 1, -1 };
  static int[] dc = { 1, -1, 0, 0 };
  static int[][][][] v;

  static void Dij(int Rx, int Ry, int Bx, int By) {
    Queue<Node> qu = new LinkedList();
    qu.add(new Node(Rx, Ry, Bx, By, 0));
    v[Rx][Ry][Bx][By] = 1;

    while (!qu.isEmpty()) {
      Node n = qu.poll();
      int oRr = n.RX;
      int oRc = n.RY;
      int oBr = n.BX; 
      int oBc = n.BY;
      int cnt = n.cnt;
      // System.out.println(oRr + ", " + oRc + "  :  " + oBr + ", " + oBc);
      // System.out.println(cnt);
      if(board[oBr][oBc].equals("O")) continue;
      if(cnt > 10) {
        System.out.println("-1");
        return;
      }

      if (board[oRr][oRc].equals("O") && !board[oBr][oBc].equals("O")) {
        System.out.println(cnt);
        return;
      }
      
      // d = 0:오른쪽, 1:왼쪽, 2:아래쪽, 3:위쪽
      for (int i = 0; i < 4; i++) {
        int Rr = oRr;
        int Rc = oRc;
        int Br = oBr;
        int Bc = oBc;

        while(true) {
          int nRr = Rr + dr[i];
          int nRc = Rc + dc[i];
          if(board[nRr][nRc].equals("#")) {
            break;
          }
          if(board[nRr][nRc].equals("O")) {
            Rr = nRr;
            Rc = nRc;
            break;
          }
          Rr = nRr;
          Rc = nRc;
        }
        while(true) {
          int nBr = Br + dr[i];
          int nBc = Bc + dc[i];
          if(board[nBr][nBc].equals("#")) {
            break;
          }
          if(board[nBr][nBc].equals("O")) {
            Br = nBr;
            Bc = nBc;
            break;
          }
          Br = nBr;
          Bc = nBc;
        }

        // 빨간공 파란공이 붙어있는 경우
        if (Rr == Br && Rc == Bc) {
          if(board[Rr][Rc].equals("O") && board[Br][Bc].equals("O")) continue;
          if(Math.abs(oRr-Rr) + Math.abs(oRc-Rc) > Math.abs(oBr-Br) + Math.abs(oBc-Bc)) {
            Rr -= dr[i];
            Rc -= dc[i];
          } else {
            Br -= dr[i];
            Bc -= dc[i];
          }
        }

        // 방문체크
        if(v[Rr][Rc][Br][Bc]==1) continue;
        v[Rr][Rc][Br][Bc] = 1;
        qu.add(new Node(Rr, Rc, Br, Bc, n.cnt+1));
        
      }
    }
    System.out.println("-1");
    return;
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    M = in.nextInt();
    board = new String[N][M];
    v = new int[N][M][N][M];
    int rr = 0, rc = 0, br = 0, bc = 0;

    for (int i = 0; i < N; i++) {
      String[] temp = in.next().split("");
      for (int j = 0; j < M; j++) {
        board[i][j] = temp[j];
        if (board[i][j].equals("R")) {
          rr = i;
          rc = j;
          board[i][j] = ".";
        } else if (board[i][j].equals("B")) {
          br = i;
          bc = j;
          board[i][j] = ".";
        }
      }
      // System.out.println(Arrays.toString(board[i]));
    }

    Dij(rr, rc, br, bc);
  }
}