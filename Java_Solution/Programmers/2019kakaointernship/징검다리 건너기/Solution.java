// 이진탐색으로 효율성 높이기
// 

class Solution {
    private int k;
    
    public boolean checkZero(int M, int[] stones) {
        int zero = 0;
        for(int i=0; i<stones.length; i++) {
            stones[i] -= M;
        }
        
        for(int i=0; i<stones.length; i++) {
            if(stones[i] < 0) {
                zero++;
                if(zero==k) return false;
            } else {
                zero=0;
            }
        }
        
        return true;
    }
    
    public int binarySearch(int stoneMax, int stoneMin, int stones[]) {
        int mid = 0;
        int res = 0;
        
        while(stoneMax >= stoneMin) {
            mid = (stoneMax + stoneMin)/2;
            
            if(!checkZero(mid, stones.clone())) {
                stoneMax = mid - 1;
            } else {
                res = Math.max(res, mid);
                stoneMin = mid + 1;
            }
        }    
        
        return res;
    }
    
    public int solution(int[] stones, int k) {
        this.k = k;
        int stoneMax = 0;
        int stoneMin = 200000001;
        
        for(int i=0; i<stones.length; i++) {
            stoneMax = Math.max(stoneMax, stones[i]);
            stoneMin = Math.min(stoneMin, stones[i]);
        }
        
        return binarySearch(stoneMax, stoneMin, stones);
    }
}