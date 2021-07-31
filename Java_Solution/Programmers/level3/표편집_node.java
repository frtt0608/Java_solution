import java.util.*;

public class 표편집_node {
    
    class Solution {
        Node root, curNode, tail;
        Stack<Node> latestDeleteTable;
        
        public void downCurPos(int pos) {
            while(pos-- > 0) {
                curNode = curNode.next;
            }
        }
        
        public void upCurPos(int pos) {
            while(pos-- > 0) {
                curNode = curNode.prev;
            }
        }
        
        public void deleteCurPos() {
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev; 
            latestDeleteTable.push(curNode);
            
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
        
        public void recoverLatestNum() {
            Node latestNode = latestDeleteTable.pop();
            Node temp = latestNode.prev.next;
            latestNode.prev.next = latestNode;
            temp.prev = latestNode;
            
            if(latestNode.data < root.data) {
                root = latestNode;
            } else if(latestNode.data > tail.data) {
                tail = latestNode;
            }
        }
        
        public void operateCommand(String cmd) {
            char chr = cmd.charAt(0);
            
            if(chr == 'D') {
                downCurPos(cmd.charAt(2) - '0');
            } else if(chr == 'U') {
                upCurPos(cmd.charAt(2) - '0');
            } else if(chr == 'C') {
                deleteCurPos();
            } else {
                recoverLatestNum();
            }
        }
        
        public String solution(int n, int k, String[] cmds) {
            
            latestDeleteTable = new Stack<>();
            
            root = new Node(0);
            curNode = root;
            
            for(int num=1; num<n; num++) {
                Node node = new Node(num);
                curNode.next = node;
                node.prev = curNode;
                curNode = node;
            }
            
            tail = curNode;
            root.prev = tail;
            tail.next = root;
            curNode = root;
            
            while(k-- > 0) {
                curNode = curNode.next;
            }
            
            for(String cmd: cmds) {
                operateCommand(cmd);
            }
    
            return getAnswerString(n);
        }
        
        public String getAnswerString(int n) {
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i<n; i++) {
                if(root.data == i) {
                    sb.append("O");
                    root = root.next;
                } else {
                    sb.append("X");
                }
            }
            
            return sb.toString();
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
