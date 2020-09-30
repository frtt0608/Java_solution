import java.util.*;

class 보와기둥 {
    Node[][] map;
    int n;
    final int PILLAR = 0;
    final int BO = 1;
    final int CONSTRUCT = 1;
    
    public boolean canConstructPillar(int x, int y) {
        return y==1 || map[x][y-1].pillar || map[x][y].bo || map[x-1][y].bo;
    }
    
    public boolean canConstructBo(int x, int y) {
        return map[x][y-1].pillar || map[x+1][y-1].pillar || (map[x-1][y].bo && map[x+1][y].bo);
    }
    
    public boolean isPossible() {
        for(int i=1; i<=n+1; i++) {
            for(int j=1; j<=n+1; j++) {
                if(map[i][j].pillar && !canConstructPillar(i, j))
                    return false;
                
                if(map[i][j].bo && !canConstructBo(i, j))
                    return false;
            }
        }
        
        return true;
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        int totalFrameCnt = 0;
        this.n = n;
        map = new Node[n+3][n+3];
        
        for(int i=0; i<n+3; i++) {
            for(int j=0; j<n+3; j++) {
                map[i][j] = new Node(false, false);
            }
        }
        
        for(int i=0; i<build_frame.length; i++) {
            int x = build_frame[i][0] + 1;
            int y = build_frame[i][1] + 1;
            int frame = build_frame[i][2];
            int Command_type = build_frame[i][3];
            
            if(Command_type == CONSTRUCT) {
                if(frame == PILLAR && canConstructPillar(x, y)) {
                    map[x][y].pillar = true;
                    totalFrameCnt++;
                } else if(frame == BO && canConstructBo(x, y)){
                    map[x][y].bo = true;
                    totalFrameCnt++;
                }
            } else {
                // 기둥 삭제
                if(frame == PILLAR) {
                    map[x][y].pillar = false;
                    totalFrameCnt--;
                    
                    if(!isPossible()) {
                        map[x][y].pillar = true;
                        totalFrameCnt++;
                    }
                } else {
                    map[x][y].bo = false;
                    totalFrameCnt--;
                    
                    if(!isPossible()) {
                        map[x][y].bo = true;
                        totalFrameCnt++;
                    }
                }
            }
        }
        
        answer = new int[totalFrameCnt][3];
        int idx = 0;
        
        for(int i=1; i<=n+1; i++) {
            for(int j=1; j<=n+1; j++) {
                if(map[i][j].pillar) {
                    answer[idx][0] = i-1;
                    answer[idx][1] = j-1;
                    answer[idx++][2] = 0;
                }
                if(map[i][j].bo) {
                    answer[idx][0] = i-1;
                    answer[idx][1] = j-1;
                    answer[idx++][2] = 1;
                }
            }
        }
        
        // Arrays.sort(answer, new Comparator<int[]>() {
        //    @Override
        //     public int compare(int[] arr1, int[] arr2) {
        //         if(arr1[0] == arr2[0] && arr1[1] == arr2[1]) {
        //             if(arr2[2] == 0) return 1;
        //         } else if(arr1[0] == arr2[0]) {
        //             return arr1[1] - arr2[1];
        //         }
        //         return arr1[0] - arr2[0];
        //     }
        // });
        
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