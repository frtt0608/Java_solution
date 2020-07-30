// 해킹

import java.util.*;
import java.io.*;

public class Main {
    static int n, d, c, totalCnt, totalTime;
    static int[] minTime;
    static ArrayList<Node>[] comp;

    static void hacking_BFS() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(c, 0));
        minTime[c] = 0;

        while(!que.isEmpty()) {
            Node n = que.poll();
            int num = n.num;

            if(minTime[num] < n.time) continue;

            for(Node node : comp[num]) {
                int idx = node.num;
                int idx_time = node.time;

                if(minTime[idx] > minTime[num] + idx_time) {
                    minTime[idx] = minTime[num] + idx_time;
                    que.offer(new Node(idx, minTime[idx]));
                }
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        
        for(int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            comp = new ArrayList[n+1];
            minTime = new int[n+1];
            Arrays.fill(minTime, Integer.MAX_VALUE);
            totalTime = 0;
            totalCnt = 0;

            for(int i=0; i<=n; i++) {
                comp[i] = new ArrayList<>();
            }

            for(int i=0; i<d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                comp[b].add(new Node(a, s));
            }

            hacking_BFS();

            for(int i=1; i<=n; i++) {
                if(minTime[i] != Integer.MAX_VALUE) {
                    totalCnt += 1;
                    totalTime = Math.max(totalTime, minTime[i]);
                }
            }

            System.out.println(totalCnt + " " + totalTime);
        }
    }

    static class Node implements Comparable<Node> {
        int num;
        int time;

        Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node node) {
            return this.time - node.time;
        }
    }
}