import java.util.*;

class Solution {
    boolean[] prime;
    int answer;
    int[] nums;
    
    public void chk(int num) {
        
        for(int i=2; i<=Math.sqrt(num); i++) {
            if(prime[i]) continue;
            
            int j = i*2;
            while(j <= num) {
                prime[j] = true;
                j += i;
            }
        }
    }
    
    public void perm(int n, int r, int idx, int sum) {
        if(r == 3) {
            // System.out.println(sum + ", " + prime[sum]);
            if(!prime[sum]) {
                // System.out.println(sum);
                answer += 1;
            }
            return;
        }
        
        if(idx >= n) return;
        
        perm(n, r+1, idx+1, sum+nums[idx]);
        perm(n, r, idx+1, sum);
    }
    
    public int solution(int[] nums) {
        answer = 0;
        Arrays.sort(nums);
        this.nums = nums;
        prime = new boolean[3001];
        
        chk(3000);
        perm(nums.length, 0, 0, 0);

        return answer;
    }
}