// Closest
// 분할정복-O(nlogn)
// 두 그룹으로 나눈다.
// DL을 왼쪽에서 x가 가장 작은것, DR은 오른쪽에서 x가 가장 작은것
// d=min(DL,DR), y축 순으로 Merge sort해서 정렬

// Line Sweeping 
// X축 정렬 한 뒤, 앞에서 부터 한개씩 보는 방법
 

import java.io.*;
import java.util.*;

public class Solution {
  static int N;
  static double res=999999;
  static int[][] node;

  static double sqrt(int x1, int x2, int y1, int y2) {
    double total = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    return Math.sqrt(total);
  }

  static void comb(int[][] comArr, int r, int target, int index) {
    if(r==2) {
      double ans = sqrt(comArr[0][0], comArr[1][0], comArr[0][1], comArr[1][1]);
      if(res > ans) {
        res = ans;
      }
    }

    
  }
  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N = in.nextInt();
      node = new int[N][2];
      for(int i=0; i<N; i++) {
        int x = in.nextInt();
        int y = in.nextInt();
        node[i][0] = x;
        node[i][1] = y;
      }


    }
  }
}