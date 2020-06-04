import java.util.*;
import java.io.*;

/**
 * Solution
 */
public class Solution {
    static int N, M, C, maxRes;
    static int[][] map;

    static void choiceHoeny(int cnt, int idx, int res, boolean[][] visited) {
        if(cnt==2) {
            maxRes = Math.max(maxRes, res);
            return;
        }

        for(int i=idx; i<N; i++) {
            int honeyCnt = 0;
            LinkedList<Integer> honeyList = new LinkedList<>();

            for(int j=0; j<N; j++) {
                if(visited[i][j]) continue;
                visited[i][j] = true;
                honeyCnt++;
                honeyList.add(map[i][j]);
                if(honeyCnt==M) {

                }
            }
        }
    }

    static void checkHoney(int i, int j) {
        
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxRes = 0;


        }
    }
}