import java.util.*;

class 컬러링북 {
    int numberOfArea, maxSizeOfOneArea;
    int M, N;
    boolean[][] visited;
    
    public void BFS(int[][] picture, int x, int y) {
        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,1,-1};
        
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y, picture[x][y]));
        visited[x][y] = true;
        int cnt = 1;
        
        while(!que.isEmpty()) {
            Node n = que.poll();
            int r = n.x;
            int c = n.y;
            int num = n.num;
            
            for(int dir=0; dir<4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                
                if(nr >= 0 && nr < M && nc >= 0 && nc < N) {
                    if(visited[nr][nc]) continue;
                    
                    if(num == picture[nr][nc]) {
                        visited[nr][nc] = true;
                        cnt += 1;
                        que.offer(new Node(nr, nc, num));
                    }
                }
            }
        }
        
        numberOfArea += 1;
        maxSizeOfOneArea = (int)Math.max(maxSizeOfOneArea, cnt);
    }    
    
    public int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        this.M = m;
        this.N = n;
        visited = new boolean[m][n];

        for(int x=0; x<m; x++) {
            for(int y=0; y<n; y++) {
                if(visited[x][y]) continue;
                if(picture[x][y] == 0) continue;
                
                BFS(picture, x, y);
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    class Node {
        int x, y;
        int num;
        
        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}