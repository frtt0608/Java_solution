import java.util.*;
public class 소수만들기 {

    static class Solution {
        
        static int answer;
        static final int MAX = 3000;
        static boolean[] isPrime;
        static LinkedList<Integer> sumOfNum;
        
        public static void makePrimeArr() {
            for(int i=2; i<Math.sqrt(MAX)+1; i++) {
                if(!isPrime[i]) {
                    int primeNum = i*2;
                    
                    while(primeNum < MAX+1) {
                        isPrime[primeNum] = true;
                        primeNum += i;
                    }
                }
            }
        }
        
        public static void choiceThreeNums(int cnt, int idx, int sum, int[] nums) {
            if(cnt == 3) {
                sumOfNum.offer(sum);
                return;
            }
            
            for(int i=idx; i<nums.length; i++) {
                choiceThreeNums(cnt+1, i+1, sum+nums[i], nums);
            }
        }
        
        public static void countPrimeNum() {
            
            while(sumOfNum.size() > 0) {
                int setNum = sumOfNum.poll();
                if(!isPrime[setNum])
                    answer += 1;
            }
        }
        
        public static int solution(int[] nums) {
            answer = 0;
            isPrime = new boolean[MAX+1];
            sumOfNum = new LinkedList<>();
            
            makePrimeArr();
            choiceThreeNums(0, 0, 0, nums);
            countPrimeNum();

            return answer;
        }
        
        public static void main(String[] args) {
            solution(new int[] {1,2,3,4});
        }
    }
}
