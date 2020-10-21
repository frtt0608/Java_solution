import java.util.*;

class Solution {
    static int N, answer;
    static int[][] nBoard;
    static HashSet<Drone> checkSet;
    static Queue<Drone> que;
    
    public static void pushDrone(int x1, int y1, int x2, int y2, int time) {
        Drone drone = new Drone(x1,  y1, x2, y2, time);
        
        if(checkSet.contains(drone)) return;
        
        checkSet.add(drone);
        que.offer(drone);
    }
    
    public static void moveDrone() {
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        int[] rotate = {-1, 1};
        
        pushDrone(1, 1, 1, 2, 0);
        
        while(!que.isEmpty()) {
            Drone drone = que.poll();
            int x1 = drone.x1;
            int y1 = drone.y1;
            int x2 = drone.x2;
            int y2 = drone.y2;
            int time = drone.time;
            
            if((x2 == N && y2 == N) || (x1 == N && y1 == N)) {
                answer = time;
                return;
            }
            
            for(int dir=0; dir<4; dir++) {
                int nx1 = x1 + dx[dir];
                int ny1 = y1 + dy[dir];
                int nx2 = x2 + dx[dir];
                int ny2 = y2 + dy[dir];
                
                if(nBoard[nx1][ny1]==1 || nBoard[nx2][ny2]==1) continue;
                
                pushDrone(nx1, ny1, nx2, ny2, time+1);
            }
            
            // 회전
            if(x1 == x2) {
               for(int r=0; r<2; r++) {
                   int nx1 = x1 + rotate[r];
                   int ny1 = y1;
                   int nx2 = x2 + rotate[r];
                   int ny2 = y2;
                   
                   if(nBoard[nx1][ny1]==0 && nBoard[nx2][ny2]==0) {
                       pushDrone(nx1, y1, x1, y1, time+1);
                       pushDrone(nx2, y2, x2, y2, time+1);
                   }
               }
            } else if(y1 == y2) {
                for(int r=0; r<2; r++) {
                   int nx1 = x1;
                   int ny1 = y1 + rotate[r];
                   int nx2 = x2;
                   int ny2 = y2 + rotate[r];
                    
                    if(nBoard[nx1][ny1]==0 && nBoard[nx2][ny2]==0) {
                       pushDrone(x1, ny1, x1, y2, time+1);
                       pushDrone(x2, ny2, x2, y2, time+1);
                   }
               }
            }
        }
    }
    
    public static int solution(int[][] board) {
        answer = 0;        
        N = board.length;
        nBoard = new int[N+2][N+2];
        checkSet = new HashSet<>();
        que = new LinkedList<>();

        for(int i=0; i<N+2; i++) {
            for(int j=0; j<N+2; j++) {
                if(i==0 || j==0 || i==N+1 || j==N+1) {
                    nBoard[i][j] = 1;
                } else {
                    nBoard[i][j] = board[i-1][j-1];
                }
            }
        }
        
        moveDrone();
        
        return answer;
    }
    
    public static void main(String[] args) {
        int[][] board = {};
        
        solution(board);
    }
    
    public static class Drone {
        int x1;
        int y1;
        int x2;
        int y2;
        int time;
        
        Drone(int x1, int y1, int x2, int y2, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
        }
        
        @Override
        public int hashCode() {
            int prime = 31;
            int result = 1;
            
            result = prime * result + x1;
            result = prime * result + y1;
            result = prime * result + x2;
            result = prime * result + y2;
            
            return result;
        }
        
        @Override
        public boolean equals(Object obj) {
            Drone drone = (Drone)obj;
            
            if(this.x1 == drone.x1 && this.y1 == drone.y1 && this.x2 == drone.x2 && this.y2 == drone.y2) return true;
            if(this.x1 == drone.x2 && this.y1 == drone.y2 && this.x2 == drone.x1 && this.y2 == drone.y1) return true;
            
            return false;
        }
    }
}