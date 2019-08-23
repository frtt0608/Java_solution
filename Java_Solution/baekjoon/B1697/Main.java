// 1697 숨바꼭질
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int K;
  static int time;

  static void BFS(int w, int t) {
    if(t>0 && w==N) return;
    if(w < N) return;
    if(w >= K) return;
    if(w-1 == N || w+1 == N) return;
    BFS(w-1, time+1);
    BFS(2*w, time+1);
    BFS(w+1, time+1);
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    K = in.nextInt();
    time = 0;
    BFS(N, time);
    System.out.println(time);
  }
}