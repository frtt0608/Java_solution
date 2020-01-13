// 주식가격
// [1,2,3,2,3]
// [4,3,1,1,0]

// 풀이 2
import java.util.*;

class Solution {
    
    public int[] solution(int[] prices) {
        int input_length = prices.length;
        int[] answer = new int[input_length];
        Stack<Integer> st = new Stack<>();
        int idx = 0;
        
        st.push(0);
        for(int i=1; i<input_length; i++) {
            while(!st.isEmpty() && prices[i] < prices[st.peek()]) {
                idx = st.pop();
                answer[idx] = i-idx;
            }
            st.push(i);
        }
        
        while(!st.isEmpty()) {
            idx = st.pop();
            answer[idx] = input_length - idx - 1;
        }
        
        return answer;
    }
}