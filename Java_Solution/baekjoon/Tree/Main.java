import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

   

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

            for(int i=1; i<N+1; i++) {
                if(!visited[i]) {

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
