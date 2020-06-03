import java.util.*;
import java.io.*;

/**
 * Solution
 */
public class Solution {
    static int D, W, K, minRes;
    static int[][] disk; // A:0 , B:1

    static boolean inspectPerform(int j) {
        int cnt = 1;
        int pre_disk = disk[0][j];

        for(int i=1; i<D; i++) {
            if(pre_disk == disk[i][j]) {
                cnt += 1;
                if(cnt >= K) return true;
            } else {
                cnt = 1;
                pre_disk = disk[i][j];
            }
        }

        return false;
    }

    static void checkDisk(int cnt, int idx, boolean visited[]) {
        boolean checkFlag = true;
        for(int j=0; j<W; j++) {
            if(!inspectPerform(j)) {
                checkFlag = false;
                break;
            }
        }

        if(checkFlag) {
            minRes = Math.min(minRes ,cnt);
            return;
        } else {
            for(int i=idx; i<D; i++) {
                if(visited[i]) continue;
                int temp[] = Arrays.copyOf(disk[i], W);
                visited[i] = true;
                
                Arrays.fill(disk[i], 1);
                checkDisk(cnt+1, i+1, visited);

                Arrays.fill(disk[i], 0);
                checkDisk(cnt+1, i+1, visited);

                visited[i] = false;
                disk[i] = Arrays.copyOf(temp, W);
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            disk = new int[D][W];
            boolean[] visited = new boolean[D];
            for(int i=0; i<D; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    disk[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minRes = Integer.MAX_VALUE;
            if(K==1) minRes = 0;
            else checkDisk(0, 0, visited);

            System.out.println("#"+tc+" "+minRes);
        }
    }
}
