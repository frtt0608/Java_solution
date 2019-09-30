// 햄버거 다이어트
import java.io.*;
import java.util.*;

public class S5215 {
  static int N, L, max, score[], kal[];
  static void comb(int k, int s, int index) {
    if(k>L) return;
    if(index>=N) return;
    if(k<=L) {
      if(max < s) {
        max = s;
      }
    }
    s += score[index];
    k += kal[index];
    comb(k, s, index+1);
    s -= score[index];
    k -= kal[index];
    comb(k, s, index+1);
  }
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N = sc.nextInt();
      L = sc.nextInt();
      score = new int[N];
      kal = new int[N];
      max = 0;
      for(int i=0; i<N; i++) {
        score[i] = sc.nextInt();
        kal[i] = sc.nextInt();
      }
      comb(0,0,0);
      System.out.println("#"+tc+" "+max);
    }
  }
}