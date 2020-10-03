import java.util.*;

public class 길찾기게임 {

    class Solution {
        int nodeLen;
        int idx;
        int[][] answer;
        Node[] tree;
        
        public void makeTree(Node root, Node insertNode) {
            if(insertNode.x < root.x) {
                if(root.left == null) {
                    root.left = insertNode;
                } else {
                    makeTree(root.left, insertNode);
                }
            } else {
                if(root.right == null) {
                    root.right = insertNode;
                } else {
                    makeTree(root.right, insertNode);
                }
            }
        }
        
        public void preorder(Node root) {
            if(root != null) {
                answer[0][idx++] = root.value;
                preorder(root.left);
                preorder(root.right);
            }
        }
        
        public void postorder(Node root) {
            if(root != null) {
                postorder(root.left);
                postorder(root.right);
                answer[1][idx++] = root.value;
            }
        }
        
        public int[][] solution(int[][] nodeinfo) {
            nodeLen = nodeinfo.length;
            answer = new int[2][nodeLen];
            tree = new Node[nodeLen];
            
            for(int i=0; i<nodeLen; i++) {
                tree[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
            }
            
            Arrays.sort(tree, new Comparator<Node>() {
            @Override
                public int compare(Node node1, Node node2) {
                    return node1.y == node2.y ? node1.x - node2.x : node2.y - node1.y;
                }
            });
            
            for(int i=1; i<nodeLen; i++) {
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
            int value;
            
            Node left = null;
            Node right = null;
            
            Node(int x, int y, int value) {
                this.x = x;
                this.y = y;
                this.value = value;
            }
        }
    }
}
