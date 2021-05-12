public class 괄호회전 {

    class Solution {
        
        public boolean checkCorrectCase(char[] chars, int index) {
            int[] openCnt = new int[3];
            Stack<Character> st = new Stack<>();
            int cnt = 0;
            
            while(cnt < chars.length) {
                
                switch(chars[index]) {
                    
                    case '(', '[', '{':
                        st.push(chars[index]);
                        break;
                    
                    case ')':
                        if(!st.isEmpty() && st.peek() == '(') {
                            st.pop();
                            break;
                        }
                        
                    case ']':
                        if(!st.isEmpty() && st.peek() == '[') {
                            st.pop();
                            break;
                        }
                        
                    case '}':
                        if(!st.isEmpty() && st.peek() == '{') {
                            st.pop();
                            break;
                        }
                        
                    default:
                        return false;
                }
                
                index += 1;
                if(index == chars.length) {
                    index = 0;
                }
                
                cnt += 1;
            }
            
            return st.isEmpty();
        }
        
        public int rotateDeq(String s) {
            int startIndex = 0;
            int answer = 0;
            char[] chars = s.toCharArray();
            
            while(startIndex < s.length()) {
                if(checkCorrectCase(chars, startIndex)) {
                    answer += 1;
                }
                
                startIndex += 1;
            }
            
            return answer;
        }
        
        public int solution(String s) {
            int answer = rotateDeq(s);
            
            return answer;
        }
    }
}
