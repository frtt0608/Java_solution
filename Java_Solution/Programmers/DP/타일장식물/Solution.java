// 정확도 100
// 효율성 0

import java.util.HashMap;

class Solution {
    HashMap<Integer, Integer> tile;
    
    public long solution(int N) {
        long answer = 0;
        tile = new HashMap<>();
        tile.put(0, 2);
        tile.put(1, 1);
        tile.put(2, 2);
        tile.put(3, 1);
        N -= 2;
        int dir[] = {0, 3, 2, 1};
        int changetemp = 0;
        int temp = 0;
        
        for(int i=0; i<N; i++) {
            if(dir[i%4]==0 || dir[i%4]==2) {
                changetemp = tile.get(0);
                temp = tile.get(1);
                tile.put(1, temp + changetemp);
                tile.put(3, temp + changetemp);
            } else {
                changetemp = tile.get(1);
                temp = tile.get(0);
                tile.put(0, temp + changetemp);
                tile.put(2, temp + changetemp);
            }
        }
        
        for(int i=0; i<4; i++) {
            answer += tile.get(i);
        }
        return answer;
    }
}