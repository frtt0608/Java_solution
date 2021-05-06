import java.util.*;

public class 길찾기게임 {

    class Solution {
        int[][] answer;
        
        class Node {
            int num;
            int x, y;
            Node left = null;
            Node right = null;
            
            Node(int num, int x, int y) {
                this.num = num;
                this.x = x;
                this.y = y;
            }
        }
        
        class Tree {
            Node root;
            int idx = 0;
            int[][] answer;
            
            public void addNode(Node param) {
                if(this.root == null) {
                    this.root = param;
                } else {
                    searchLeftOrRight(this.root, param);
                }
            }
            
            public void searchLeftOrRight(Node parent, Node param) {
                
                if(parent.y > param.y) {
                    
                    if(parent.x < param.x) {
                        if(parent.right == null) {
                            parent.right = param;
                        } else {
                            searchLeftOrRight(parent.right, param);
                        }
                    } else {
                        if(parent.left == null) {
                            parent.left = param;
                        } else {
                            searchLeftOrRight(parent.left, param);
                        }
                    }
                }
            }
            
            public void preOrder(Node parent) {
                if(parent == null) return;
                
                answer[0][idx] = parent.num;
                idx += 1;
                preOrder(parent.left);
                preOrder(parent.right);
            }
            
            public void postOrder(Node parent) {
                if(parent == null) return;
                
                postOrder(parent.left);
                postOrder(parent.right);
                answer[1][idx] = parent.num;
                idx += 1;
            }
        }

        public void getOrderResult(Tree tripRoute, int N) {
            tripRoute.answer = new int[2][N];
            
            tripRoute.preOrder(tripRoute.root);
            tripRoute.idx = 0;
            tripRoute.postOrder(tripRoute.root);
        }
        
        public Node[] settingNodeinfo(int[][] nodeinfo) {
            Node[] nodes = new Node[nodeinfo.length];
            for(int i=0; i<nodeinfo.length; i++) {
                nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
            }
            Arrays.sort(nodes, (Node node1, Node node2) -> (node2.y - node1.y));
            
            return nodes;
        }
        
        public int[][] solution(int[][] nodeinfo) {
            Node[] nodes = settingNodeinfo(nodeinfo);
            Tree tripRoute = new Tree();
            
            for(Node node: nodes) {
                tripRoute.addNode(node);
            }
            
            getOrderResult(tripRoute, nodeinfo.length);
            
            return tripRoute.answer;
        }
    }
}
