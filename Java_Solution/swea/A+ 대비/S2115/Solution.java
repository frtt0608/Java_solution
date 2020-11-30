import java.util.*;
import java.io.*;

/**
 * Solution
 */

 // DFS, bruteforce

public class Solution {
    static int T, N, M, C, maxIncome;
    static int honey1, honey2, money1, money2;
    static int[][] honey;

    public static void getMaxMoney(int si, int sj) {
        int j = 0;
        for(int i=si; i<N; i++) {
            if(i == si) j=sj;
            else j=0;
            for(; j<N; j++) {
                if(j+M-1 < N) {
                    honey2 = 0;
                    getMaxHoney2(Arrays.copyOfRange(honey[i], j, j+M), 0, 0, new int[M], 0);
                    // System.out.println(honey1 + ", " + honey2);
                    maxIncome = Math.max(maxIncome, honey1 + honey2);
                } else
                    break;
            }
        }
    }

    public static void getMaxHoney2(int[] honeyArr, int sum, int idx, int[] arr, int ai) {
        if(sum <= C) {
            money2 = 0;
            for(int a: arr) 
                money2 += Math.pow(a, 2);

            honey2 = Math.max(honey2, money2);
        }

        for(int i=idx; i<M; i++) {
            if(sum + honeyArr[i] <= C) {
                arr[ai] = honeyArr[i];
                getMaxHoney2(honeyArr, sum+honeyArr[i], i+1, arr, ai+1);
                arr[ai] = 0;
            }
        }
    }

    public static void getMaxHoney1(int[] honeyArr, int sum, int idx, int[] arr, int ai) {
        if(sum <= C) {
            money1 = 0;
            for(int a: arr) 
                money1 += Math.pow(a, 2);

            honey1 = Math.max(honey1, money1);
        }

        for(int i=idx; i<M; i++) {
            if(sum + honeyArr[i] <= C) {
                arr[ai] = honeyArr[i];
                getMaxHoney1(honeyArr, sum+honeyArr[i], i+1, arr, ai+1);
                arr[ai] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            maxIncome = 0;

            honey = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    honey[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(j+M-1 < N) {
                        honey1 = 0;
                        getMaxHoney1(Arrays.copyOfRange(honey[i], j, j+M), 0, 0, new int[M], 0);
                        getMaxMoney(i, j+M);
                    }
                }
            }

            System.out.println("#" + tc + " " + maxIncome);
        }
    }
}