import java.io.*;
import java.util.*;

public class B20061 {
    static int getScore;
    static boolean[][] green, blue;
    
    public static void loadedGreenBlock(int t, int x, int y) {
        x = 0;

        if(t == 1) {
            while(x+1 < 6 && !green[x+1][y]) {
                x += 1;
            }

            green[x][y] = true;

        } else if(t == 2) {
            while(x+1 < 6 && !green[x+1][y] && !green[x+1][y+1]) {
                x += 1;
            }

            green[x][y] = true;
            green[x][y+1] = true;

        } else {
            while(x+1 < 6 && !green[x+1][y]) {
                x += 1;
            }

            green[x-1][y] = true;
            green[x][y] = true;
        }
    }

    public static void loadedBlueBlock(int t, int x, int y) {
        y = 0;

        if(t == 1) {
            while(y+1 < 6 && !blue[x][y+1]) {
                y += 1;
            }

            blue[x][y] = true;

        } else if(t == 2) {
            while(y+1 < 6 && !blue[x][y+1]) {
                y += 1;
            }

            blue[x][y] = true;
            blue[x][y-1] = true;

        } else {
            while(y+1 < 6 && !blue[x][y+1] && !blue[x+1][y+1]) {
                y += 1;
            }

            blue[x][y] = true;
            blue[x+1][y] = true;
        }
    }

    public static void downBlockArr(char type, int start) {
        if(type == 'g') {
            for(int i=start-1; i>=0; i--) {
                for(int j=0; j<4; j++) {
                    green[i+1][j] = green[i][j];
                    green[i][j] = false;
                }
            }
        } else {

            for(int i=start-1; i>=0; i--) {
                for(int j=0; j<4; j++) {
                    blue[j][i+1] = blue[j][i];
                    blue[j][i] = false;
                }
            }
        }
    }

    public static void checkRowAndColumn() {
        for(int i=5; i>=0; i--) {
            if(i<6 && green[i][0] && green[i][1] && green[i][2] && green[i][3]) {
                getScore += 1;
                Arrays.fill(green[i], false);
                downBlockArr('g', i);
                i += 1;
            }
        }

        for(int j=5; j>=0; j--) {
            if(j<6 && blue[0][j] && blue[1][j] && blue[2][j] && blue[3][j]) {
                getScore += 1;
                for(int i=0; i<4; i++) {
                    blue[i][j] = false;
                }

                downBlockArr('b', j);
                j+=1;
            }
        }
    }

    public static void checkSideBlock() {
        while(green[1][0] || green[1][1] || green[1][2] || green[1][3]) {
            Arrays.fill(green[5], false);
            downBlockArr('g', 5);
        }

        while(blue[0][1] || blue[1][1] || blue[2][1] || blue[3][1]) {
            for(int i=0; i<4; i++) {
                blue[i][5] = false;
            }
            downBlockArr('b', 5);
        }
    }

    public static int countRemainBlocks() {
        int remainCount = 0;

        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                if(green[i][j]) remainCount += 1;
                if(blue[j][i]) remainCount += 1;
            }
        }

        return remainCount;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        green = new boolean[6][4];
        blue = new boolean[4][6];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            loadedGreenBlock(t, x, y);
            loadedBlueBlock(t, x, y);
            checkRowAndColumn();
            checkSideBlock();
        }

        System.out.println(getScore);
        System.out.println(countRemainBlocks());
    }
}
