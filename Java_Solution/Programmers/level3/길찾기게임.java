import java.util.*;

public class 길찾기게임 {

    class Solution {
        int len, idx;
        Node[] tree;
        int[][] answer;
        
        public void makeTree(Node root, Node insert) {
            if(insert.x < root.x) {
                if(root.left == null) root.left = insert;
                else
                    makeTree(root.left, insert);
                
            } else {
                if(root.right == null) root.right = insert;
                else
                    makeTree(root.right, insert);
            }
        }
        
        public void preorder(Node root) {
            if(root != null) {
                answer[0][idx++] = root.root;
                preorder(root.left);
                preorder(root.right);   
            }
        }
        
        public void postorder(Node root) {
            if(root != null) {
                postorder(root.left);
                postorder(root.right);
                answer[1][idx++] = root.root;
            }
        }
        
        public int[][] solution(int[][] nodeinfo) {
            len = nodeinfo.length;
            answer = new int[2][len];
            tree = new Node[len];
            
            for(int i=0; i<len; i++) {
                int x = nodeinfo[i][0];
                int y = nodeinfo[i][1];
                tree[i] = new Node(x, y, i+1);
            }
            
            Arrays.sort(tree, new Comparator<Node>() {
            @Override
                public int compare(Node node1, Node node2) {
                    return node1.y == node2.y ? node1.x - node2.x : node2.y - node1.y;
                }
            });
            
            for(int i=1; i<len; i++) {
                makeTree(tree[0], tree[i]);
            }
            
            idx = 0;
            preorder(tree[0]);
            idx = 0;
            postorder(tree[0]);
            
            return answer;
        }
        
        public class Node {
            int x;
            int y;
            int root;
            Node left = null;
            Node right = null;
            
            Node(int x, int y, int root) {
                this.x = x;
                this.y = y;
                this.root = root;
            }
        }
    }
}
