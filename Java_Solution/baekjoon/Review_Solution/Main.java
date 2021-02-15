import java.util.*;
import java.io.*;

public class Main {
    static int N;

    static class Node {
        String name;
        Node Left, Right;

        Node(String name) {
            this.name = name;
        }
    }

    static class Tree {
        Node root;

        public void add(String parent, String left, String right) {
            if(root == null) {
                root = new Node(parent);
                if(!left.equals(".")) root.Left = new Node(left);
                if(!right.equals(".")) root.Right = new Node(right);
            } else {
                searchNode(root, parent, left, right);
            }
        }

        public void searchNode(Node node, String parent, String left, String right) {
            if(node == null) 
                return;

            if(node.name.equals(parent)) {
                if(!left.equals(".")) node.Left = new Node(left);
                if(!right.equals(".")) node.Right = new Node(right);
            } else {
                searchNode(node.Left, parent, left, right);
                searchNode(node.Right, parent, left, right);
            }
        }

        public void preOrder(Node node) {
            if(node == null) return;

            System.out.print(node.name);
            preOrder(node.Left);
            preOrder(node.Right);
        }

        public void inOrder(Node node) {
            if(node == null) return;

            inOrder(node.Left);
            System.out.print(node.name);
            inOrder(node.Right);
        }

        public void postOrder(Node node) {
            if(node == null) return;

            postOrder(node.Left);
            postOrder(node.Right);
            System.out.print(node.name);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Tree tree = new Tree();
        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            tree.add(input[0], input[1], input[2]);
        }

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }
}
