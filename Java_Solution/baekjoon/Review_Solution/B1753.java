import java.util.*;
import java.io.*;

public class B1753 {
    static final int MAX = 200001;
    static int V, E;
    static int[] minRoute;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node> {
        int num, weight;

        Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }

    public static void findMinRoute(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        boolean[] visited = new boolean[V+1];
        minRoute[start] = 0;

        while(!pq.isEmpty()) {
            Node n = pq.poll();
            start = n.num;

            if(visited[start]) continue;
            visited[start] = true;

            for(Node node: graph[start]) {
                int end = node.num;

                if(minRoute[end] > minRoute[start] + node.weight && !visited[end]) {
                    minRoute[end] = minRoute[start] + node.weight;
                    pq.offer(new Node(end, minRoute[end]));
                }
            }
        }
    }

    public static void printMinRoute() {
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<V+1; i++) {
            if(minRoute[i] == MAX) {
                sb.append("INF\n");
            } else {
                sb.append(minRoute[i]+"\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList[V+1];
        minRoute = new int[V+1];
        Arrays.fill(minRoute, MAX);

        for(int i=1; i<V+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        findMinRoute(start);
        printMinRoute();
    }
}   
