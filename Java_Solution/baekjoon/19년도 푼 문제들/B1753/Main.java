// 최단 경로
import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static int E;
    static int K;
    static ArrayList<Node>[] uv;
    static int[] v;
    static int[] weigh;

    static void BFS(int s, int sum) {
        PriorityQueue<Node> qu = new PriorityQueue<Node>();
        qu.add(new Node(s, sum));
        int node = 0;
        int deck = 0;
        weigh[s] = 0;

        while(!qu.isEmpty()){
            Node nn = qu.poll();
            int now = nn.getStart();
            int dist = nn.getVal();
            v[now] = 1;
            
            for(int i=0; i<uv[now].size(); i++) {
                node = uv[now].get(i).start;
                deck = uv[now].get(i).val;

                if(weigh[node] > dist + deck) {
                    weigh[node] = dist + deck;
                    qu.add(new Node(node, weigh[node]));
                }
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        V = in.nextInt();
        E = in.nextInt();
        K = in.nextInt();
        uv = new ArrayList[V+1];
        v = new int[V+1];
        weigh = new int[V+1];

        for(int i=0; i<=V; i++) {
            uv[i] = new ArrayList<Node>();
            weigh[i]=Integer.MAX_VALUE;
        }

        for(int i=0; i<E; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            
            uv[u].add(new Node(v, w));
        }
        
        BFS(K, 0);

        for(int i=1; i<=V; i++) {
            if(weigh[i] < Integer.MAX_VALUE)
                System.out.println(weigh[i]);
            else
                System.out.println("INF");
        }
    }
}

class Node implements Comparable<Node> {
    int start;
    int val;
 
    Node(int start, int val) {
        this.start = start;
        this.val = val;
    }

    @Override
    public int compareTo(Node node) {
        return (this.val > node.getVal()) ? 1 : -1;
    }
    
    public int getVal() {
        return val;
    }

    public int getStart() {
        return start;
    }
}