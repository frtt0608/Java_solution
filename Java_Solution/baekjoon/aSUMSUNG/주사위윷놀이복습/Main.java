import java.util.*;
import java.io.*;

class CHESS {
    int mapidx;
    int position;

    CHESS() {
        this.mapidx = 0;
        this.position = 0;
    }

    CHESS(int mapidx, int position) {
        this.mapidx = mapidx;
        this.position = position;
    }
}
/**
 * Main
 */
public class Main {
    static int map[][] = {{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,41},
                        {10,13,16,19,25},
                        {20,22,24,25},
                        {30,28,27,26,25},
                        {25,30,35,40}};
    static int dice[], resScore;

    static public void allCase(int cnt, int visited[]) {
        if(cnt==10) {
            moveChess(visited);
            return;
        }

        for(int i=1; i<=4; i++) {
            if(visited[cnt]==0) {
                visited[cnt] = i;
                allCase(cnt+1, visited);
                visited[cnt] = 0;
            }
        }
    }

    static public boolean checkPosition(int idx, CHESS chessArr[]) {
        for(int i=1; i<=4; i++) {
            if(i==idx) continue;
            if(chessArr[i].mapidx == chessArr[idx].mapidx && chessArr[i].position == chessArr[idx].position) {
                return false;
            }
        }
        return true;
    }

    static public void moveChess(int visited[]) {
        int score = 0;
        boolean flag = false;

        CHESS chessArr[] = new CHESS[5];
        for(int i=1; i<=4; i++) {
            chessArr[i] = new CHESS();
        }

        loop:
        for(int i=0; i<10; i++) {
            CHESS chess = chessArr[visited[i]];
            int mapidx = chess.mapidx;
            int position = chess.position;
            flag = false;
            if(map[mapidx][position] == 41) continue;

            for(int j=0; j<dice[i]; j++) {
                position++;
                if(map[mapidx][position] == 25) {
                    mapidx = 4;
                    position = 0;
                } else if(map[mapidx][position] == 40) {
                    mapidx = 0;
                    position = 20;
                } else if(map[mapidx][position] == 41) {
                    flag = true;
                    break;
                }
            }

            if(!flag) {
                if(map[mapidx][position] == 10) {
                    mapidx = 1;
                    position = 0;
                } else if(map[mapidx][position] == 20) {
                    mapidx = 2;
                    position = 0;
                } else if(mapidx==0 && map[mapidx][position] == 30) {
                    mapidx = 3;
                    position = 0;
                }
            }
            
            chessArr[visited[i]].mapidx = mapidx;
            chessArr[visited[i]].position = position;
            if(map[mapidx][position] == 41) continue;

            score += map[mapidx][position];
            if(!checkPosition(visited[i], chessArr)) return;
        }

        resScore = Math.max(resScore, score);
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[10];
        int visited[] = new int[10];

        for(int i=0; i<10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        allCase(0, visited);
        System.out.println(resScore);
    }
}