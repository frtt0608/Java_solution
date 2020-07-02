// 롤러코스터

import java.io.*;


public class Main {
    static int R, C;
    static int[][] map;
    static StringBuilder sb;

    static public String R_isOdd() {
        for(int i=0; i<R; i++) {
    
            for(int j=1; j<C; j++) {
                if(i%2==0)
                    sb.append("R");
                else
                    sb.append("L");
            }
            if(i<R-1)
                sb.append("D");
        }

        return sb.toString();
    }
    
    static public String C_isOdd() {
        for(int i=0; i<C; i++) {
    
            for(int j=1; j<R; j++) {
                if(i%2==0)
                    sb.append("D");
                else
                    sb.append("U");
            }
            if(i<C-1)
                sb.append("R");
        }

        return sb.toString();
    }

    static public String RC_isEven() {
        StringBuilder rev_sb = new StringBuilder();
        int minX = 0;
        int minY = 0;
        int minNum = 100000;

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if((i+j)%2==1) {
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

        // 시작점에서 출발해 최솟값의 x인덱스인 minX까지
        while(sx != minX) {
            if(sx%2==0) {
                for(int i=1; i<C; i++) {
                    sb.append("R");
                }
                sb.append("D");
                sy = C-1;
            } else {
                for(int i=1; i<C; i++) {
                    sb.append("L");
                }
                sb.append("D");
                sy = 0;
            }
            sx += 1;
        }

        // 도착점에서 출발해 minX와 1차이까지
        while(ex - minX > 1) {
            if(ex%2==1) {
                for(int i=1; i<C; i++) {
                    rev_sb.append("R");
                }
                rev_sb.append("D");
                ey = 0;
            } else {
                for(int i=1; i<C; i++) {
                    rev_sb.append("L");
                }
                rev_sb.append("D");
                ey = C-1;
            }
            ex -= 1;
        }

        // sy와 ey위치에 따라 다르게 움직인다.
        if(sy < ey) {
            while(sy+1 != minY) {
                sb.append("D");
                sb.append("R");
                sb.append("U");
                sb.append("R");
                sy += 2;
            }
    
            while(ey != minY) {
                rev_sb.append("D");
                rev_sb.append("R");
                rev_sb.append("U");
                rev_sb.append("R");
                ey -= 2;
            }
        } else {
            while(sy-1 != minY) {
                sb.append("D");
                sb.append("L");
                sb.append("U");
                sb.append("L");
                sy -= 2;
            }
    
            while(ey != minY) {
                rev_sb.append("D");
                rev_sb.append("L");
                rev_sb.append("U");
                rev_sb.append("L");
                ey += 2;
            }
        }

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

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new int[R][C];
        for(int i=0; i<R; i++) {
            input = br.readLine().split(" ");
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        String route = "";
        sb = new StringBuilder();
        if(R%2==1) {
            route = R_isOdd();
        } else if(C%2==1) {
            route = C_isOdd();
        } else {
            route = RC_isEven();
        }
        
        System.out.println(route);
    }
}