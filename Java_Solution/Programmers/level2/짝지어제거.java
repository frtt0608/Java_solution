import java.util.*;

public class 짝지어제거 {

    static class Solution
    {
        public static int solution(String s)
        {
            Stack<Character> st = new Stack<>();
            char[] inputStringArr = s.toCharArray();
            
            for(char chr: inputStringArr) {
                if(!st.isEmpty() && st.peek() == chr)
                    st.pop();
                else
                    st.push(chr);
            }
            
            return st.isEmpty() ? 1:0;
        }
        
        public static void main(String[] args) {
            String s = "";
            solution(s);
        }
    }
}
