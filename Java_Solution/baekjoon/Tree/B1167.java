import java.util.*;
import java.io.*;

public class B1167 {
    static int N, farNode, maxRadius;
    static boolean[] visited;
    static ArrayList<Node>[] tree;

    static class Node {
        int num, len;

        Node(int num, int len) {
            this.num = num;
            this.len = len;
        }
    }

    public static void findParentNodeBFS(int num) {
        Queue<Node> que = new LinkedList<>();
        visited = new boolean[N+1];

        que.offer(new Node(num, 0));
        visited[num] = true;

        while(!que.isEmpty()) {
            Node parent = que.poll();
        
            if(parent.len > maxRadius) {
                maxRadius = parent.len;
                farNode = parent.num;
            }

            for(Node node: tree[parent.num]) {
                if(!visited[node.num]) {
                    visited[node.num] = true;
                    que.offer(new Node(node.num, parent.len + node.len));
                }
            }
        }
    }

    public static void findParentNodeDFS(int num, int len) {
        visited[num] = true;

        if(maxRadius < len) {
            maxRadius = len;
            farNode = num;
        }

        for(Node node: tree[num]) {
            if(visited[node.num]) continue;
            findParentNodeDFS(node.num, len + node.len);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<N+1; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            while(true) {
                int num = Integer.parseInt(st.nextToken());
                if(num == -1)
                    break;

                int len = Integer.parseInt(st.nextToken());
                tree[v].add(new Node(num, len));
            }
        }

        findParentNodeDFS(1, 0);
        visited = new boolean[N+1];
        maxRadius = 0;
        findParentNodeDFS(farNode, 0);

        // findParentNodeBFS(1);
        // findParentNodeBFS(farNode);

        System.out.println(maxRadius);
    }
}