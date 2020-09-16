import java.util.*;

class 자물쇠와열쇠 {
    int[][] lock;
    boolean answer;
    int N, M;
    
    public int[][] rotateKey(int[][] key) {
        int[][] tempKey = new int[M][M];
        
        for(int i=0; i<M; i++) {
            for(int j=0; j<M; j++) {
                tempKey[i][j] = key[M-1-j][i];
            }
        }
        
        return tempKey;
    }
    
    public int[][] moveKey(int[][] key, int i, int j) {
        int[][] tempKey = new int[N][N];
        
        for(int x=0; x<M; x++) {
            for(int y=0; y<M; y++) {
                if(x+i>=N || x+i<0 || y+j>=N || y+j<0) continue;
                
                // System.out.println(x+i + ", " + (y+j));
                tempKey[x+i][y+j] = key[x][y];
            }
        }
        
        return tempKey;
    }
    
    public boolean chkLock(int[][] key) {
        // System.out.println("key");
        
        for(int i=1-M; i<N; i++) {
            for(int j=1-M; j<N; j++) {
                int[][] tempKey = moveKey(key, i, j);
                
                if(isUnlock(tempKey)) {
                    answer = true;
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isUnlock(int[][] key) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(key[i][j] + lock[i][j] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        this.lock = lock;
        M = key.length;
        N = lock.length;
        answer = false;
        
        for(int dir=0; dir<4; dir++) {
            if(chkLock(key)) break;
            
            key = rotateKey(key);
        }
        
        return answer;
    }
}