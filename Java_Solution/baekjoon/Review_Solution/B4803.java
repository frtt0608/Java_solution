import java.util.*;
import java.io.*;

public class B4803 {
    static int n, m;
    static int[] tree, isCycle, nodeCount;

    public static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);

        if(node1 == node2) {
            isCycle[node1] = 0;
        } else {
            if(nodeCount[node1] > nodeCount[node2]) {
                tree[node2] = node1;
                nodeCount[node1] += nodeCount[node2];
            } else {
                tree[node1] = node2;
                nodeCount[node2] += nodeCount[node1];
            }
        }
    }
    
    public static int find(int idx) {
        if(tree[idx] == idx) return idx;
        else {
            return tree[idx] = find(tree[idx]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int testCase = 1;

        while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            
            if(n==0 & m==0) break;

            int treeCount = 0;
            tree = new int[n+1];
            isCycle = new int[n+1];
            nodeCount = new int[n+1];
            for(int i=1; i<n+1; i++) {
                tree[i] = i;
                isCycle[i] = 1;
                nodeCount[i] = 1;
            }

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                union(n1, n2);
            }

            for(int i=1; i<n+1; i++) {
                int root = find(i);
                if(isCycle[root] > 0) {
                    isCycle[root] = 0; // treeCount에 반영, 중복을 막기위해 초기화
                    treeCount += 1;
                }
            }

            sb.append("Case " + testCase + ": ");
            if(treeCount == 0) {
                sb.append("No trees.\n");
            } else if(treeCount == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of " + treeCount + " trees.\n");
            }

            testCase += 1;
        }        

        System.out.println(sb.toString());
    }
}   
