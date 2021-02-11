import java.io.*;
import java.util.*;

public class B1707 {
    static int V, E, graphCnt;
    static ArrayList<Integer>[] graph;
    static String result;
    static int[] visited;

    public static void discriminateBipartiteGraph(int v) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(v);
        visited[v] = 1;

        while(!que.isEmpty()) {
            int point = que.poll();

            for(int target : graph[point]) {
                if(visited[target] == 0) {
                    visited[target] = visited[point] * -1;
                    que.offer(target);
                } else if(visited[target] == visited[point]) {
                    result = "NO";
                    break;
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

            visited = new int[V+1];
            graph = new ArrayList[V+1];
            result = "YES";

            for(int i=0; i<V+1; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                graph[e].add(s);
            }

            for(int i=1; i<=V; i++) {
                if(visited[i] == 0) {
                    discriminateBipartiteGraph(i);
                }
                
                if(result.equals("NO")) break;
            }

            System.out.println(result);
        }
    }
}