import java.util.*;
import java.io.*;

/**
 * Solution
 */
public class Solution {
    static int N, X, runwayCnt;
    static int[][] map;

    static void setRow() {
        boolean flag = false;
        
        for(int i=0; i<N; i++) {
            
            if(ROW(i)) runwayCnt++;
            if(COL(i)) runwayCnt++;
            // System.out.println(runwayCnt);
        }
    }

    static boolean COL(int j) {
        boolean flag[] = new boolean[N];
        int i = 0;

        while(i < N) {
            if(i >= N-1) return true;
            if(Math.abs(map[i+1][j] - map[i][j]) > 1) return false;

            if(map[i][j] == map[i+1][j]) i++;
            else if(map[i][j] - map[i+1][j] == 1) {
                for(int cnt=i+1; cnt<=i+X; cnt++) {
                    if(cnt>=N || map[i+1][j] != map[cnt][j] || flag[cnt]) return false;
                    flag[cnt] = true;
                }
                i += X;
            } else if(map[i][j] - map[i+1][j] == -1) {
                for(int cnt=i; cnt>i-X; cnt--) {
                    if(cnt<0 || map[i][j] != map[cnt][j] || flag[cnt]) return false;
                    flag[cnt] = true;
                }
                i += 1;
            }
        }

        return true;
    }

    static boolean ROW(int i) {
        boolean flag[] = new boolean[N];
        int j = 0;

        while(j < N) {
            if(j >= N-1) return true;
            if(Math.abs(map[i][j] - map[i][j+1]) > 1) return false;

            if(map[i][j] == map[i][j+1]) j++;
            else if(map[i][j] - map[i][j+1] == 1) {
                for(int cnt=j+1; cnt<=j+X; cnt++) {
                    if(cnt>=N || map[i][j+1] != map[i][cnt] || flag[cnt]) return false;
                    flag[cnt] = true;
                }
                j += X;
            } else if(map[i][j] - map[i][j+1] == -1) {
                for(int cnt=j; cnt>j-X; cnt--) {
                    if(cnt<0 || map[i][j] != map[i][cnt] || flag[cnt]) return false;
                    flag[cnt] = true;
                }
                j += 1;
            }
        }

        return true;
    }
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            
            runwayCnt = 0;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            setRow();

            System.out.println("#"+tc+" "+runwayCnt);
        }
    }
}