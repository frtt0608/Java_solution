import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

class Node implements Comparable<Node> {
    int num, price;

    Node(int num, int price) {
        this.num = num;
        this.price = price;
    }

    @Override
    public int compareTo(Node node) {
        return (this.price > node.price) ? 1:-1;
    }
}

public class reMain {
    static int N, M, S, D;
    static ArrayList<Node>[] city;
    static int W[];

    static void BUS(int s) {
        PriorityQueue<Node> qu = new PriorityQueue<Node>();
        qu.add(new Node(s, 0));
        int now, val;
        int start, now_val;
        
        while(!qu.isEmpty()) {
            Node bus = qu.poll();
            now = bus.num;
            val = bus.price;
            for(int i=0; i<city[now].size(); i++) {
                start = city[now].get(i).num;
                now_val = city[now].get(i).price;
                if(W[start] > now_val + val) {
                    W[start] = now_val + val;
                    qu.add(new Node(start, W[start]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        M=sc.nextInt();
        int u,v,p;
        city = new ArrayList[N+1];
        W = new int[N+1];
        for(int i=1; i<N+1; i++) {
            city[i] = new ArrayList<Node>();
            W[i] = 200000000;
        }
        for(int i=0; i<M; i++) {
            u=sc.nextInt();
            v=sc.nextInt();
            p=sc.nextInt();
            city[u].add(new Node(v,p));
        }
        S=sc.nextInt();
        D=sc.nextInt();
        BUS(S);
        System.out.println(W[D]);
    }
}