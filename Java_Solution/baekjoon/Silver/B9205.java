import java.io.*;
import java.util.*;

public class B9205 {

    static int N, M, targetX, targetY;
    static int[][] stores;
    static boolean[] visited;
    static boolean flag;
    static StringBuilder sb;

    static class Node {
        int x, y;
        int beerCount;

        Node(int x, int y, int beerCount) {
            this.x = x;
            this.y = y;
            this.beerCount = beerCount;
        }
    }

    public static int calculateDistance(int x, int y, int ex, int ey) {
        return Math.abs(x-ex) + Math.abs(y-ey);
    }

    public static void moveToTarget(int x, int y) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y, 20));

        while(!que.isEmpty()) {
            Node node = que.poll();
            x = node.x;
            y = node.y;

            if(x == targetX && y == targetY) {
                flag = true;
                return;
            }

            for(int i=0; i<N+1; i++) {
                if(visited[i]) continue;

                int distance = calculateDistance(x, y, stores[i][0], stores[i][1]);
                if(distance <= node.beerCount*50) {
                    visited[i] = true;
                    que.offer(new Node(stores[i][0], stores[i][1], 20));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int homeX = Integer.parseInt(st.nextToken());
            int homeY = Integer.parseInt(st.nextToken());

            stores = new int[N+1][2];
            visited = new boolean[N+1];
            flag = false;

            for(int i=0; i<N+1; i++) {
                st = new StringTokenizer(br.readLine());
                stores[i][0] = Integer.parseInt(st.nextToken());
                stores[i][1] = Integer.parseInt(st.nextToken());
            }

            targetX = stores[N][0];
            targetY = stores[N][1];

            moveToTarget(homeX, homeY);

            if(flag)
                sb.append("happy\n");
            else
                sb.append("sad\n");
        }

        System.out.println(sb.toString());
    }
}
