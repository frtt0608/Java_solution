import java.util.*;

class 프렌즈4블록 {
    int M, N, answer;
    String[][] boards;
    
    public boolean chkSquare() {
        int[] dx = {1,1,0}, dy = {0,1,1};
        Set<Node> set = new HashSet<>();
        Queue<Node> temp_que = new LinkedList<>();
        boolean[][] visited = new boolean[M][N];
        
        for(int i=0; i<M-1; i++) {
            for(int j=0; j<N-1; j++) {
                if(boards[i][j].equals("X")) continue;
                
                temp_que.clear();
                temp_que.offer(new Node(i, j));
                for(int dir=0; dir<3; dir++) {
                    int ni = i + dx[dir];
                    int nj = j + dy[dir];
                    
                    if(boards[ni][nj].equals("X")) break;
                    if(boards[i][j].equals(boards[ni][nj])) {
                        temp_que.offer(new Node(ni, nj));
                    }
                }
                
                if(temp_que.size() == 4) {
                    while(!temp_que.isEmpty()) {
                        Node n = temp_que.poll();

                        set.add(new Node(n.x, n.y));
                    }
                }
            }
        }
        
        if(set.size() <= 0)
            return false;
        
        Iterator<Node> set_iter = set.iterator();
        while(set_iter.hasNext()) {
            Node n = set_iter.next();
            if(visited[n.x][n.y]) continue;
            
            visited[n.x][n.y] = true;
            boards[n.x][n.y] = "X";
            answer += 1;
        }
        
        return true;
    }
    
    public void downBlock() {
        Queue<String> que = new LinkedList<>();
        
        for(int j=0; j<N; j++) {
            for(int i=M-1; i>=0; i--) {
                if(!boards[i][j].equals("X")) {
                    que.offer(boards[i][j]);
                }
            } // X가 아닌 것들 que에 저장.
            
            for(int i=M-1; i>=0; i--) {
                if(que.size() > 0) {
                    boards[i][j] = que.poll();
                } else {
                    boards[i][j] = "X";
                }
            } // que 순서대로 저장, que가 비면 "X"를 저장.
        }
    }
    
    public int solution(int m, int n, String[] board) {
        answer = 0;
        M = m;
        N = n;
        boards = new String[M][N];
        
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                boards[i][j] = Character.toString(board[i].charAt(j));
            }
        }
        
        // chkSquare();
        while(true) {
            if(chkSquare()) {
                downBlock();
            } else
                break;
        }
        
        return answer;
    }
    
    public class Node {
        int x, y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}