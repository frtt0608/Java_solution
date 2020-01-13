// 더 맵게
// [1, 2, 3, 9, 10, 12], 7
// 2

import java.util.*;

class Solution {
    public class Spicy implements Comparable<Spicy> {
        int scoville;
        
        Spicy(int scoville) {
            this.scoville = scoville;
        }
        
        @Override
        public int compareTo(Spicy target) {
            return this.scoville > target.scoville ? 1:-1;
        }
    }
    
    public int MIX(int NOT_spicy1, int NOT_spicy2) {
        return NOT_spicy1 + (NOT_spicy2)*2;
    }
    
    public int solution(int[] scoville, int K) {
        int answer = 0;
        int mix_up = 0;
        PriorityQueue<Spicy> qu = new PriorityQueue<Spicy>();
        
        for(int data: scoville) {
            qu.add(new Spicy(data));
        }
        
        while(!qu.isEmpty()) {
            Spicy spicy = qu.poll();
            if(spicy.scoville < K) {
                Spicy temp_spicy = qu.poll();
                mix_up = MIX(spicy.scoville, temp_spicy.scoville);
                qu.add(new Spicy(mix_up));
                answer++;
            }
            if(qu.size()==1 && qu.peek().scoville < K) {
                return -1;
            }
        }
        return answer;
    }
}