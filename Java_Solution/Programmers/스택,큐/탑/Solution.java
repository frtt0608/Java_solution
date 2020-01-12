// 탑
// [6,9,5,7,4]
// [0,0,2,2,4]

// [3,9,9,3,5,7,2]
// [0,0,0,3,3,3,6]

class Solution {
    public int[] answer;
    
    public void DFS(int idx, int target, int[] heights) {
        if(idx<0) {
            answer[target] = 0;
            return;
        }
        else {
            if(heights[idx] > heights[target]) {
                answer[target] = idx+1;
            } else {
                DFS(idx-1, target, heights);
            }
        }
    }
    public int[] solution(int[] heights) {
        int top_length = heights.length;
        answer = new int[top_length];
        for(int i=top_length-1; i>0; i--) {
            DFS(i-1, i, heights);
        }
        return answer;
    }
}