import java.util.*;
import java.io.*;

public class B20061 {
    static int N, totalScore, totalBlock;
    static int nx, ny;
    static int[][] blue, green;

    // .
    public static void moveToBlock1(int x, int y) {
        nx = 0;
        while(true) {
            if(nx+1 <= 5 && green[nx+1][y] == 0) {
                nx += 1;
            } else
                break;
        }
        green[nx][y] = 1;

        ny = 0;
        while(true) {
            if(ny+1 <= 5 && blue[x][ny+1] == 0) {
                ny += 1;
            } else
                break;
        }
        blue[x][ny] = 1;
    }

    // ㅡ
    public static void moveToBlock2(int x, int y) {
        nx = 0;        
        while(true) {
            if(nx+1 <= 5 && green[nx+1][y] == 0 && green[nx+1][y+1] == 0) {
                nx += 1;
            } else
                break;
        }
        green[nx][y] = 1;
        green[nx][y+1] = 1;

        ny = 1;
        while(true) {
            if(ny+1 <= 5 && blue[x][ny+1] == 0) {
                ny += 1;
            } else
                break;
        }
        blue[x][ny] = 1;
        blue[x][ny-1] = 1;
    }

    // ㅣ
    public static void moveToBlock3(int x, int y) {
        nx = 1;
        while(true) {
            if(nx+1 <= 5 && green[nx+1][y] == 0) {
                nx += 1;
            } else
                break;
        }
        green[nx][y] = 1;
        green[nx-1][y] = 1;

        ny = 0;
        while(true) {
            if(ny+1 <= 5 && blue[x][ny+1] == 0 && blue[x+1][ny+1] == 0) {
                ny += 1;
            } else
                break;
        }
        blue[x][ny] = 1;
        blue[x+1][ny] = 1;
    }

    public static void locatedBlock(int t, int x, int y) {
        switch(t) {
            case 1:
                moveToBlock1(x, y);
                break;
            case 2:
                moveToBlock2(x, y);
                break;
            case 3:
                moveToBlock3(x, y);
        }
    }

    public static void downBlock(int type, int si) {
        if(type == 0) {
            for(int i=si; i>0; i--) {
                for(int j=0; j<4; j++) {
                    green[i][j] = green[i-1][j];
                    green[i-1][j] = 0;
                }
            }

        } else {
            for(int i=si; i>0; i--) {
                for(int j=0; j<4; j++) {
                    blue[j][i] = blue[j][i-1];
                    blue[j][i-1] = 0;
                }
            }
        }
    }

    public static void getScoreBlock() {
        boolean flag;

        for(int i=5; i>=0; i--) {
            flag = true;
            for(int j=0; j<4; j++) {
                if(green[i][j] == 0) {
                    flag = false;   
                    break;
                }
            }

            if(flag) {
                totalScore += 1;
                for(int j=0; j<4; j++) {
                    green[i][j] = 0;
                }
                downBlock(0, i);
                i += 1;
            }
        }

        for(int i=5; i>=0; i--) {
            flag = true;
            for(int j=0; j<4; j++) {
                if(blue[j][i] == 0) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                totalScore += 1;
                for(int j=0; j<4; j++) {
                    blue[j][i] = 0;
                }
                downBlock(1, i);
                i += 1;
            }
        }
    }

    public static void removeBlock() {
        while(true) {
            if(green[1][0]==1 || green[1][1]==1 ||
                green[1][2]==1 || green[1][3]==1) {
                for(int j=0; j<4; j++) {
                    green[5][j] = 0;
                }

                downBlock(0, 5);
            } else
                break;
        }

        while(true) {
            if(blue[0][1]==1 || blue[1][1]==1 ||
                blue[2][1]==1 || blue[3][1]==1) {
                for(int j=0; j<4; j++) {
                    blue[j][5] = 0;
                }

                downBlock(1, 5);
            } else
                break;
        }
    }

    public static void getTotalBlock() {
        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                if(green[i][j] == 1) {
                    totalBlock += 1;
                }

                if(blue[j][i] == 1) {
                    totalBlock += 1;
                }
            }
        }
    }

    public static void print() {
        System.out.println("GREEN: ");
        for(int i=0; i<6; i++) {
            System.out.println(Arrays.toString(green[i]));
        }
        
        System.out.println("BLUE: ");
        for(int i=0; i<4; i++) {
            System.out.println(Arrays.toString(blue[i]));
        }
        System.out.println("---------------");
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        blue = new int[4][6];
        green = new int[6][4];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            locatedBlock(t, x, y);
            // print();
            getScoreBlock();
            removeBlock();
        }

        getTotalBlock();
        System.out.println(totalScore + "\n" + totalBlock);
    }
}