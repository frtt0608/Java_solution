import java.util.*;

class Solution2 {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        char numbers[] = number.toCharArray();
        char chr;
        
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<numbers.length; i++) {
            chr = numbers[i];
            while(!stack.isEmpty() && stack.peek() < chr && k-- > 0) {
                stack.pop();
            }
            stack.push(chr);
        }
        if(k!=0) {
            while(k>0) {
                stack.pop();
                k--;
            }
        }
        for(int j=0; j<stack.size(); j++) answer.append(stack.get(j));
        
        return answer.toString();
    }
}