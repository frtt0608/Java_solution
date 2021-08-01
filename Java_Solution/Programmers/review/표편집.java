import java.util.*;

public class 표편집 {

    class Solution {
        Node root, tail, curNode;
        Stack<Node> deletedNodes;
        
        public void makeRootNodes(int n, int k) {
            root = new Node(0);
            curNode = root;
            
            for(int i=1; i<n; i++) {
                Node node = new Node(i);
                curNode.next = node;
                node.prev = curNode;
                curNode = node;
            }
            
            tail = curNode;
            tail.next = root;
            root.prev = tail;
            curNode = root;
            
            while(k-- > 0) {
                curNode = curNode.next;
            }
        }
        
        public void downCurNode(int pos) {
            while(pos-- > 0) {
                curNode = curNode.next;
            }
        }
        
        public void upCurNode(int pos) {
            while(pos-- > 0) {
                curNode = curNode.prev;
            }
        }
        
        public void deleteNode() {
            deletedNodes.push(curNode);
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
            
            if(curNode == root) {
                root = curNode.next;
                curNode = root;
                
            } else if(curNode == tail) {
                tail = curNode.prev;
                curNode = tail;
            } else {
                curNode = curNode.next;
            }
        }
        
        public void recoverNode() {
            Node latestNode = deletedNodes.pop();
            latestNode.next = latestNode.prev.next;
            latestNode.prev.next = latestNode;
            latestNode.next.prev = latestNode;
            
            if(latestNode.data < root.data) {
                root = latestNode;
            } else if(latestNode.data > tail.data) {
                tail = latestNode;
            }
        }
        
        public void operateCommand(String cmd) {
            char chr = cmd.charAt(0);
            
            if(chr == 'U') {
                upCurNode(Integer.parseInt(cmd.substring(2)));
            } else if(chr == 'D') {
                downCurNode(Integer.parseInt(cmd.substring(2)));
            } else if(chr == 'C') {
                deleteNode();
            } else {
                recoverNode();
            }
        }
        
        public String getResultString(int n) {
            StringBuilder result = new StringBuilder();
            
            for(int i=0; i<n; i++) {
                
                if(root.data == i) {
                    result.append("O");
                    root = root.next;
                } else {
                    result.append("X");
                }
            }
            
            return result.toString();
        }
        
        public String solution(int n, int k, String[] cmds) {
            deletedNodes = new Stack<>();
            makeRootNodes(n, k);
            
            for(String cmd: cmds) {
                operateCommand(cmd);
            }
            
            return getResultString(n);
        }
        
        class Node {
            int data;
            Node prev;
            Node next;
            
            Node(int data) {
                this.data = data;
                this.prev = null;
                this.next = null;
            }
        }
    }
}
