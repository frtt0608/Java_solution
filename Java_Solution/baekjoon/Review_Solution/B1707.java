import java.util.*;
import java.io.*;

public class B1707 {
    static int V, E;
    static int[] visited;
    static ArrayList<Integer>[] graph;
    static String result;

    public static void checkBipartiteGraph(int v) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(v);
        visited[v] = 1;

        while(!que.isEmpty()) {
            int st = que.poll();

            for(int et : graph[st]) {
                if(visited[et] == 0) {
                    visited[et] =  (-1) * visited[st];
                    que.offer(et);
                } else if(visited[st] == visited[et]) {
                    result = "NO";
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            result = "YES";
            visited = new int[V+1];

            graph = new ArrayList[V+1];
            for(int i=1; i<V+1; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                
                graph[s].add(e);
                graph[e].add(s);
            }

            for(int i=1; i<V+1; i++) {
                if(result.equals("NO")) break;
                if(visited[i] == 0) checkBipartiteGraph(i);
            }
            
            System.out.println(result);
        }
    }
}