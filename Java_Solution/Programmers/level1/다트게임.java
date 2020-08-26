import java.util.*;

class 다트게임 {
    public int solution(String dartResult) {
        int answer = 0;
        char[] darts = dartResult.toCharArray();
        Stack<Integer> st = new Stack<>();
        int num = 0;
        
        for(int i=0; i<darts.length; i++) {
            char dart = darts[i];
            int charToNum = dart - '0';
            
            if(charToNum >= 0 && charToNum <= 10) {
                st.push(num);
                
                if(darts[i+1] == '0') {
                    num = 10;
                    i += 1;
                }
                else
                    num = charToNum;
                continue;
            }
            
            if(dart == 'D') {
                num *= num;
            }
            else if(dart == 'T') {
                num = (int)Math.pow(num, 3);
            }
                
            if(dart == '#') {
                num = -num;
            }
            else if(dart == '*') {
                if(st.size() != 0) {
                    st.push(st.pop() * 2);
                }
                num *= 2;
            }
        }
        
        st.push(num);
        
        while(!st.isEmpty()) {
            answer += st.pop();
        }
        
        return answer;
    }
}