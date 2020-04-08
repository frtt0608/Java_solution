import java.util.*;

class Solution {
    
    public int solution(int[] weight) {
        int answer = 0;
        Arrays.sort(weight);
        int min_val = 0;
        
        for(int i=0; i<weight.length; i++) {
            if(min_val+1 >= weight[i]) {
                min_val += weight[i];
            } else break;
        }
        answer = min_val + 1;
        return answer;
    }
}