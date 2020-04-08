class Solution {
    private int target, numbers[];
    int answer;
    
    public void DFS(int n, int r, int num) {
        if(n==r) {
            if(target==num) answer++;
            return;
        }
        
        DFS(n, r+1, num + numbers[r]);
        DFS(n, r+1, num - numbers[r]);
    }
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        this.target = target;
        this.numbers = numbers;
        DFS(numbers.length, 0, 0);
        
        return answer;
    }
}