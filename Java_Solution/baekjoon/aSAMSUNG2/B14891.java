import java.util.*;
import java.io.*;

public class B14891 {
    static int K;
    static int[][] cycle;

    public static void rotateCycle(int[] dirs) {
        int temp = 0;
        for(int i=0; i<4; i++) {
            if(dirs[i] == 1) {
                temp = cycle[i][7];
                for(int j=7; j>0; j--) {
                    cycle[i][j] = cycle[i][j-1];
                }
                cycle[i][0] = temp;

            } else if(dirs[i] == -1) {
                temp = cycle[i][0];
                for(int j=0; j<7; j++) {
                    cycle[i][j] = cycle[i][j+1];
                }
                cycle[i][7] = temp;
            }
        }
    }

    public static void startRotateCycle(int num, int dir) {
        int[] dirs = new int[4];
        dirs[num] = dir;

        for(int i=num+1; i<4; i++) {
            if(cycle[i-1][2] != cycle[i][6]) {
                dirs[i] = -dirs[i-1];
            } else {
                break;
            }
        }

        for(int i=num-1; i>=0; i--) {
            if(cycle[i+1][6] != cycle[i][2]) {
                dirs[i] = -dirs[i+1];
            } else {
                break;
            }
        }

        rotateCycle(dirs);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int totalScore = 0;
        cycle = new int[4][8];

        for(int i=0; i<4; i++) {
           String[] input = br.readLine().split("");
           
           for(int j=0; j<8; j++) {
               cycle[i][j] = Integer.parseInt(input[j]);
           }
        }
        
        
        K = Integer.parseInt(br.readLine());

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());

            // 1인 경우 시계방향
            startRotateCycle(num, dir);
        }

        for(int i=0; i<4; i++) {

            if(cycle[i][0] == 1) {
                switch(i) {
                    case 0:
                        totalScore += 1;
                        break;
                    case 1:
                        totalScore += 2;
                        break;
                    case 2:
                        totalScore += 4;
                        break;
                    case 3:
                        totalScore += 8;
                        break;
                }
            }
        }

        System.out.println(totalScore);
    }
}