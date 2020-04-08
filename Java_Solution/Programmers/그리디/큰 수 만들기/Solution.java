import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        char numbers[] = number.toCharArray();
        int idx = 0;
        char max;
        
        for(int i=0; i<number.length()-k; i++) {
            max = '0';
            for(int j=idx; j<number.length()-(number.length()-k-1)+i;j++) {
                if(max < numbers[j]) {
                    max = numbers[j];
                    idx = j+1;
                }
            }
            answer.append(Character.toString(max));
        }
        
        return answer.toString();
    }
}