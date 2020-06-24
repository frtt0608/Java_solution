// 파일 합치기2
// java는 통과 8088ms

import java.util.*;
import java.io.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] priceArr = new int[K];
            int[] sumArr = new int[K];
            int[][] cost = new int[K][K];

            for(int i=0; i<K; i++) {
                cost[i][i] = i;
                priceArr[i] = Integer.parseInt(st.nextToken());
            }
            
            // System.out.println(Arrays.toString(priceArr));
            
            sumArr[0] = priceArr[0];
            for(int i=1; i<K; i++) {
                sumArr[i] = sumArr[i-1] + priceArr[i];
            }

            int[][] dpMap = new int[K][K];
            
            int temp = 0;

            for(int i=1; i<K; i++) {
                
                for(int j=0; i+j<K; j++) {
                    dpMap[j][i+j] = Integer.MAX_VALUE;

                    for(int k=cost[j][i+j-1]; k<=cost[j+1][i+j]; k++) {
                        if(k+1 >= K) break;

                        if(j==0) {
                            temp = dpMap[j][k] + dpMap[k+1][i+j] + sumArr[i+j];
                        } else {
                            temp = dpMap[j][k] + dpMap[k+1][i+j] + sumArr[i+j] - sumArr[j-1];
                        }

                        if(temp < dpMap[j][i+j]) {
                            dpMap[j][i+j] = temp;
                            cost[j][i+j] = k;
                        }
                    }
                }
            }
            // for(int i=0; i<K; i++) {
            //     System.out.println(Arrays.toString(dpMap[i]));
            // }
            System.out.println(dpMap[0][K-1]);
        }
    }
}