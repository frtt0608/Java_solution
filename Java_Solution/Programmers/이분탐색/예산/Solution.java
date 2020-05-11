// 나대지말고 최솟값은 0으로 하자...

class Solution {
    int city_N, totalBudget;
    private int[] budgets;
    
    public int checkBudget(int mid) {
        int budget = 0;
        
        for(int i=0; i<city_N; i++) {
            if(budgets[i] < mid) {
                budget += budgets[i];        
            } else {
                budget += mid;
            }
        }
        
        return budget;
    }
    
    public int BinarySearch(int max, int min) {
        int mid = 0;
        int res = 0;
        
        while(max>=min) {
            mid = (max+min)/2;
            if(checkBudget(mid) > totalBudget) {
                max = mid-1;
            } else {
                res = Math.max(res, mid);
                min = mid+1;
            }
        }
        
        return res;
    }
    
    public int solution(int[] budgets, int M) {
        int answer = 0;
        city_N = budgets.length;
        totalBudget = M;
        this.budgets = budgets;
        int maxBudget = 0;
        
        for(int budget: budgets) {
            maxBudget = Math.max(maxBudget, budget);
        }
        
        answer = BinarySearch(maxBudget, 0);
        return answer;
    }
}