import java.util.*;

class 보와기둥 {
    Node[][] map;
    final int PILLAR = 0;
    final int BO = 1;
    final int CONSTRUCT = 1;
    
    public boolean canConstruct_pillar(int x, int y) {
        return y == 1 || map[x][y-1].pillar || map[x-1][y].bo || map[x][y].bo;
    }
    
    public boolean canConstruct_bo(int x, int y) {
        return map[x][y-1].pillar || map[x+1][y-1].pillar ||(map[x-1][y].bo && map[x+1][y].bo);
    }
    
    public boolean canDestruct(int n) {
        for(int x=0; x<n+3; x++) {
            for(int y=0; y<n+3; y++) {
                if(map[x][y].pillar)
                    if(!canConstruct_pillar(x, y)) return false;
                
                if(map[x][y].bo)
                    if(!canConstruct_bo(x, y)) return false;
            }
        }
        
        return true;
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        map = new Node[n+3][n+3];
        int construct_cnt = 0;
        
        for(int x=0; x<n+3; x++) {
            for(int y=0; y<n+3; y++) {
                map[x][y] = new Node(false, false);
            }
        }
        
        for(int i=0; i<build_frame.length; i++) {
            int x = build_frame[i][0]+1;
            int y = build_frame[i][1]+1;
            int frame = build_frame[i][2];
            int command = build_frame[i][3];
            
            
            if(command == CONSTRUCT) {
                if(frame == PILLAR && canConstruct_pillar(x, y)) {
                    map[x][y].pillar = true;
                    construct_cnt++;
                } else if(frame == BO && canConstruct_bo(x, y)) {
                    map[x][y].bo = true;
                    construct_cnt++;
                }
            } else {
                construct_cnt--;
                if(frame == PILLAR) {
                    map[x][y].pillar = false;
                    
                    if(!canDestruct(n)) {
                        map[x][y].pillar = true;
                        construct_cnt++;
                    }
                } else {
                    map[x][y].bo = false;
                    
                    if(!canDestruct(n)) {
                        map[x][y].bo = true;
                        construct_cnt++;
                    }
                }
            }
        }
        
        
        int[][] answer = new int[construct_cnt][3];
        int idx = 0;
        for(int x=0; x<n+3; x++) {
            for(int y=0; y<n+3; y++) {
                if(map[x][y].pillar) answer[idx++] = new int[] {x-1, y-1, 0};
                if(map[x][y].bo) answer[idx++] = new int[] {x-1, y-1, 1}; 
            }
        }
        
        return answer;
    }
    
    public class Node {
        boolean pillar;
        boolean bo;
        
        Node(boolean pillar, boolean bo) {
            this.pillar = pillar;
            this.bo = bo;
        }
    }
}