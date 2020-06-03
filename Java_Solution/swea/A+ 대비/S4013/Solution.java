import java.util.*;
import java.io.*;

/**
 * Solution
 */
public class Solution {
    static int N;
    static int[][] magnet, rotate;

    static void rotateDir(int num, int dir) {
        int tempMagnet[] = new int[8];

        if(dir==1) {
            for(int i=0; i<8; i++) {
                tempMagnet[i] = magnet[num][(7+i)%8];
            }
        } else {
            for(int i=0; i<8; i++) {
                tempMagnet[i] = magnet[num][(i+1)%8];
            }
        }

        magnet[num] = tempMagnet;
    }

    static void rotateMagnet() {
        int rotateFlag[] = new int[4];
        int magnetNum;
        int dir;

        for(int i=0; i<N; i++) {
            Arrays.fill(rotateFlag, 0);
            magnetNum = rotate[i][0];
            dir = rotate[i][1];
            rotateFlag[magnetNum] = dir;

            for(int num=magnetNum; num<4-1; num++) {
                if(magnet[num][2] != magnet[num+1][6]) {
                    rotateFlag[num+1] = -rotateFlag[num];
                } else break;
            }

            for(int num=magnetNum; num>=1; num--) {
                if(magnet[num][6] != magnet[num-1][2]) {
                    rotateFlag[num-1] = -rotateFlag[num];
                } else break;
            }

            for(int num=0; num<4; num++) {
                if(rotateFlag[num]==0) continue;
                rotateDir(num, rotateFlag[num]);
            }
            // System.out.println(Arrays.toString(magnet[0]));
            // System.out.println(Arrays.toString(magnet[1]));
            // System.out.println(Arrays.toString(magnet[2]));
            // System.out.println(Arrays.toString(magnet[3]));
            // System.out.println();
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            magnet = new int[4][8];
            rotate = new int[N][2];
            
            for(int i=0; i<4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<8; j++) {
                    magnet[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                rotate[i][0] = Integer.parseInt(st.nextToken())-1;
                rotate[i][1] = Integer.parseInt(st.nextToken());
            }
            
            rotateMagnet();
            
            // N극이 0, S극이 1
            int score = 0;
            int getScore=1;
            for(int num=0; num<4; num++) {
                if(magnet[num][0]==1) {
                    score += getScore;
                }
                getScore *= 2;
            }

            System.out.println("#"+tc+" "+score);
        }
    }
}