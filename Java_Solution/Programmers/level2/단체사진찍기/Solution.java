import java.util.*;

class Solution {
    String[] data;
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    boolean[] visited;
    Map<Character, Integer> map;
    int answer;
    
    public boolean checkLine() {
        
        for(String str: data) {
            char friend1 = str.charAt(0);
            char friend2 = str.charAt(2);
            
            int size = str.charAt(4) - '0';
            
            int gap = Math.abs(map.get(friend1) - map.get(friend2))-1;
            // System.out.println(friend1 + ", " +frien2);
            // System.out.println(Math.abs(idx1-idx2));
            
            switch(str.charAt(3)) {
                case '=':
                    if(gap != 0) return false;
                    break;
                case '>':
                    if(gap <= size) return false;
                    break;
                case '<':
                    if(gap >= size) return false;
                    break;
            }
        }
        
        return true;
    }
    
    public void perm(int n, int r) {
        if(n == r) {
            if(checkLine()) {
                answer += 1;
            }
            return;
        }
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                map.put(friends[i], r);
                perm(n, r+1);
                map.remove(friends[i]);
                visited[i] = false;
            }
        }
    }
    
    public int solution(int n, String[] data) {
        answer = 0;
        this.data = data;
        visited = new boolean[8];
        map = new HashMap<>();
        
        perm(8, 0);
        
        return answer;
    }
}