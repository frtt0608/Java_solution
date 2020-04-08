import java.util.*;

class Solution {
    int answer;
    String alphabet[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",                               "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String names[];
    
    public void up_down(String name_str) {
        int alpha_idx = Arrays.binarySearch(alphabet, name_str);
        if(alpha_idx <= 13) answer += alpha_idx;
        else answer += 26 - alpha_idx;
    }
    
    public int solution(String name) {
        answer = 0;
        names = name.split("");
        int nextIdx = 0; int Acnt = 0; int temp=0;
        int min_cursor_cnt = names.length - 1;
        
        for(int i=0; i<names.length; i++) {
            up_down(names[i]);
            
            if(names[i].equals("A")) {
                nextIdx = i+1;
                Acnt = 0;
                while(nextIdx < names.length && names[nextIdx].equals("A")) {
                    Acnt++;
                    nextIdx++;
                }
                temp = (i-1)*2 + (names.length-1 - i - Acnt);
                if(min_cursor_cnt > temp) min_cursor_cnt = temp;
            }
        }
        
        answer += min_cursor_cnt;
        return answer;
    }
}