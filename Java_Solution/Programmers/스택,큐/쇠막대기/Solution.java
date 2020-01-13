// 쇠막대기
// "()(((()())(())()))(())"
// 17

import java.util.*;

class Solution {
    public int solution(String arrangement) {
        int answer = 0;
        Boolean flag = false;
        int open__ = 0;
        String[] args = arrangement.split("");
        
        for(String arg:args) {
            if(arg.equals("(")) {
                open__++;
                flag = true;
            } else {
                open__--;
                if(flag) {
                    answer += open__;
                } else {
                    answer += 1;
                }
                flag = false;
            }
        }
        
        return answer;
    }
}