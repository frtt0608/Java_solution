// numbers [1, 1, 1, 1, 1] target 3
// return 5;

class Solution {
    private int numbers[];
    int num_length;
    int answer;
    
    public void DFS(int depth, int res, int target) {
        if(depth >= num_length) {
            if(target == res) {
                answer++;
            }
            return;
        }
        depth += 1;
        DFS(depth, res + numbers[depth-1], target);
        DFS(depth, res - numbers[depth-1], target);
    }    
        
    public int solution(int[] numbers, int target) {
        answer = 0;
        this.numbers = numbers;
        num_length = numbers.length;
        
        DFS(0, 0, target);
        
        return answer;
    }
}