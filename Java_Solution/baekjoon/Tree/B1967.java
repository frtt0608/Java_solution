import java.util.*;
import java.io.*;

public class B1967 {
    static int N, farNode, maxRadius;
    static boolean[] visited;
    static ArrayList<Node>[] tree;

    static class Node {
        int num, weight;

        Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    public static void findParentNodeBFS(int num) {
        if(num == 0) return;
        
        Queue<Node> que = new LinkedList<>();
        visited = new boolean[N+1];

        que.offer(new Node(num, 0));
        visited[num] = true;
        maxRadius = 0;

        while(!que.isEmpty()) {
            Node parent = que.poll();
        
            if(parent.weight > maxRadius) {
                maxRadius = parent.weight;
                farNode = parent.num;
            }

            for(Node node: tree[parent.num]) {
                if(!visited[node.num]) {
                    visited[node.num] = true;
                    que.offer(new Node(node.num, parent.weight + node.weight));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        maxRadius = 0;

        for(int i=1; i<N+1; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
        
            tree[p].add(new Node(s, weight));
            tree[s].add(new Node(p, weight));
        }

        findParentNodeBFS(1);
        findParentNodeBFS(farNode);

        System.out.println(maxRadius);
    }
}