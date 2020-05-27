// 파티
import java.io.*;
import java.util.*;

class Street implements Comparable<Street> {
  int end;
  int val;

  Street(int end, int val) {
    this.end = end;
    this.val = val;
  }

  public int getEnd() {
    return end;
  }

  public int getVal() {
    return val;
  }

  @Override
  public int compareTo(Street street) {
    return (this.val > street.getVal()) ? 1:-1;
  }
}

public class Main {
  static int N;
  static int M;
  static int X;
  static ArrayList<Street>[] city;
  static int[] up;
  static int[] down;
  static int[] v;

  static void BFS(int x, int sum) {
    PriorityQueue<Street> qu = new PriorityQueue<>();
    qu.add(new Street(x, sum));
    up[x] = 0;
    while(!qu.isEmpty()) {
      Street street = qu.poll();
      int now = street.getEnd();
      int val = street.getVal();
      
      for(int i=0; i<city[now].size(); i++) {
        int node = city[now].get(i).end;
        int c_val = city[now].get(i).val;
        if(up[node] > c_val + val) {
          up[node] = c_val + val;
          qu.add(new Street(node, up[node]));
        }
      }
    }
  }

  static void BFS2(int s, int x, int sum) {
    PriorityQueue<Street> qu = new PriorityQueue<>();
    qu.add(new Street(x, sum));
    down[x] = 0;
    while(!qu.isEmpty()) {
      Street street = qu.poll();
      int now = street.getEnd();
      int val = street.getVal();
      
      for(int i=0; i<city[now].size(); i++) {
        int node = city[now].get(i).end;
        int c_val = city[now].get(i).val;
        if(down[node] > c_val + val) {
          down[node] = c_val + val;
          qu.add(new Street(node, down[node]));
        }
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    M = in.nextInt();
    X = in.nextInt();
    city = new ArrayList[N+1];
    up = new int[N+1];
    down = new int[N+1];
    v = new int[N+1];
    

    for(int i=0; i<=N; i++) {
      city[i] = new ArrayList<Street>();
    }

    for(int i=0; i<M; i++) {
      int s = in.nextInt();
      int e = in.nextInt();
      int v = in.nextInt();

      city[s].add(new Street(e,v));
    }
    for(int i=1; i<=N; i++) {
      if(i==X) continue;
      Arrays.fill(up, Integer.MAX_VALUE);
      Arrays.fill(down, Integer.MAX_VALUE);
      BFS(i,0);
      BFS2(i,X,0);
      v[i] = up[X] + down[i];
    }
    int max = 0;
    // System.out.println(Arrays.toString(up));
    // System.out.println(Arrays.toString(down));
    // System.out.println(Arrays.toString(v));

    for(int i:v) {
      max = Math.max(i, max);
    }

    System.out.println(max);
  }
}