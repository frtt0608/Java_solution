import java.util.*;
import java.io.*;

public class B4803 {
    static int N, M;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static int checkIsTree(int root) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(root);
        int node = 0, edge = 0;

        while(!que.isEmpty()) {
            int start = que.poll();
            visited[start] = true;
            node += 1;

            for(int end: tree[start]) {
                edge += 1;
                if(!visited[end]) {
                    que.offer(end);
                }
            }
        }

        return edge/2+1 == node ? 1:0;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = 1;
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;

            int treeCount = 0;
            tree = new ArrayList[N+1];
            visited = new boolean[N+1];
            for(int i=1; i<N+1; i++)
                tree[i] = new ArrayList<>();

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int e1 = Integer.parseInt(st.nextToken());
                int e2 = Integer.parseInt(st.nextToken());

                tree[e1].add(e2);
                tree[e2].add(e1);
            }

            // 간선의 개수 = 정점의 개수 - 1
            // 양방향이므로 간선의개수는 2배
            // 간선의개수/2 + 1 = 정점의 개수
            for(int i=1; i<N+1; i++) {
                if(!visited[i]) {
                    treeCount += checkIsTree(i);               
                }
            }

            if(treeCount == 1) {
                sb.append("Case " + testCase + ": There is one tree.");
            } else if(treeCount > 1) {
                sb.append("Case " + testCase + ": A forest of " + treeCount + " trees.");
            } else {
                sb.append("Case " + testCase + ": No trees.");
            }
            sb.append("\n");

            testCase += 1;
        }
        System.out.println(sb.toString());
    }
}   
