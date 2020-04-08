// n 3, computers [[1, 1, 0], [1, 1, 0], [0, 0, 1]]
// return 2

// n 3, computers [[1, 1, 0], [1, 1, 1], [0, 1, 1]]
// return 1

import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private int computers[][];
    int computer_cnt, visited[];
    Queue<Integer> network;
    
    public void BFS(int num, int visited[]) {
        network.offer(num);
        visited[num] = 1;
        int com = 0;
        while(!network.isEmpty()) {
            com = network.poll();
            for(int i=0; i<computer_cnt; i++) {
                if((i == num) || visited[i]==1) continue;
                if(computers[com][i]==1) {
                    visited[i] = 1;
                    network.offer(i);
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        this.computers = computers;
        network = new LinkedList<>();
        computer_cnt = n;
        visited = new int[n];
        
        for(int i=0; i<n; i++) {
            if(visited[i]==0) {
                BFS(i, visited);
                answer++;
            }
        }
        
        return answer;
    }
}