// 완주하지 못한 선수
// ["leo", "kiki", "eden"], ["eden", "kiki"]
// "leo"
// ["mislav", "stanko", "mislav", "ana"], ["stanko", "ana", "mislav"]
// "mislav"

import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        int overlap = 0;
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String person: participant) {
            if(map.get(person) == null) {
                map.put(person, 1);
            } else {
                overlap = map.get(person) + 1;
                map.put(person, overlap);
            }
        }
        
        for(String success: completion) {
            if(map.get(success) > 1) {
                overlap = map.get(success) - 1;
                map.put(success, overlap);
            } else {
                map.remove(success);
            }
        }
        
        for(String key: map.keySet()) {
            answer += key;
        }
        return answer;
    }
}