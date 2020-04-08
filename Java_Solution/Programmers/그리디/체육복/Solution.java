import java.util.*;

class Solution {
    
    public int max(int n, int visited[], ArrayList<Integer> losts, ArrayList<Integer> reserves) {
        int answer = 0;
        for(int i=1; i<=n; i++) {
            if(visited[i]==1) continue;
            if(!losts.contains(i)) {
                System.out.println(i);
                answer++;
            } else if(losts.contains(i) && reserves.contains(i)) {
                visited[i] = 1;
                answer++;
            } else {
                if(i != 1 && reserves.contains(i-1)) {
                    if(!losts.contains(i-1) && visited[i-1]==0) {
                        visited[i] = 1;
                        visited[i-1] = 1;
                        answer++;
                        continue;
                    }
                }
                if(reserves.contains(i+1) && !losts.contains(i+1)) {
                    visited[i+1] = 1;
                    visited[i] = 1;
                    answer+=2;
                }
            }
        }
        return answer;
    }
    
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int visited[] = new int[n+1];
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        ArrayList<Integer> losts = new ArrayList<>();
        ArrayList<Integer> reserves = new ArrayList<>();
        
        for(int i:lost) {
            losts.add(i);
        }
        for(int i:reserve) {
            reserves.add(i);
        }
        
        answer = max(n, visited, losts, reserves);
        
        return answer;
    }
}