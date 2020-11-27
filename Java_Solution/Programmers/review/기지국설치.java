public class 기지국설치 {
    class Solution {
        public int solution(int n, int[] stations, int w) {
            int answer = 0;
            
            int position = 1;
            int si = 0;
            
            while(position <= n) {
                if(si < stations.length && position >= stations[si] - w) {
                    position = stations[si] + w + 1;
                    si += 1;
                } else {
                    position += 2*w + 1;
                    answer += 1;    
                }
            }
    
            return answer;
        }
    }
}
