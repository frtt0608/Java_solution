import java.util.*;

public class 길찾기게임 {

    static class Solution {
        static int N;

        public static int[][] solution(int[][] nodeinfo) {
            N = nodeinfo.length;
            int[][] answer = new int[2][N];
            Node[] nodes = new Node[N];
            Tree tree = new Tree();
            
            for(int i=0; i<N; i++) {
                nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
            }
            
            Arrays.sort(nodes, new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    return n1.y == n2.y ? (n1.x - n2.x) : (n2.y - n1.y);
                }
            });
            
            for(int i=0; i<N; i++) {
                tree.addNode(nodes[i]);
            }
            
            tree.preOrder(tree.root);
            tree.postOrder(tree.root);
            
            String[] preArray = tree.pre.split(" ");
            String[] postArray = tree.post.split(" ");
            for(int i=0; i<N; i++) {
                answer[0][i] = Integer.parseInt(preArray[i]);
                answer[1][i] = Integer.parseInt(postArray[i]);
            }
            
            return answer;
        }
        
        public static void main(String[] args) {
            int[][] nodeinfo = {};
            solution(nodeinfo);
        }
    }

    static class Node {
        int x, y;
        int data;
        Node left = null;
        Node right = null;
        
        Node(int x, int y, int data) {
            this.x = x;
            this.y = y;
            this.data = data;
        }
    }

    static class Tree {
        Node root;
        String pre = "";
        String post = "";
        
        public void addNode(Node node) {
            if(root == null) {
                root = node;
            } else {
                insertNode(root, node);
            }
        }
        
        public void insertNode(Node root, Node insert) {
            if(root.y > insert.y) {
                if(root.x < insert.x) {
                    if(root.right == null) root.right = insert;
                    else insertNode(root.right, insert);
                    
                } else {
                    if(root.left == null) root.left = insert;
                    else insertNode(root.left, insert);
                }
            }
        }
        
        public void preOrder(Node node) {
            if(node != null) {
                pre += node.data + " ";
                preOrder(node.left);
                preOrder(node.right);
            }
        }
        
        public void postOrder(Node node) {
            if(node != null) {
                postOrder(node.left);
                postOrder(node.right);
                post += node.data + " ";
            }
        }
    }
}
