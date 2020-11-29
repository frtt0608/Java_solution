public class 도둑질 {

    class Solution {
        int[][] moneyDP;
        int N;
        
        public void stealMaxMoney(int[] money) {
            moneyDP[0][0] = money[0];
            moneyDP[0][1] = money[0];
            // 0번째 집 선택
            moneyDP[1][0] = 0;
            moneyDP[1][1] = money[1];
            // 0번째 집 선택x
            
            for(int i=2; i<N; i++) {
                moneyDP[0][i] = Math.max(moneyDP[0][i-2] + money[i], moneyDP[0][i-1]);
                moneyDP[1][i] = Math.max(moneyDP[1][i-2] + money[i], moneyDP[1][i-1]);
            }
        }
        
        public int solution(int[] money) {
            N = money.length;
            moneyDP = new int[2][N];
            stealMaxMoney(money);
            
            return (int)Math.max(moneyDP[0][N-2], moneyDP[1][N-1]);
        }
    }
}
