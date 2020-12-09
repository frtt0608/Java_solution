import java.util.*;

public class 섬연결하기 {

    static class Solution {
        
        public int find(int[] node, int start) {
            if(node[start] == start) {
                return start;
            }
            
            return find(node, node[start]);
        }
        
        public void union(int[] node, int start, int target) {
            int s = find(node, start);
            int e = find(node, target);
            
            node[s] = e;
        }
        
        public int solution(int n, int[][] costs) {
            int answer = 0;
            int[] node = new int[n];
            for(int i=0; i<n; i++) {
                node[i] = i;
            }
            
            Arrays.sort(costs, (cost1, cost2) -> Integer.compare(cost1[2], cost2[2]));
            for(int[] cost: costs) {
                if(find(node, cost[0]) != find(node, cost[1])) {
                    union(node, cost[0], cost[1]);
                    answer += cost[2];
                }
            }
            
            return answer;
        }
    }
}
