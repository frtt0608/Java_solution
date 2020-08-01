// 트리인가?

import java.util.*;
import java.io.*;

public class Main {
    static int maxNode;
    static LinkedList<Integer>[] treeLArr;
    static LinkedList<Node> treeList;
    static String[] input;

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = 1;

        while(true) {
            treeList = new LinkedList<>();
            boolean flag = true;
            maxNode = 0;

            loop:
            while(true) {
                input = br.readLine().split("  ");
                for(int i=0; i<input.length; i++) {
                    String[] strArr = input[i].split(" ");
                    if(strArr[0].equals("0") && strArr[1].equals("0")) {
                        break loop;
                    }

                    int u = Integer.parseInt(strArr[0]);
                    int v = Integer.parseInt(strArr[1]);

                    maxNode = Math.max(maxNode, Math.max(u, v));
                    treeList.add(new Node(u, v));
                }
            }

            if(treeList.size() >= 1) {
                treeLArr = new LinkedList[maxNode+1];
                for(int i=0; i<maxNode+1; i++) {
                    treeLArr[i] = new LinkedList<>();
                }
    
                while(!treeList.isEmpty()) {
                    Node n = treeList.poll();
                    // System.out.println(n.u + ", " + n.v);
                    treeLArr[n.v].offer(n.u);
                }
    
                for(int i=0; i<maxNode+1; i++) {
                    if(treeLArr[i].size() >= 2) {
                        flag = false;
                        break;
                    }
                }
            }

            if(flag) {
                System.out.println("Case " + TC + " is a tree.");
            } else {
                System.out.println("Case " + TC + " is not a tree.");
            }

            input = br.readLine().split(" ");
            if(input[0].equals("-1")) {
                break;
            }

            TC += 1;
        }
    }

    static class Node {
        int u;
        int v;

        Node(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
}