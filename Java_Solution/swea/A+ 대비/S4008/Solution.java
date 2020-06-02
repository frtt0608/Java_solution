import java.util.*;
import java.io.*;

/**
 * Solution
 */
public class Solution {
    static int N, minRes, maxRes;
    static int[] numArr;

    static void calculateNum(int idx, int res, int[] oper) {
        if(idx==N) {
            minRes = Math.min(minRes, res);
            maxRes = Math.max(maxRes, res);
            return;
        }

        for(int i=0; i<4; i++) {
            if(oper[i] == 0) continue;

            oper[i] -= 1;
            if(i==0) {
                calculateNum(idx+1, res+numArr[idx], oper);
            }
            else if(i==1) {
                calculateNum(idx+1, res-numArr[idx], oper);
            }
            else if(i==2) {
                calculateNum(idx+1, res*numArr[idx], oper);
            }
            else {
                calculateNum(idx+1, res/numArr[idx], oper);
            }
            oper[i] += 1;
        }
    }
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            int[] oper = new int[4]; // + - * /
            numArr = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) {
                oper[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                numArr[i] = Integer.parseInt(st.nextToken());
            }

            minRes = Integer.MAX_VALUE;
            maxRes = Integer.MIN_VALUE;

            calculateNum(1, numArr[0], oper);
            int res = maxRes-minRes;
            System.out.println("#" + tc + " " + res);
        }
    }
}