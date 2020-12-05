import java.util.*;
import java.io.*;

// t=1 .
// t=2 ㅡ
// t=3 ㅣ

// 1. 적재
// 2. 행 탐색
// 3. 0, 1 탐색

// 1,3 -> green, y고정
// 1,2 -> blue, x고정

public class Main {
    static int score, totalBlock;
    static boolean[][] green, blue;

    public static void downBlock(int idx, int color) { 
        // 0:green, 1:blue
        if(color == 0) {
            while(idx > 0) {
                for(int j=0; j<4; j++) {
                    green[idx][j] = green[idx-1][j];
                    green[idx-1][j] = false;
                }
                idx -= 1;
            }
        } else {
            while(idx > 0) {
                for(int i=0; i<4; i++) {
                    blue[i][idx] = blue[i][idx-1];
                    blue[i][idx-1] = false;
                }
                idx -= 1;
            }
        }
    }

    public static void checkArray() {
        // green
        while(true) {
            if(green[1][0] || green[1][1] || green[1][2] || green[1][3]) {
                Arrays.fill(green[5], false);
                downBlock(5, 0);
            } else {
                break;
            }
        }
        // blue
        while(true) {
            if(blue[0][1] || blue[1][1] || blue[2][1] || blue[3][1]) {
                for(int i=0; i<4; i++) {
                    blue[i][5] = false;
                }
                downBlock(5, 1);
            } else {
                break;
            }
        }
    }

    public static void deleteBlock() {
        int idx = 5;

        // green
        while(idx > 0) {
            if(green[idx][0] && green[idx][1] && green[idx][2] && green[idx][3]) {
                score += 1;
                Arrays.fill(green[idx], false);
                downBlock(idx, 0);
            } else {
                idx -= 1;
            }
        }

        // blue
        idx = 5;
        while(idx > 0) {
            if(blue[0][idx] && blue[1][idx] && blue[2][idx] && blue[3][idx]) {
                score += 1;
                for(int i=0; i<4; i++) {
                    blue[i][idx] = false;
                }
                downBlock(idx, 1);
            } else {
                idx -= 1;
            }
        }
    }

    public static void loadBlock(int t, int x, int y) {
        // green
        for(int i=0; i<7; i++) {

            if(t == 1) {
                if(i>=6 || green[i][y]) {
                    green[i-1][y] = true;
                    break;
                }
            } else if(t == 2) {
                if(i>=6 || green[i][y] || green[i][y+1]) {
                    green[i-1][y] = true;
                    green[i-1][y+1] = true;
                    break;
                }
            } else {
                if(i+1>=6 || green[i+1][y]) {
                    green[i][y] = true;
                    green[i-1][y] = true;
                    break;
                }
            }
            
        }

        // blue
        for(int j=0; j<7; j++) {

            if(t == 1) {
                if(j>=6 || blue[x][j]) {
                    blue[x][j-1] = true;
                    break;
                }
            } else if(t == 2) {
                if(j+1>=6 || blue[x][j+1]) {
                    blue[x][j] = true;
                    blue[x][j-1] = true;
                    break;
                }
            } else {
                if(j>=6 || blue[x][j] || blue[x+1][j]) {
                    blue[x][j-1] = true;
                    blue[x+1][j-1] = true;
                    break;
                }
            }
        }
    }

    public static void sumTotalBlock() {
        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                if(green[i][j]) {
                    totalBlock += 1;
                }
                if(blue[j][i]) {
                    totalBlock += 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        green = new boolean[6][4];
        blue = new boolean[4][6];
        score = 0;
        totalBlock = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            loadBlock(t, x, y);
            deleteBlock();
            checkArray();
        }

        sumTotalBlock();

        System.out.println(score);
        System.out.println(totalBlock);
    }
}