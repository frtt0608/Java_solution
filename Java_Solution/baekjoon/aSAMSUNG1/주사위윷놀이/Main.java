import java.util.*;
import java.io.*;

class CHESS {
    int mapIdx;
    int position;
    int num;

    CHESS(int mapIdx, int position, int num) {
        this.mapIdx = mapIdx;
        this.position = position;
        this.num = num;
    }
}
/**
 * Main
 */
public class Main {
    static int totalScore, dice[];
    static int map[][] = {{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,41},
                        {10,13,16,19,25},
                        {20,22,24,25},
                        {30,28,27,26,25},
                        {25,30,35,40}};
                            

    static void playDice(CHESS chess[], int idx, int score) {
        if(idx == dice.length) {
            // System.out.println(score);
            totalScore = Math.max(totalScore, score);
            return;
        }
        
        for(int i=0; i<4; i++) {
            if(chess[i].num == 41) continue;
            int mapidx = chess[i].mapIdx;
            int position = chess[i].position;
            int num = chess[i].num;

            int temp = dice[idx];
            boolean flag = false;
            while(temp>0) {
                temp--;
                chess[i].position++;
                chess[i].num = map[chess[i].mapIdx][chess[i].position];

                if(chess[i].num == 25) {
                    chess[i].mapIdx = 4;
                    chess[i].position = 0;
                } else if(chess[i].num == 40) {
                    chess[i].mapIdx = 0;
                    chess[i].position = 20;
                } else if(chess[i].num == 41) {
                    flag = true;
                    playDice(chess, idx+1, score);
                    break;
                }
            }

            if(!flag) {
                if(chess[i].num==10) {
                    chess[i].mapIdx = 1;
                    chess[i].position = 0;
                } else if(chess[i].num==20) {
                    chess[i].mapIdx = 2;
                    chess[i].position = 0;
                } else if(chess[i].num==30 && chess[i].mapIdx==0) {
                    chess[i].mapIdx = 3;
                    chess[i].position = 0;
                }
                if(checkDice(i, chess)) {
                    playDice(chess, idx+1, score + chess[i].num);
                }
            }

            chess[i].mapIdx = mapidx;
            chess[i].position = position;
            chess[i].num = num;
        }
    } // DFS

    static boolean checkDice(int idx, CHESS chess[]) {

        for(int i=0; i<4; i++) {
            if(idx==i) continue;
            if(chess[idx].position == chess[i].position && chess[idx].mapIdx == chess[i].mapIdx) {
                return false;
            }
        }
 
        return true;
    } // 같은 위치 확인

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[10];
        CHESS chess[] = new CHESS[4];

        for(int i=0; i<dice.length; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<4; i++) {
            chess[i] = new CHESS(0, 0, 0);
        }

        playDice(chess, 0, 0);
        System.out.println(totalScore);
    }
}