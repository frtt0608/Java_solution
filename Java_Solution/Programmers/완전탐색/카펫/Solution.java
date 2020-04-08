// brown 10	red 2
// return [4, 3]

// 8	1	
// [3, 3]
// 24	24
// [8, 6]

class Solution {
    public int[] solution(int brown, int red) {
        int[] answer = new int[2];
        
        int front = (brown-4)/2;
        int back = (int)Math.sqrt((int)Math.pow(front, 2) - 4*red);
        
        answer[0] = (front + back)/2 + 2;
        answer[1] = front - answer[0] + 4;
        
        return answer;
    }
}