// Pole
// #1 2
// #2 0
// #3 4
// #4 6402373705728000

// 완전탐색 시 O(N! * N)
// 동적계획법 적용 Dy[N][R]라고 정의
// N개의 막대 중, 길이가 1인 막대의 위치에 대해 생각해보자.



import java.io.*;
import java.util.*;

public class Solution {
  static int N,L,R,min;
  static int[] table, new_t, v;
  static String RL="";

  static void perm() {

  }

  static void take(int n) {
    new_t[n] = N;
    if(n!=1) {
      
    }
    
  }
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N=in.nextInt();
      L=in.nextInt();
      R=in.nextInt();
      table = new int[N+1];
      new_t = new int[N+1];
      v = new int[N+1];
      for(int i=0; i<=N; i++) {
        table[i] = i;
      }
      System.out.println(Arrays.toString(table));
      min = R<L ? R:L;
      RL = min==R ? "R":"L";
      

    }
  }
}