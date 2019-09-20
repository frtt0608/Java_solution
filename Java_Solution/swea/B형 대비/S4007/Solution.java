// 간담회 참석
// MAX heap, 완전 이진트리
// push:추가, pop:삭제
// 부모가 항상 자식보다 크다.
// PriorityQueue 우선순위 Queue

// 다익스트라! 최단거리구하는 알고리즘 중에 대표적.
// 만약 음수가 있으면 다익스트라는 No!
// 최소값 찾는 건 heap을 이용
// i -> G | G -> i
// i => G : 간선의 방향을 바꿔서 생각해보자.
// 그러면 기존 i->G 다익스트라 한번, 간선방향을 바꾼 후 G->i 다익스트라 한번
// 둘을 더하면 결과 도출.


import java.io.*;
import java.util.*;

public class Solution {
  static int N,M,X, res=0;
  static int[][] room;
  static int[] v;
  static int[] wv;

  static int BFS(int E) {
    PriorityQueue<Integer[]> qu = new PriorityQueue<>();
    qu.add(new Integer[] {X,0});
    v[X] = 1;
    while(qu.isEmpty()) {
      Integer[] node = qu.poll();
      int start = node[0];
      int weight = node[1];
      v[start] = 1;
      if(wv[E] < weight) continue;
      if(start==E) {
        if(wv[E] > weight)
          wv[E] = weight;
      }
      for(int end=1; end<N+1; end++) {
        if(v[end]==1) continue;
        if(wv[end] > room[start][end] + weight) {
          wv[end] = room[start][end] + weight;
          qu.add(new Integer[] {end,wv[end]});
        }
      }
    }
    return wv[E];
  }

  static void reset() {
    for(int i=1; i<N+1; i++)
      wv[i] = 99999;
    wv[X] = 0;
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N=in.nextInt();
      M=in.nextInt();
      X=in.nextInt();
      room = new int[N+1][N+1];
      v = new int[N+1];
      wv = new int[N+1];
      

      for(int m=0; m<M; m++) {
        int x = in.nextInt();
        int y = in.nextInt();
        int w = in.nextInt();
        
        room[x][y] = w;
      }

      for(int i=1; i<=N; i++) {
        if(i==X) continue;
        int ans = BFS(i);
        if(res < ans)
          res = ans;
        reset();
      }
      
      System.out.println("#" + tc + " " + res);
    }
  }
}