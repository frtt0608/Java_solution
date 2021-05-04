import java.util.*;

public class 게임맵최단거리 {
    
    class Solution {
        int N, M, minRoute;
        
        class Node {
            int x, y;
            int count;
            
            Node(int x, int y, int count) {
                this.x = x;
                this.y = y;
                this.count = count;
            }
        }
        
        public boolean isWall(int x, int y) {
            if(x<0 || x>=N || y<0 || y>=M) return true;
            return false;
        }
        
        public void getMinRoute(int[][] maps) {
            int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
            Queue<Node> locateQue = new LinkedList<>();
            locateQue.offer(new Node(0, 0, 1));
            boolean[][] visited = new boolean[N][M];
            visited[0][0] = true;
            
            while(!locateQue.isEmpty()) {
                Node cur = locateQue.poll();
                
                if(cur.x == N-1 && cur.y == M-1) {
                    minRoute = cur.count;
                    return;
                }
                
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    
                    if(isWall(nx, ny) || maps[nx][ny] == 0) continue;
                    if(visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true;
                    locateQue.offer(new Node(nx, ny, cur.count + 1));
                }
            }
        }
        
        public int solution(int[][] maps) {
            N = maps.length;
            M = maps[0].length;
            
            minRoute = -1;
            getMinRoute(maps);
            
            return minRoute;
        }
    }
}
