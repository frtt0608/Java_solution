// 라면공장
// 4 [4,10,15] [20,5,10] 30
// 2

import java.util.*;

class Solution {
    
    public int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        int idx = 0;
        
        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());
        
        while(k-stock > 0) {
            while(idx < dates.length && dates[idx] <= stock) {
                qu.add(new Integer(supplies[idx]));
                idx++;
            }
            answer++;
            stock += qu.poll();
        }
        return answer;
    }
}