// 트리 순회
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class Node {
  String p, lc, rc;
  Node(String p, String lc, String rc) {
    this.p = p;
    this.lc = lc;
    this.rc = rc;
  }
}

public class Main {
  static int N;
  static String map[];

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    sc.nextLine();
    for(int i=0; i<N; i++) {

    }
  }
}