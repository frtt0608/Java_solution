// 중복을 피할 때는 Set 또는 Map을 생각하자!!!!

import java.util.Arrays;
import java.util.LinkedList;

class Solution1 {
    private String[] user_id, banned_id;
    private int answer;
    int v_idx;
    boolean v_Arr[][];
    
    public boolean checkVisited(boolean visited[], boolean list[]) {
        for(int i=0; i<visited.length; i++) {
            if(visited[i] != list[i]) {
                return false;
            }
        }
        return true;
    }
        
        
    public void check(boolean visited[], int cnt, int idx) {
        if(cnt==banned_id.length) {
            if(v_idx==0) {
                answer++;
                for(int i=0; i<visited.length; i++) {
                    v_Arr[v_idx][i] = visited[i];
                }
                v_idx++;
            } else {
                for(int i=0; i<v_idx; i++) {
                    if(checkVisited(visited, v_Arr[i])) return;
                }
                answer++;
                for(int i=0; i<visited.length; i++) {
                    v_Arr[v_idx][i] = visited[i];
                }
                v_idx++;
            }
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
        answer = 0;
        boolean visited[] = new boolean[user_id.length];
        v_Arr = new boolean[1000][user_id.length];
        v_idx = 0;
        this.user_id = user_id;
        this.banned_id = banned_id;
        
        check(visited, 0, 0);
        
        return answer;
    }
}