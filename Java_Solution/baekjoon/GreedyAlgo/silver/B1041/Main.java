// 주사위
//   D
// E A B F
//   C

// 입력: A B C D E F

import java.util.*;
import java.io.*;

public class Main {
    static long N, powN, innerN, minNum;
    static int[] dice;
    static int[][] idx = {{1,2},
                        {1,3},
                        {2,4},
                        {3,4}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        powN = (long)Math.pow(N, 2);
        innerN = (long)Math.pow(N-2, 2);
        dice = new int[6];
        minNum = Long.MAX_VALUE;
        
        String input[] = br.readLine().split(" ");
        for(int i=0; i<6; i++) {
            dice[i] = Integer.parseInt(input[i]);
        }
        
        if(N==1) {
            Arrays.sort(dice);
            minNum = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
        } else {
            // 1면
            Long minDice1 = Long.MAX_VALUE;
            Long minDice2 = Long.MAX_VALUE;
            Long minDice3 = Long.MAX_VALUE;

            for(int i=0; i<6; i++) {
                minDice1 = Math.min(minDice1, dice[i]);
            }

            // 2면
            for(int i=0; i<6; i++) {
                for(int j=0; j<6; j++) {
                    if(i==j || i+j == 5) continue;

                    minDice2 = Math.min(minDice2, dice[i] + dice[j]);
                }
            }

            // 3면 ㄱ자 형태
            int[] top_bottom = {0, 5};
            for(int i : top_bottom) {
                for(int j=0; j<4; j++) {
                    minDice3 = Math.min(minDice3, dice[i] + dice[idx[j][0]] + dice[idx[j][1]]);
                }
            }

            minNum = minDice3*4 + minDice2*((N-1) + (N-2))*4 + minDice1*((long)Math.pow(N-2,2) + (N-2)*(N-1)*4);
        }

        System.out.println(minNum);
    }
}