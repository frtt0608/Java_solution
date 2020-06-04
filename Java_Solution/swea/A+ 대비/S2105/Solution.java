import java.util.*;
import java.io.*;

/**
 * Solution
 */
public class Solution {
    static int N, maxCnt;
    static Set<Integer> numSet;
    static int[][] map;

    static boolean wall(int x, int y) {
        if(x<0 || x<=N || y<0 || y>=N) return true;
        return false;
    }

    static void divideRoute() {
        numSet = new HashSet<>();
        for(int i=0; i<N-2; i++) {
            for(int j=1; j<N-1; j++) {
                for(int d1=N-2; d1>=1; d1--) {
                    if(j-d1 < 0 || i+d1 >= N) continue;
                    for(int d2=N-2; d2>=1; d2--) {
                        if(j+d2 >= N || i+d1+d2 >= N || j+d2-d1 >= N) continue;
                        if(goCafe(i, j, d1, d2)) {
                            maxCnt = Math.max(maxCnt, numSet.size());
                        }
                        numSet.clear();
                    }
                }
            }
        }
    }

    static boolean goCafe(int i, int j, int D1, int D2) {
        int temp_D1=1;
        int temp_D2=1;
        // 오른쪽 아래로
        while(true) {
            if(numSet.contains(map[i+temp_D2][j+temp_D2])) return false;
            numSet.add(map[i+temp_D2][j+temp_D2]);
            if(temp_D2 >= D2) break;

            if(temp_D2 < D2) temp_D2++;
        }

        // 왼쪽 아래로
        while(true) {
            if(numSet.contains(map[i+D2+temp_D1][j+D2-temp_D1])) return false;
            numSet.add(map[i+D2+temp_D1][j+D2-temp_D1]);
            if(temp_D1 >= D1) break;

            if(temp_D1 < D1) temp_D1++;
        }

        temp_D2 = 1;
        // 왼쪽 위로
        while(true) {
            if(numSet.contains(map[i+D1+D2-temp_D2][j+D2-D1-temp_D2])) return false;
            numSet.add(map[i+D1+D2-temp_D2][j+D2-D1-temp_D2]);
            if(temp_D2 >= D2) break;

            if(temp_D2 < D2) temp_D2++;
        }

        // 오른쪽 위로
        temp_D1 = 1;
        while(true) {
            if(numSet.contains(map[i+D1-temp_D1][j-D1+temp_D1])) return false;
            numSet.add(map[i+D1-temp_D1][j-D1+temp_D1]);
            if(temp_D1 >= D1) break;

            if(temp_D1 < D1) temp_D1++;
        }

        return true;
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxCnt = 0;
            divideRoute();
            if(maxCnt==0) maxCnt = -1;
            System.out.println("#"+tc+" "+maxCnt);
        }
    }
}