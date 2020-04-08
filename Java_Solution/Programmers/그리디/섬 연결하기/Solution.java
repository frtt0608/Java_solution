// 실패!!
import java.util.*;

class Solution {
    int bridges[][], value[], visited[];
    int answer;
    private int costs[][];
    Queue<Integer> que;
    
    public void BFS() {
        que.offer(0);
        value[0] = 0;
        visited[0] = 1;
        while(!que.isEmpty()) {
            int island = que.poll();
            
            for(int i=0; i<bridges.length; i++) {
                if(bridges[island][i]==0) continue; 
                if(visited[i] == 1) {
                    // 가중치 비교 후 업데이트
                    if(value[i] > bridges[island][i]) {
                        value[i] = bridges[island][i];
                        que.offer(i);
                    }
                } else {
                    // 가중치 업데이트
                    value[i] = bridges[island][i];
                    visited[i] = 1;
                    que.offer(i);
                }
            }
        }
    }
    
    public int solution(int n, int[][] costs) {
        answer = 0;
        this.costs = costs;
        que = new LinkedList<>();
        
        bridges = new int[n][n];
        value = new int[n];
        visited = new int[n];
        
        for(int i=0; i<costs.length; i++) {
            bridges[costs[i][0]][costs[i][1]] = costs[i][2];
            bridges[costs[i][1]][costs[i][0]] = costs[i][2];
        }
        BFS();
        System.out.println(Arrays.toString(value));
        
        for(int i=1; i<n; i++) {
            answer += value[i];
        }
        return answer;
    }
}