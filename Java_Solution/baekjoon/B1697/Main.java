// 1697 숨바꼭질
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int K;
  static int t;
  static int[] v;

  static void BFS_odd(int w) {
    LinkedList<Integer> qu = new LinkedList();
    int time = 0;
    qu.add(w);
    while(!qu.isEmpty()) {

    }
  }

  static void BFS_even(int w) {
    LinkedList<Integer> qu = new LinkedList();
    int time = 0;
    qu.add(w);
    while(!qu.isEmpty()) {
      int p = qu.poll();
      if(p==N) {
        System.out.println("time: " + time);
        return;
      }
      if(N <= p/2) {
        
      }
    }
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    K = in.nextInt();
    t=0;
    if(K%2==0)
      BFS_even(K);
    else
      BFS_odd(K);
  }
}