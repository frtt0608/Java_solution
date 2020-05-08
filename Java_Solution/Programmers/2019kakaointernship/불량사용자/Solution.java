// 비트마스킹을 직접 함으로써 방문체크 역할!!
// 정규 표현식(Regular Expression)으로 특정 문자열의 패턴이 있는지 확인!!

import java.util.*;

class Solution2 {
    private String[] user_id, banned_id;
    HashSet<Integer> set;
    
    public void check(int idx, int num) {
        if(idx == banned_id.length) {
            set.add(num);
            return;
        }
        
        String reg = banned_id[idx].replaceAll("*", "[\\w\\d]");

        for(int i=0; i<user_id.length; i++) {
            if(!user_id[i].matches(reg) || ((num>>i) & 1) == 1) continue;
            check(idx+1, (num | (1<<i)));
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        this.user_id = user_id;
        this.banned_id = banned_id;
        
        check(0, 0);
        
        return set.size();
    }
}