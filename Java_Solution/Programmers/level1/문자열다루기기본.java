class 문자열다루기기본 {
    public boolean solution(String s) {
        boolean answer = true;
        int len = s.length();
        
        if(len == 4 || len == 6) {
            for(int i=0; i<len; i++) {
                if((int)s.charAt(i) < 48 || (int)s.charAt(i) > 57) {
                    answer = false;
                    break;
                }
            }
        } else
            answer = false;
        
        return answer;
    }
}

// 정규식
// import java.util.*;

// class Solution {
//     public boolean solution(String s) {
//         return s.matches("^[0-9]{4}|{6}$");
//     }
// }