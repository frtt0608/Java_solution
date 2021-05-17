public class bit2개이하로다른비트 {
    
    class Solution {
    
        public Long getMinAnswer(long number) {
            long minAnswer = number;
            
            if(number%2 == 0) {
                minAnswer += 1;
            } else {
                long lastBit = (number + 1) & ~number;
                minAnswer = (number | lastBit) & ~(lastBit >> 1);
            }
                
            return minAnswer;
        }
        
        public long[] solution(long[] numbers) {
            long[] answer = new long[numbers.length];
            
            for(int i=0; i<numbers.length; i++) {
                answer[i] = getMinAnswer(numbers[i]);
            }
            
            return answer;
        }
    }
}
