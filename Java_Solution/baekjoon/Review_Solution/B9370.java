import java.util.*;
import java.io.*;

public class B9370 {
    static final int MAX = 100000000;
    static int N, M, T, S, G, H;
    static int[] minRoute;
    static ArrayList<Node>[] streets;
    static ArrayList<Integer> targets;

    static class Node implements Comparable<Node> {
        int e, w;

        Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node n) {
            return this.w - n.w;
        }
    }

    public static void calculateMinRoute() {
        minRoute = new int[N+1];
        Arrays.fill(minRoute, MAX);
        minRoute[S] = 0;
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(S, 0));
        while(!que.isEmpty()) {
            Node node = que.poll();
            int s = node.e;

            if(visited[s]) continue;
            visited[s] = true;

            for(Node street: streets[s]) {

                if(minRoute[street.e] > minRoute[s] + street.w) {
                    minRoute[street.e] = minRoute[s] + street.w;
                    que.offer(new Node(street.e, minRoute[street.e]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            streets = new ArrayList[N+1];
            targets = new ArrayList<>();
            for(int i=1; i<N+1; i++) {
                streets[i] = new ArrayList<>();
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken())*2;

                if(a==G && b==H || a==H && b==G) {
                    streets[a].add(new Node(b, d-1));
                    streets[b].add(new Node(a, d-1));
                } else {
                    streets[a].add(new Node(b, d));
                    streets[b].add(new Node(a, d));
                }
            }

            for(int i=0; i<T; i++) {
                st = new StringTokenizer(br.readLine());
                targets.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(targets);

            calculateMinRoute();
            for(int target: targets) {
                if(minRoute[target]%2 == 1) {
                    sb.append(target+" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}   
