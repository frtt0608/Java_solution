import java.util.*;

public class 가장먼노드 {

    public static class Solution {
        static int maxRoute;
        static int[] minRoute;
        static List<Integer>[] nodeList;
        
        static class Node {
            int num;
            int count;
            
            Node(int num, int count) {
                this.num = num;
                this.count = count;
            }
        }
        
        public static void getRoute(int n) {
            Queue<Node> nq = new LinkedList<>();
            boolean[] visited = new boolean[n+1];
            nq.offer(new Node(1, 0));
            visited[1] = true;
            
            while(!nq.isEmpty()) {
                Node nd = nq.poll();
                int num = nd.num;
                int count = nd.count;
                List<Integer> target = nodeList[num];
                
                for(int idx=0; idx<target.size(); idx++) {
                    int y = target.get(idx);
                        
                    if(!visited[y]) {
                        visited[y] = true;
                        minRoute[y] = count + 1;
                        nq.offer(new Node(y, count+1));
                    } else {
                        if(minRoute[y] > minRoute[num] + 1) {
                            minRoute[y] = minRoute[num] + 1;
                            nq.offer(new Node(y, minRoute[y]));
                        }
                    }
                }
            }
            
            maxRoute = Arrays.stream(minRoute).max().getAsInt();
        }
        
        public static int solution(int n, int[][] edges) {
            int answer = 0;
            maxRoute = 0;
            minRoute = new int[n+1];
            nodeList = new ArrayList[n+1];
            
            for(int i=1; i<n+1; i++) 
                nodeList[i] = new ArrayList<>();
            
            for(int[] edge: edges) {
                int start = edge[0];
                int end = edge[1];
                nodeList[start].add(end);
                nodeList[end].add(start);
            }
            
            getRoute(n);
            for(int i=1; i<n+1; i++) {
                if(maxRoute == minRoute[i]) {
                    answer += 1;
                }
            }
            
            return answer;
        }
        
        public static void main(String[] args) {
            int n = 0;
            int[][] edges = {};
            
            solution(n, edges);
        }
    }
}
