// 위장
// input
// [[yellow_hat, headgear], [blue_sunglasses, eyewear], [green_turban, headgear]]
// 5
// [[crow_mask, face], [blue_sunglasses, face], [smoky_makeup, face]]
// 3

import java.util.HashMap;
import java.util.Set;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        int val= 0;
        
        HashMap<String, Integer> closet = new HashMap<String, Integer>();
        
        for(int i=0; i<clothes.length; i++) {
            if(closet.get(clothes[i][1])==null) {
                closet.put(clothes[i][1], 2);
            } else {
                val = closet.get(clothes[i][1]) + 1;
                closet.put(clothes[i][1], val);
            }
        }
        for(String cloth:closet.keySet()) {
            answer *= closet.get(cloth);
        }
        answer -= 1;
        return answer;
    }
}