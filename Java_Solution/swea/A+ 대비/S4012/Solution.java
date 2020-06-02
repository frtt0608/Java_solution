import java.util.*;
import java.io.*;

/**
 * Solution
 */
public class Solution {
    static int N, minRes;
    static int[][] map;

    static void makeSynergy(int cnt, int idx, boolean[] visited) {
        if(cnt==N/2) {
            int cook1 = 0;
            int cook2 = 0;
            for(int i=1; i<N; i++) {
                for(int j=i+1; j<=N; j++) {
                    if(visited[i] && visited[j]) {
                        cook1 += map[i][j] + map[j][i];
                    } else if(!visited[i] && !visited[j]) {
                        cook2 += map[i][j] + map[j][i];
                    }
                }
            }

            minRes = Math.min(minRes, Math.abs(cook1- cook2));
            return;
        }

        for(int i=idx; i<=N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            makeSynergy(cnt+1, i+1, visited);
            visited[i] = false;
        }
    }
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[];
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N+1][N+1];
            boolean[] visited = new boolean[N+1];
            
            for(int i=1; i<=N; i++) {
                input = br.readLine().split(" ");
                for(int j=1; j<=N; j++) {
                    map[i][j] = Integer.parseInt(input[j-1]);
                }
            }

            minRes = Integer.MAX_VALUE;

            makeSynergy(0, 1, visited);
            System.out.println("#"+tc+" "+minRes);
        }
    }
}