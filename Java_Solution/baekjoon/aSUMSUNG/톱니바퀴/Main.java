// 1번이 시계방향 회전시, 맞닿은 극이 다르면 2번은 반대로(반시계방향) 회전!
// 이런식으로 1, 2, 3, 4번 톱니바퀴가 회전!
// 1번 12시방향 N극이면 0점 S극이면 1점
// 2번 12시방향 N극이면 0점 S극이면 2점
// 3번 12시방향 N극이면 0점 S극이면 4점
// 4번 12시방향 N극이면 0점 S극이면 8점

// N극 : 0 // S극 : 1
// 1은 시계방향, -1은 반시계방향

import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int Gear[][], K, spin[][], score;

    static void Gear_Rotate(int num, int dir) {
        // 시계방향
        if(dir==1) {
            for(int i=8; i>0; i--) {
                Gear[num][i+1] = Gear[num][i];
            }
            Gear[num][1] = Gear[num][9];
        }

        // 반시계방향
        if(dir==-1) {
            for(int i=1; i<=8; i++) {
                Gear[num][i-1] = Gear[num][i];
            }
            Gear[num][8] = Gear[num][0];
        }
    }

    static void Check_Rotate(int num, int dir) {
        int rotateList[] = new int[5];
        rotateList[num] = dir;
        for(int i=num; i<4; i++) {
            if(Gear[i][3]!=Gear[i+1][7]) {
                if(rotateList[i]==0) rotateList[i+1] = 0;
                rotateList[i+1] = -rotateList[i];
            } else {
                rotateList[i+1] = 0;
            }
        }

        for(int i=num; i>1; i--) {
            if(Gear[i][7]!=Gear[i-1][3]) {
                if(rotateList[i]==0) rotateList[i-1] = 0;
                else rotateList[i-1] = -rotateList[i];
            } else {
                rotateList[i-1] = 0;
            }
        }
        System.out.println(Arrays.toString(rotateList));
        for(int i=1; i<5; i++) Gear_Rotate(i, rotateList[i]);
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        score = 0;
        Gear = new int[5][10];
        String temp[] = new String[8];
        for(int i=1; i<5; i++) {
            temp = sc.next().split("");
            for(int j=1; j<9; j++) {
                Gear[i][j] = Integer.parseInt(temp[j-1]);
                System.out.print(Gear[i][j]);
            }
            System.out.println();
        }
        K = sc.nextInt();
        spin = new int[K][2];
        for(int i=0; i<K; i++) {
           for(int j=0; j<2; j++) {
               spin[i][j] = sc.nextInt();
           }
        }

        for(int i=0; i<K; i++) 
           Check_Rotate(spin[i][0], spin[i][1]);
        
        if(Gear[1][1]==1) score+=1;
        if(Gear[2][1]==1) score+=2;
        if(Gear[3][1]==1) score+=4;
        if(Gear[4][1]==1) score+=8;

        System.out.println(score);
    }
}