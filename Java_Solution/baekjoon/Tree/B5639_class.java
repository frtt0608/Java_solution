import java.util.*;
import java.io.*;

public class B5639_class {
    static int N;
    static int[] preOrder;

    static class Node {
        int num;
        Node left, right;

        Node(int num) {
            this.num = num;
        }
    }

    static class Tree {
        Node root;

        public void add(int num) {
            if(root == null) {
                root = new Node(num);
            } else {
                searchNode(root, num);
            }
        }

        public void searchNode(Node node, int num) {
            if(node == null) return;

            if(node.num > num) {
                if(node.left == null) {
                    node.left = new Node(num);
                } else
                    searchNode(node.left, num);
            } else {
                if(node.right == null) {
                    node.right = new Node(num);
                } else
                    searchNode(node.right, num);
            }
        }

        public void postOrder(Node node) {
            if(node == null) return;

            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.num);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Tree tree = new Tree();
        while(true) {
            String input = br.readLine();
            if(input == null) break;

            int num = Integer.parseInt(input);
            tree.add(num);
        }

        tree.postOrder(tree.root);
    }
}   
