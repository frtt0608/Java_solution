public class 점프순간이동 {

    public static class Solution {
        public static int solution(int n) {
            int minSpendBattery = 0;
            
            while(n > 0) {
                if(n%2 == 0) n /= 2;
                else {
                    n -= 1;
                    minSpendBattery += 1;
                }
            }
            
            
            return minSpendBattery;
        }
        
        public static void main(String[] args) {
            solution(5);
        }
    }
}
