import java.util.*;
import java.io.*;

/**
 * Solution
 */
public class Solution {
    static int D, W, K, minRes;
    static int[][] disk, copy_disk; // A:0 , B:1
    static int cnt, pre_disk;

    static boolean inspectPerform() {
        for(int j=0; j<W; j++) {
            cnt = 1;
            pre_disk = disk[0][j];
            for(int i=1; i<D; i++) {
                if(pre_disk == disk[i][j]) {
                    cnt += 1;
                    if(cnt >= K) break;
                } else {
                    cnt = 1;
                    pre_disk = disk[i][j];
                }
            }
            if(cnt < K) return false;
        }

        return true;
    }

    static void checkDisk(int cnt, int idx) {
        if(cnt==K || inspectPerform()) {
            minRes = Math.min(minRes ,cnt);
            return;
        }

        for(int i=idx; i<D; i++) {
            Arrays.fill(disk[i], 1);
            checkDisk(cnt+1, i+1);

            Arrays.fill(disk[i], 0);
            checkDisk(cnt+1, i+1);

            disk[i] = Arrays.copyOf(copy_disk[i], W);
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
            copy_disk = new int[D][W];

            for(int i=0; i<D; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    disk[i][j] = Integer.parseInt(st.nextToken());
                    copy_disk[i][j] = disk[i][j];
                }
            }

            minRes = Integer.MAX_VALUE;
            if(K==1) minRes = 0;
            else checkDisk(0, 0);

            System.out.println("#"+tc+" "+minRes);
        }
    }
}
