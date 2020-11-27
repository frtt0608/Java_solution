import java.util.*;

public class 배달 {

    static class Solution {
        static int answer;
        static int[][] timeArr;
        static int[] minRoute;
        static boolean[] visited;
        
        public static void deliveryCity(int K, int N) {
            Queue<Integer> cq = new LinkedList<>();
            minRoute[1] = 0;
            cq.offer(1);
            
            while(!cq.isEmpty()) {
                int ci = cq.poll();
                
                if(!visited[ci]) {
                    visited[ci] = true;
                    answer += 1;
                }
                
                for(int i=1; i<N+1; i++) {
                    if(timeArr[ci][i] == 0) continue;
                    
                    if(minRoute[ci] + timeArr[ci][i] < minRoute[i]) {
                        minRoute[i] = minRoute[ci] + timeArr[ci][i];
                        
                        if(minRoute[i] <= K) {
                            cq.offer(i);
                        }
                    }
                }
            }
        }
        
        public static int solution(int N, int[][] roads, int K) {
            answer = 0;
            timeArr = new int[N+1][N+1];
            minRoute = new int[N+1];
            Arrays.fill(minRoute, Integer.MAX_VALUE);
            visited = new boolean[N+1];
            
            for(int[] road: roads) {
                int city1 = road[0];
                int city2 = road[1];
                
                if(timeArr[city1][city2] == 0) {
                    timeArr[road[0]][road[1]] = road[2];
                    timeArr[road[1]][road[0]] = road[2];
                } else {
                    timeArr[road[0]][road[1]] = Math.min(timeArr[road[0]][road[1]], road[2]);
                    timeArr[road[1]][road[0]] = timeArr[road[0]][road[1]];
                }
                    
            }
        
            deliveryCity(K, N);
            
            return answer;
        }
        
        public static void main(String[] args) {
            int[][] roads = {};
            solution(5, roads, 3);
        }
    }
}
