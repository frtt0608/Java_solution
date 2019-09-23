// 선 맞춤
// #1 2

// D[i] = i번째 점을 포함하여 문제의 조건을 만족하는 꺾은 선 중 가장 적은 선분의 개수
// 현재 점 i에서 뒤를 보며 해당 점(j)이 가능한 기울기의 범위 내에 있는 경우? D[j] <= D[i]+1
// 기울기는 double이나 CCW로 

import java.io.*;
import java.util.*;

public class Solution {
  static int N,E;
  static int[] table;
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      N=in.nextInt();
      E=in.nextInt();
    
      
    }
  }
}