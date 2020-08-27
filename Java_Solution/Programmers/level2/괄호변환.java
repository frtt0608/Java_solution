import java.util.*;

class 괄호변환 {
    int pos;
    
    public boolean checkStr(String p) {
        int open = 0;
        int close = 0;
        Stack<Character> st = new Stack<>();
        boolean flag = true;
        
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) == '(') {
                open += 1;
                st.push('(');
            } else {
                close += 1;
                if(st.isEmpty())
                    flag = false;
                else
                    st.pop();
            }
            
            if(open == close) {
                pos = i+1;
                return flag;
            }
        }
        
        return true;
    }
    
    public String solution(String p) {
        if(p.isEmpty()) return p;
        boolean chk = checkStr(p);
        
        String u = p.substring(0, pos);
        String v = p.substring(pos, p.length());
        
        if(chk) {
            return u + solution(v);
        }
        
        String answer = "(" + solution(v) + ")";
        for(int i=1; i<u.length()-1; i++) {
            if(u.charAt(i) == '(') {
                answer += ")";
            } else {
                answer += "(";
            }
        }
        
        return answer;
    }
}