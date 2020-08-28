import java.util.*;

class 괄호변환 {
    int pos;
    
    public boolean checkStr(String p) {
        int cnt = 0;
        boolean flag = true;
        
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) == '(') {
                cnt += 1;
            } else {
                if(cnt == 0) {
                    cnt -= 1;
                    flag = false;
                } else {
                    cnt -= 1;
                }
            }
            
            if(cnt == 0) {
                pos = i+1;
                return flag;
            }
        }
        
        return true;
    }
    
    public String solution(String p) {
        if(p.length() == 0) return "";
        
        boolean flag = checkStr(p);
        
        String u = p.substring(0, pos);
        String v = p.substring(pos, p.length());
        
        if(flag) {
            return u + solution(v);
        }
        
        String answer = "(" + solution(v) + ")";
        
        for(int i=1; i<u.length()-1; i++) {
            if(u.charAt(i) == '(') answer += ")";
            else answer += "(";
        }
        
        return answer;
    }
}