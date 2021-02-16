import java.util.*;
import java.io.*;

public class B11725 {
    static int N;
    static ArrayList<Integer>[] graph;
    static int[] parentArr;

    public static void findParentNode() {
        boolean[] visited = new boolean[N+1];
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        visited[1] = true;

        while(!que.isEmpty()) {
            int parent = que.poll();

            for(int sub: graph[parent]) {
                if(!visited[sub]) {
                    parentArr[sub] = parent;
                    visited[sub] = true;
                    que.offer(sub);   
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        parentArr = new int[N+1];

        for(int i=1; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            String[] input = br.readLine().split(" ");
            int n1 = Integer.parseInt(input[0]);
            int n2 = Integer.parseInt(input[1]);
            
            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        findParentNode();
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<N+1; i++) {
            sb.append(parentArr[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}