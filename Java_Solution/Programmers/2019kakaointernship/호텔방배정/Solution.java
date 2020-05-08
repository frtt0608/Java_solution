// Node클래스를 만들어서 Set으로 찾기보단 Map으로 접근하자
// num+1이 아니라 map에 저장된 value로 접근.
// key-value를 부모-자식관계로 접근
// 부모노드 update하는 크루스칼(?)알고리즘 비슷..

import java.util.*;

class Solution {
    HashMap<Long, Long> roomMap;
    
    public long getRoom(long num) {
        if(!roomMap.containsKey(num)) {
            roomMap.put(num, num+1);
            return num;
        }
        roomMap.put(num, getRoom(roomMap.get(num)));
        return roomMap.get(num);
    }
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        roomMap = new HashMap<>();
        
        for(int i=0; i<room_number.length; i++) {
            answer[i] = getRoom(room_number[i]);
        }
        
        return answer;
    }
}