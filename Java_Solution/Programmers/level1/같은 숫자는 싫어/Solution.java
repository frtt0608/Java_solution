import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> st = new Stack<>();
        
        for(int i: arr) {
            if(st.size() == 0) st.push(i);
            
            if(st.peek() != i) st.push(i);
        }
        
        int[] answer = new int[st.size()];
        
        int idx = st.size()-1;
        while(!st.isEmpty()) {
            answer[idx] = st.pop();
            idx--;
        }
        
        return answer;
    }
}