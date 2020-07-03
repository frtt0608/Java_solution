// 롤러코스터 review

import java.io.*;

public class Main {
    static int R, C;
    static int[][] map;
    static StringBuilder sb;

    static String R_isOdd() {
        for(int i=0; i<R; i++) {
            for(int j=1; j<C; j++) {
                if(i%2==0) sb.append("R");
                else sb.append("L");
            }

            if(i<R-1) sb.append("D");
        }

        return sb.toString();
    }

    static String C_isOdd() {
        for(int i=0; i<C; i++) {
            for(int j=1; j<R; j++) {
                if(i%2==0) sb.append("D");
                else sb.append("U");
            }

            if(i<C-1) sb.append("R");
        }

        return sb.toString();
    }

    static String RC_idEven() {
        StringBuilder rev_sb = new StringBuilder();

        int minNum = 1001;
        int minX = 0;
        int minY = 0;

        // 최솟값 찾기(지나가지 않을 위치 찾기)
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if((i+j)%2 == 1) {
                    if(minNum > map[i][j]) {
                        minNum = map[i][j];
                        minX = i;
                        minY = j;
                    }
                }
            }
        }

        int sx = 0;
        int sy = 0;
        int ex = R-1;
        int ey = C-1;

        // 시작점에서 출발
        while(sx != minX || Math.abs(sy-minY) != 1) {
            if(sx != minX) {
                if(sx%2==0) {
                    for(int i=1; i<C; i++) {
                        sb.append("R");
                    }
                    sy = C-1;
                } else {
                    for(int i=1; i<C; i++) {
                        sb.append("L");
                    }
                    sy = 0;
                }
                sb.append("D");
                sx += 1;
            } else {
                if(Math.abs(sy-minY) != 1) {
                    if(sy < minY) {
                        sb.append("D");
                        sb.append("R");
                        sb.append("U");
                        sb.append("R");
                        sy += 2;
                    } else {
                        sb.append("D");
                        sb.append("L");
                        sb.append("U");
                        sb.append("L");
                        sy -= 2;
                    }
                }
            }
        }

        // 도착점에서 출발
        while(Math.abs(ex-minX) != 1 || ey != minY) {
            if(Math.abs(ex-minX) != 1) {
                if(ex%2==1) {
                    for(int i=1; i<C; i++) {
                        rev_sb.append("R");
                    }
                    ey = 0;
                } else {
                    for(int i=1; i<C; i++) {
                        rev_sb.append("L");
                    }
                    ey = C-1;
                }
                rev_sb.append("D");
                ex -= 1;
            } else {
                if(ey != minY) {
                    if(ey < minY) {
                        rev_sb.append("D");
                        rev_sb.append("L");
                        rev_sb.append("U");
                        rev_sb.append("L");
                        ey += 2;
                    } else {
                        rev_sb.append("D");
                        rev_sb.append("R");
                        rev_sb.append("U");
                        rev_sb.append("R");
                        ey -= 2;
                    }
                }
            }
        }

        // 마무리 2x2형태
        if(sy < ey) {
            sb.append("D");
            sb.append("R");
        } else {
            sb.append("D");
            sb.append("L");
        }

        sb.append(rev_sb.reverse());

        return sb.toString();
    }    

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        sb = new StringBuilder();
        String route = "";

        map = new int[R][C];

        for(int i=0; i<R; i++) {
            input = br.readLine().split(" ");
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        if(R%2==1) {
            route = R_isOdd();
        } else if(C%2==1) {
            route = C_isOdd();
        } else {
            route = RC_idEven();
        }

        System.out.println(route);
    }
}