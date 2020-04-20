// 각 면 색 동일해야 함!
// 윗면 흰색, 아랫면 노란색, 옆면 빨간색, 뒷면 주황색, 왼쪽 초록색, 오른쪽 파란색
// U, D, F, B, L, R
// +는 시계방향, -는 반시계방향

/*            U
          0  1  2
          3  4  5
          6  7  8
L        __________    R            B
36 37 38|F18 19 20 | 45 46 47 | 27 28 29
39 40 41| 21 22 23 | 48 49 50 | 30 31 32
42 43 44| 24 25 26 | 51 52 53 | 33 34 35
        -----------
          9  10 11
          12 13 14
          15 16 17
          D
*/ 

import java.util.*;
import java.io.*;

public class Main {
    static int tc, cube[][][];
    static String cubeColorArr[];
    static int rotateIdx[][] = {
        {36,37,38,18,19,20,45,46,47,27,28,29},        //U
        {33,34,35,51,52,53,24,25,26,42,43,44},        //D
        {6,7,8,44,41,38,11,10,9,45,48,51},            //F
        {2,1,0,53,50,47,15,16,17,36,39,42},           //B
        {0,3,6,35,32,29,9,12,15,18,21,24},            //L
        {8,5,2,26,23,20,17,14,11,27,30,33}            //R
    };

    static void rotateCUBE(int idx, String cubeSide, String rotateDir) {
        int cnt = 1;
        if(rotateDir.equals("-")) cnt=3;

        String temp[] = new String[12];
        String temp_side[][] = new String[3][3];

        while(cnt>0) {
            cnt--;
            for(int i=0; i<12; i++) {
                temp[i] = cubeColorArr[rotateIdx[idx][i]];
            }
            //System.out.println(Arrays.toString(temp));
            for(int i=0; i<12; i++) {
                cubeColorArr[rotateIdx[idx][i]] = temp[(i+3)%12];
            }
            
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    temp_side[j][2-i] = cubeColorArr[cube[idx][i][j]];
                }
            }

            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    cubeColorArr[cube[idx][i][j]] = temp_side[i][j];
                }
            }
        }
    }

    static void printCubeTop() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                System.out.print(cubeColorArr[cube[0][i][j]]);
            }
            System.out.println();
        }
    }

    static void initCube() {
        String init[] = {"w", "y", "r", "o", "g", "b"};
        for(int i=0; i<6; i++) {
            for(int j=0; j<9; j++) {
                cubeColorArr[i*9+j] = init[i];
            }
        }
        //System.out.println(Arrays.toString(cubeColorArr));
        
        for(int i=0; i<6; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    cube[i][j][k] = i*9 + j*3 + k;
                }
            }
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        tc=sc.nextInt();
        String dirIdx = "UDFBLR";
        cubeColorArr = new String[54];
        cube = new int[6][3][3];
        

        for(int i=0; i<tc; i++) {
            int n = sc.nextInt();
            initCube();
            for(int j=0; j<n; j++) {
                String rotateOrder[] = sc.next().split("");
                rotateCUBE(dirIdx.indexOf(rotateOrder[0]), rotateOrder[0], rotateOrder[1]);
            }
            printCubeTop();
        }
    }
}
