public class 약수의개수와덧셈 {
    class Solution {
        public int solution(int left, int right) {
            int answer = 0;
            
            for(int num=left; num<=right; num++) {
                int powNum = (int) Math.pow((int) Math.sqrt(num), 2);
                
                answer += powNum == num ? -num : num;
            }
            
            return answer;
        }
    }
}
