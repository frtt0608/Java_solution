import java.util.HashSet;

class Solution {
    HashSet<Integer> prime_set;
    String nums[];
    int res, answer, visited[];
    
    public void perm(int n, int r, String num) {
        if(n==r) {
            if(num.equals("")) return;
            res = Integer.parseInt(num);
            if(prime(res)) prime_set.add(res);
            return;
        }
        
        for(int i=0; i<nums.length; i++) {
            if(visited[i]==1) continue;
            visited[i] = 1;
            perm(n, r+1, num+nums[i]);
            perm(n, r+1, num);
            visited[i] = 0;
        }
    }
    
    public Boolean prime(int res) {
        if(res==1 || res==0) return false;
        for(int i=2; i<=Math.sqrt(res); i++) {
            if(res%i==0) return false;
        }
        return true;
    }
    
    public int solution(String numbers) {
        answer = 0;
        prime_set = new HashSet<>();
        nums = numbers.split("");
        visited = new int[nums.length];
        
        perm(nums.length, 0, "");
        answer = prime_set.size();
        
        return answer;
    }
}