import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int board_N = board.length;
        Stack<Integer> cabinet = new Stack<>();
        
        for(int move: moves) {
            move--;
            for(int i=0; i<board_N; i++) {
                if(board[i][move] != 0) {
                    if(!cabinet.empty()) {
                        if(cabinet.peek() == board[i][move]) {
                            cabinet.pop();
                            answer += 2;
                            board[i][move] = 0;
                            break;
                        }
                    }
                    cabinet.push(board[i][move]);
                    board[i][move] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}