import java.util.*;
import java.io.*;

class CHESS {
    int x;
    int y;
    int dir;
    
    CHESS(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

/**
 * Main
 */
public class B17837 {
    static int N, K, map[][], takeTime;
    static int[] dx = {0,0,0,-1,1}, dy={0,1,-1,0,0};
    static CHESS[] chessArr;
    static LinkedList<Integer> chessMap[][];

    static boolean wall(int x, int y) {
        if(x>N || x<=0 || y>N || y<=0) return true;
        return false;
    }

    static int changeDir(int dir) {
        if(dir==1) dir=2;
        else if(dir==2) dir=1;
        else if(dir==3) dir=4;
        else if(dir==4) dir=3;
        return dir;
    }

    static boolean moveChess(int num) {
        int x = chessArr[num].x;
        int y = chessArr[num].y;
        int dir = chessArr[num].dir;
        
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(wall(nx, ny) || map[nx][ny]==2) {
            dir = changeDir(dir);
            chessArr[num].dir = dir;
            nx = x + dx[dir];
            ny = y + dy[dir];
            if(wall(nx, ny) || map[nx][ny]==2) {
                return false;
            }
        }

        LinkedList<Integer> tempChess = new LinkedList<>();
        
        for(int i=chessMap[x][y].size()-1; i>=0; i--) {
            int chess = chessMap[x][y].get(i);
            tempChess.add(chess);
            chessMap[x][y].remove(i);
            if(chess == num) break;
        }

        if(map[nx][ny] == 0) {
            
            for(int i=tempChess.size()-1; i>=0; i--) {
                int chess = tempChess.get(i);
                chessMap[nx][ny].add(chess);
                chessArr[chess].x = nx;
                chessArr[chess].y = ny;
            }
        } else if(map[nx][ny] == 1)  {
            
            for(int i=0; i<tempChess.size(); i++) {
                int chess = tempChess.get(i);
                chessMap[nx][ny].add(chess);
                chessArr[chess].x = nx;
                chessArr[chess].y = ny;
            }
        }

        if(chessMap[nx][ny].size() >= 4) return true;
        
        return false;
    }


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        chessArr = new CHESS[K+1];
        chessMap = new LinkedList[N+1][N+1];
        takeTime = 0;

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                chessMap[i][j] = new LinkedList<>();
            }
        }

        for(int k=1; k<=K; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            chessArr[k] = new CHESS(x, y, dir);
            chessMap[x][y].add(k);
        }

        loop:
        while(true) {
            takeTime++;
            for(int k=1; k<=K; k++) {
                if(moveChess(k)) break loop;
            }
            if(takeTime>1000) {
                takeTime = -1;
                break;
            }


        }

        System.out.println(takeTime);
    }
}
