class 소수찾기 {
    
    public int solution(int n) {
        int answer = 0;
        boolean[] nums = new boolean[1000001];
        
        for(int i=2; i<=Math.sqrt(n); i++) {
            if(nums[i]) continue;
            
            for(int j=2; j<=i; j++) {
                if(i%j == 0) {
                    
                    for(int k=j*j; k<=n; k+=j) {
                        nums[k] = true;
                    }
                }
            }
        }
        
        for(int i=2; i<=n; i++) {
            if(!nums[i]) answer += 1;
        }
        
        return answer;
    }
}