// 이진 검색 트리
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class Node {
  int p;
  Node Lc, Rc;

  Node(int p) {
    this.p = p;
  }
}

class Tree {
  Node node;

  void add(int p) {
    if(node==null) {
      node = new Node(p);
    } else {
      search(node, p);
    }
  }

  void search(Node node, int p) {
    if(node==null) return;
    else if(node.p > p) {
      node.Lc = new Node(p);
    } else if(node.p < p) {
      node.Rc = new Node(p);
    }
  }

  void pre_order(Node node) {
    System.out.print(node.p);
    if(node.Lc != null) pre_order(node.Lc);
    if(node.Rc != null) pre_order(node.Rc);
  }
}


public class Main {
  static int root;

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    Tree tree = new Tree();
    while(sc.hasNextLine()) {
      tree.add(sc.nextInt());
    }
    tree.pre_order(tree.node);
  }
}