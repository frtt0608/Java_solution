// 이진탐색에서 min, max값 설정과
// while문에서 min, max값 재설정을 위한 조건 파악이 어렵다..
// 문제에서 제시한 n개의 바위삭제를 기준으로 접근.
// while문마다 삭제한 바위와 n을 비교하면서 min, max값 재설정!

import java.util.Arrays;

class Solution {
    private int rocks[], distance;
    
    public int BinarySearch(int min, int max, int n) {
        int mid = 0;
        int resDistance = 0;
        int removeRock;
        int prevRock;
        
        while(max >= min) {
            mid = (max+min)/2;
            removeRock = 0;
            prevRock = 0;
            
            for(int i=0; i<rocks.length; i++) {
                if(rocks[i] - prevRock < mid) {
                    removeRock++;
                } else {
                    prevRock = rocks[i];
                }
            }
            
            if(distance - prevRock < mid) removeRock++;
            
            if(removeRock > n) {
                max = mid-1;
            } else {
                min = mid+1;
                resDistance = Math.max(resDistance, mid);
            }
        }
        
        return resDistance;
    } 
    
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        this.rocks = rocks;
        this.distance = distance;
        
        return BinarySearch(1, distance, n);
    }
}