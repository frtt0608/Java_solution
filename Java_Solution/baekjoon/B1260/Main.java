// DFS와 BFS
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int M;
  static int V;
  static int[][] table;
  static int[] visit;
  static ArrayList<Integer> res;

  static void DFS(int node) {
    if(visit[node]==1) return;

    Stack<Integer> st = new Stack();
    visit[node] = 1;
    st.push(node);
    while(!st.empty()) {
      int r = st.pop();
      res.add(r);
      for(int c=1; c<N; c++) {
        if(visit[c]==0 && table[r][c]==1)
          DFS(c);
      }
    }
  }

  static void BFS(int node) {
    LinkedList<Integer> qu = new LinkedList<>();
    visit[node] = 1;
    qu.add(node);
    while(!qu.isEmpty()) {
      int r = qu.poll();
      res.add(r);
      for(int c=1; c<N; c++) {
        if(table[r][c]==1 && visit[c]==0) {
          visit[c] = 1;
          qu.add(c);
        }
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt()+1;    
    M = in.nextInt();    
    V = in.nextInt();

    
    table = new int[N][N];

    for(int m=0; m<M; m++) {
      int x = in.nextInt();
      int y = in.nextInt();
      table[x][y] = 1;
      table[y][x] = 1;
    }

    visit = new int[N];
    res = new ArrayList();
    DFS(V);
    for(int r:res)
      System.out.print(r + " ");
    System.out.println();

    visit = new int[N];
    res = new ArrayList();
    BFS(V);
    for(int r:res)
      System.out.print(r + " ");
  }
}