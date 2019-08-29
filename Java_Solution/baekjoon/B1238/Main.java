// 파티
import java.io.*;
import java.util.*;

class Street {
  int end;
  int val;

  Street(int end, int val) {
    this.end = end;
    this.val = val;
  }
}

public class Main {
  static int N;
  static int M;
  static int X;
  static ArrayList<Street>[] city;

  static void BFS(int x, int sum) {
    PriorityQueue<Street> qu = new PriorityQueue<>();
    
  }
  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    M = in.nextInt();
    X = in.nextInt();
    city = new ArrayList[N+1];

    for(int i=1; i<=N; i++) {
      city[i] = new Street();
    }

    for(int i=0; i<M; i++) {
      int s = in.nextInt();
      int e = in.nextInt();
      int v = in.nextInt();

      city[s].add(new Street(e,v));
    }

    
  }
}