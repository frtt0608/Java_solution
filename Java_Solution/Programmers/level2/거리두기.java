import java.util.*;

public class 거리두기 {

    class Solution {
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
        
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
            return x<0 || x>=5 || y<0 || y>=5;
        }
        
        public boolean checkDistanceCount(int x, int y, 
                                        String[] place) {
            Queue<Node> que = new LinkedList<>();
            boolean[][] visited = new boolean[place.length][place[0].length()];
            
            que.offer(new Node(x, y, 0));
            visited[x][y] = true;
            
            while(!que.isEmpty()) {
                Node cur = que.poll();
                x = cur.x;
                y = cur.y;
                
                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    
                    if(isWall(nx, ny) || visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true;
                    
                    if(place[nx].charAt(ny) == 'O') {
                        que.offer(new Node(nx, ny, cur.count+1));
                        
                    } else if(place[nx].charAt(ny) == 'P' && cur.count+1 <= 2) {
                        return false;
                    }
                }
            }
            
            return true;
        }
        
        public boolean checkCondition(String[] place) {
            
            for(int i=0; i<place.length; i++) {
                for(int j=0; j<place[i].length(); j++) {
                    
                    if(place[i].charAt(j) == 'P') {
                        if(!checkDistanceCount(i, j, place)) {
                            return false;
                        }
                    }
                }
            }
            
            return true;
        }
        
        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
            
            for(int i=0; i<places.length; i++) {
                answer[i] = checkCondition(places[i]) == true ? 1:0;
            }
            
            return answer;
        }
    }
}
