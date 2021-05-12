public class 행렬테두리회전 {
    
    class Solution {
    
        public int[][] initMap(int rows, int columns) {
            int[][] map = new int[rows+1][columns+1];
            int num = 1;
            
            for(int i=1; i<rows+1; i++) {
                for(int j=1; j<columns+1; j++) {
                    map[i][j] = num++;
                }
            }
            
            return map;
        }
        
        
        
        public void rotateMap(int[][] map, int[] query, int idx, int[] answer) {
            int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
            int sx = query[0];
            int sy = query[1];
            int ex = query[2];
            int ey = query[3];
            int x = sx;
            int y = sy;
            
            int temp = map[sx][sy];
            int minValue = temp;
            
            int dir = 0;
            while(dir < 4) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                if(nx > ex || ny > ey || nx < sx || ny < sy) {
                    nx -= dx[dir];
                    ny -= dy[dir];
                    dir += 1;
                } else {
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;
                    minValue = Math.min(minValue, map[x][y]);
                }
            }
            
            map[sx][sy+1] = temp;
            answer[idx] = minValue;
        }
        
        public int[] solution(int rows, int columns, int[][] queries) {
            int[] answer = new int[queries.length];
            int[][] map = initMap(rows, columns);
            
            for(int i=0; i<queries.length; i++) {
                rotateMap(map, queries[i], i, answer);
            }
            
            return answer;
        }
    }
}
