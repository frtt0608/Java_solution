// 승률 비교하기
import java.io.*;
import java.util.Scanner;

public class S3975 {
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for(int tc=1;tc<=T;tc++) {
      int A = sc.nextInt();
      int B = sc.nextInt();
      int C = sc.nextInt();
      int D = sc.nextInt();
      String res="";

      A *= D;
      C *= B; 
      if(A>C) res="ALICE";
      else if(A<C) res="BOB";
      else res="DRAW";
      
      System.out.println("#"+tc+" "+res);
    }
  }
}