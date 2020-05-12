// 수학적으로 접근하자.. 쉬운문제다.
// 몫으로 제한시간동안 심사할 수 있는 인원수 체크하는 점 숙지하기.
// 자료형에 유의하자


class Solution {
    private int times[];
    
    public long BinarySearch(long min, long max, int n) {
        long mid = 0;
        long resTime = Long.MAX_VALUE;
        long judgePass = 0;
        
        while(max >= min) {
            mid = (max+min)/2;
            judgePass = 0;
            
            for(int time: times) 
                judgePass += mid/time;
            
            if(judgePass >= n) {
                resTime = Math.min(resTime, mid);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        
        return resTime;
    }
    
    public long solution(int n, int[] times) {
        long maxTime = 0;
        this.times = times;
        for(int time: times) {
            maxTime = Math.max(maxTime, time);
        }
        
        return BinarySearch(1, maxTime*n, n);
    }
}