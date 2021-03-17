import java.io.*;
import java.util.*;

public class B9370 {
    static final int MAX = 10000000;
    static int N, M, T, S, G, H;
    static int[] minRoute;
    static ArrayList<Node>[] streets;
    static ArrayList<Integer> targets;

    static class Node implements Comparable<Node> {
        int s, weight;

        Node(int s, int weight) {
            this.s = s;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }

    public static void findMinRoute() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        que.offer(new Node(S, 0));
        minRoute[S] = 0;

        while(!que.isEmpty()) {
            Node node = que.poll();
            int s = node.s;

            if(visited[s]) continue;
            visited[s] = true;

            for(Node target: streets[s]) {
                int t = target.s;
                
                if(!visited[t] && minRoute[t] > minRoute[s] + target.weight) {
                    minRoute[t] = minRoute[s] + target.weight;
                    que.offer(new Node(t, minRoute[t]));
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
        while(testCase > 0) {
            testCase -= 1;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            minRoute = new int[N+1];
            Arrays.fill(minRoute, MAX);
            streets = new ArrayList[N+1];
            for(int i=0; i<N+1; i++) {
                streets[i] = new ArrayList<>();
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) * 2;

                if(a==G && b==H || a==H && b==G) {
                    streets[a].add(new Node(b, d-1));
                    streets[b].add(new Node(a, d-1));
                } else {
                    streets[a].add(new Node(b, d));
                    streets[b].add(new Node(a, d));
                }
            }

            targets = new ArrayList<>();
            for(int i=0; i<T; i++) {
                targets.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(targets);

            findMinRoute();
            for(int num: targets) {
                if(minRoute[num]%2 == 1) {
                    sb.append(num + " ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
