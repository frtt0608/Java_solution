public class 도둑질 {
    class Solution {
        int N;
        int[][] moneyDP;
        
        public void stealMaxMoney(int[] money) {
            moneyDP[0][0] = money[0];
            moneyDP[1][0] = money[0];
            
            moneyDP[0][1] = 0;
            moneyDP[1][1] = money[1];
            
            for(int i=2; i<N; i++) {
                moneyDP[i][0] = Math.max(moneyDP[i-2][0] + money[i], moneyDP[i-1][0]);
                moneyDP[i][1] = Math.max(moneyDP[i-2][1] + money[i], moneyDP[i-1][1]);
            }
        }
        
        public int solution(int[] money) {
            N = money.length;
            moneyDP = new int[N+1][2];
            stealMaxMoney(money);
            
            return Math.max(moneyDP[N-2][0], moneyDP[N-1][1]);
        }
    }
}
