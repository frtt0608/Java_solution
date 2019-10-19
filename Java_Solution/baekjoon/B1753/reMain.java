import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.PriorityQueue;

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

public class reMain {
    static int V, E, K;
    static LinkedList<Node>[] uv;
    static int w[];

    static void BFS(int K, int sum) {
        PriorityQueue<Node> qu = new PriorityQueue<Node>();
        qu.add(new Node(K, sum));
        w[K] = sum;
        int s, v;
        while(!qu.isEmpty()) {
            Node node = qu.poll();
            s = node.getStart();
            v = node.getVal();
            for(int i=0; i<uv[s].size(); i++) {
                int a = uv[s].get(i).start;
                int b = uv[s].get(i).val;
                if(w[a] > b + v) {
                    w[a] = b + v;
                    qu.add(new Node(a, w[a]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        V=sc.nextInt();
        E=sc.nextInt();
        K=sc.nextInt();
        uv = new LinkedList[V+1];
        w = new int[V+1];
        for(int i=0; i<V+1; i++) {
            uv[i] = new LinkedList<Node>();
            w[i] = 9999999;
        }
        for(int i=0; i<E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            uv[u].add(new Node(v,w));
        }
        BFS(K,0);
        for(int i=1; i<V+1; i++) {
            if(w[i]==9999999) {
                System.out.println("INF");
            } else {
                System.out.println(w[i]);
            }
        }
    }
}