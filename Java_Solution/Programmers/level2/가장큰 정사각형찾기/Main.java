import java.util.*;

class Solution
{
    int[][] board;
    int min;

    public int solution(int[][] board)
    {
        this.board = board;
        int answer = 0;
        int R = board.length;
        int C = board[0].length;
        min = 1;

        for(int i=1; i<R; i++) {
            for(int j=1; j<C; j++) {
                if(board[i][j] >= 1) {
                    int flag = chkBoard(i, j);

                    if(flag == 0)
                        continue;
                    else if(flag == 1) {
                        board[i][j] = board[i][j-1] + 1;
                        answer = Math.max(answer, board[i][j]);
                    } else if(flag == 2) {
                        board[i][j] = min+1;
                    }
                }
            }
        }

        // for(int i=0; i<R; i++) {
        //     System.out.println(Arrays.toString(board[i]));
        // }
        if(R == 1 && C == 1) {
            answer = board[0][0];
        }

        return answer*answer;
    }

    public int chkBoard(int i, int j) {
        int[] dx = {0,-1,-1}, dy = {-1,0,-1};

        if(board[i][j-1] == 0 || board[i-1][j] == 0 || board[i-1][j-1] == 0) {
            return 0;
            // 0인 경우, continue;
        }

        if(board[i][j-1] == board[i-1][j] && board[i-1][j-1] == board[i][j-1]) {
            return 1;
            // 같은 경우, += 1;
        }

        min = Integer.MAX_VALUE;
        // System.out.println("입력값 : " + i + ", " + j);
        for(int dir=0; dir<3; dir++) {
            int nx = i + dx[dir];
            int ny = j + dy[dir];

            // System.out.println(dir + ": "+ nx + ", " + ny);
            min = Math.min(min, board[nx][ny]);
        }

        return 2;
    }
}