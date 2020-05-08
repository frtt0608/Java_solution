// Set을 쓰고 비트마스킹처럼 배열을 정수로 변환해서
// Set에 저장

import java.util.Arrays;
import java.util.*;

class Solution2 {
    private String[] user_id, banned_id;
    HashSet<Integer> set;
    
        
    public void check(boolean visited[], int cnt, int idx) {
        if(cnt == banned_id.length) {
            int num = 0;
            for(int i=0; i<visited.length; i++) {
                if(visited[i]) num += Math.pow(2, i);
            }
            set.add(num);
            return;
        }
        
        loop:
        for(int i=0; i<user_id.length; i++) {
            if(visited[i]) continue;
            if(banned_id[idx].length() == user_id[i].length()) {
                for(int j=0; j<user_id[i].length(); j++) {
                    if(banned_id[idx].charAt(j) == '*') continue;
                    if(banned_id[idx].charAt(j) != user_id[i].charAt(j)) {
                        continue loop;
                    }
                }
                visited[i] = true;
                check(visited, cnt+1, idx+1);
                visited[i] = false;
            }
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        boolean visited[] = new boolean[user_id.length];
        set = new HashSet<>();
        this.user_id = user_id;
        this.banned_id = banned_id;
        
        check(visited, 0, 0);
        
        return set.size();
    }
}