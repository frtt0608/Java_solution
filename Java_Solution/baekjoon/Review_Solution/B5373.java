import java.util.*;
import java.io.*;

/*         U
           0 1 2
           3 4 5
           6 7 8
L         -------- R         B
36 37 38 |18 19 20|45 46 47|27 28 29 
39 40 41 |21 22 23|48 49 50|30 31 32
42 43 44 |24 25 26|51 52 53|33 34 35
          --------
          9 10 11
          12 13 14
          15 16 17
          D
*/

/**
 * Main
 */
public class B5373 {
    static int tc, n;
    static String CUBEarr[];
    static int CUBE[][][];
    static int sideCube[][] = {{18,19,20,45,46,47,27,28,29,36,37,38},  // U
                                {24,25,26,42,43,44,33,34,35,51,52,53}, // D
                                {6,7,8,44,41,38,11,10,9,45,48,51},     // F
                                {2,1,0,53,50,47,15,16,17,36,39,42},    // B
                                {0,3,6,35,32,29,9,12,15,18,21,24},     // L
                                {8,5,2,26,23,20,17,14,11,27,30,33}};   // R

    static void rotateCube(int idx, String clockDir) {
        int rotateCnt = 1;
        if(clockDir.equals("-")) rotateCnt=3;
        String temp_side[] = new String[12];
        String temp_CUBE[][] = new String[3][3];
        while(rotateCnt>0) {
            for(int i=0; i<12; i++) {
                temp_side[i] = CUBEarr[sideCube[idx][i]];
            }
            for(int i=0; i<12; i++) {
                CUBEarr[sideCube[idx][i]] = temp_side[(i+3)%12];
            }
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    temp_CUBE[j][2-i] = CUBEarr[CUBE[idx][i][j]];
                }
            }
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    CUBEarr[CUBE[idx][i][j]] = temp_CUBE[i][j];
                }
            }
            
            rotateCnt--;
        }
    }

    static void initCube() {
        String cubeColor[] = {"w", "y", "r", "o", "g", "b"};
        for(int i=0; i<6; i++) {
            for(int j=0; j<9; j++) {
                CUBEarr[i*9+j] = cubeColor[i];
            }
        }

        for(int i=0; i<6; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    CUBE[i][j][k] = i*9 + j*3 + k;
                }
            }
        }
    }

    static void printCubeTop() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                System.out.print(CUBEarr[CUBE[0][i][j]]);
            }
            System.out.println();
        }
    }
    
    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        String dirCube = "UDFBLR";
        CUBEarr = new String[54];
        CUBE = new int[6][3][3];

        tc=sc.nextInt();
        for(int i=0; i<tc; i++) {
            n=sc.nextInt();
            initCube();
            for(int j=0; j<n; j++) {
                String input_string[] = sc.next().split("");
                rotateCube(dirCube.indexOf(input_string[0]), input_string[1]);
            }
            printCubeTop();
        }
    }
}