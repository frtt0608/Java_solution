// 트리 순회
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class Node {
  String parent;
  Node Lc, Rc;

  Node(String parent) {
    this.parent = parent;
  }

  void toString(Node node) {
    System.out.println(node.parent);
  }
}

class Tree {
  Node node;
  
  void add(String p, String Lc, String Rc) {
    if(node==null) {
      node = new Node(p);
      if(!Lc.equals(".")) node.Lc = new Node(Lc);
      if(!Rc.equals(".")) node.Rc = new Node(Rc);
    } else search(node, p, Lc, Rc);
  }

  void search(Node node, String p, String Lc, String Rc) {
    if(node==null) return;
    else if(node.parent.equals(p)) {
      if(!Lc.equals(".")) node.Lc = new Node(Lc);
      if(!Rc.equals(".")) node.Rc = new Node(Rc);
    } else {
      search(node.Lc, p, Lc, Rc);
      search(node.Rc, p, Lc, Rc);
    }
  }

  void pre_order(Node node) {
    System.out.print(node.parent);
    if(node.Lc!=null) pre_order(node.Lc);
    if(node.Rc!=null) pre_order(node.Rc);
  }
  void in_order(Node node) {
    if(node.Lc!=null) in_order(node.Lc);
    System.out.print(node.parent);
    if(node.Rc!=null) in_order(node.Rc);
  }
  void post_order(Node node) {
    if(node.Lc!=null) post_order(node.Lc);
    if(node.Rc!=null) post_order(node.Rc);
    System.out.print(node.parent);
  }
}

public class Main {
  static int N;

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    Tree tree = new Tree();
    sc.nextLine();
    for(int i=1; i<N+1; i++) {
      String data[] = sc.nextLine().split(" ");
      tree.add(data[0], data[1], data[2]);
    }
    tree.pre_order(tree.node);
    System.out.println();
    tree.in_order(tree.node);
    System.out.println();
    tree.post_order(tree.node);
  }
}