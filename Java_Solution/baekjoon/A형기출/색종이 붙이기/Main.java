import java.util.*;
import java.io.*;

// 1x1 ~ 5x5까지 각 5장
// 완전탐색
// 1부터 5까지 붙일 수 있으면 붙이기!

/**
 * Main
 */
public class Main {
    static int N, map[][], squares[];
    static int minRes = Integer.MAX_VALUE;

    static public void attachPaper(int idx, int squareCnt) {
        if(idx==100) {
            minRes = Math.min(minRes, squareCnt);
            return;
        }
        if(minRes <= squareCnt) return;

        int x = idx/10;
        int y = idx%10;
        if(map[x][y]==1) {
            for(int size=5; size>0; size--) {
                // checkPaper
                if(checkPaper(x, y, size)) {
                    if(squares[size] < 5) {
                        // fill
                        squares[size] += 1;
                        fillMap(x, y, size, 0);
                        // backTracking
                        attachPaper(idx+1, squareCnt+1);
                        // unfill
                        squares[size] -= 1;
                        fillMap(x, y, size, 1);
                    } 
                }
            }
        } else {
            attachPaper(idx+1, squareCnt);
        }
    }

    static public boolean checkPaper(int x, int y, int size) {
        if(x+size > 10 || y+size > 10) return false;
        
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(map[x+i][y+j] != 1) return false;
            }
        }

        return true;
    }

    static public void fillMap(int x, int y, int size, int tape) {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                map[x+i][y+j] = tape;
            }
        }
    }


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 10;
        map = new int[N][N];
        squares = new int[6];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        attachPaper(0, 0);
        System.out.println(minRes==Integer.MAX_VALUE ? -1:minRes);
    }
}