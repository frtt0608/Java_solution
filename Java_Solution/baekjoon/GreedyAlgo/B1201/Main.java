// NMK

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] seqArr;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NMK = br.readLine().split(" ");
        N = Integer.parseInt(NMK[0]);
        M = Integer.parseInt(NMK[1]); // LIS 길이
        K = Integer.parseInt(NMK[2]); // LDS 길이

        if(N < M+K-1 || N > M*K) {
            System.out.println(-1);

        } else {
            seqArr = new int[N];
            for(int i=0; i<N; i++) {
                seqArr[i] = i+1;
            }

            int size = 0;
            int Msize = 0;
            int[] indexArr = new int[M];

            for(int i=0; i<M; i++) {
                if(i==0) {
                    indexArr[i] = K;
                    size = N-K;
                    
                    if(M == 1) Msize = 1;
                    else {
                        Msize = M - 1;
                    }

                } else {
                    if(size%Msize != 0) {
                        indexArr[i] = (int)size/Msize+1;
                        size--;
                    } else {
                        indexArr[i] = (int)size/Msize;
                    }
                }
            }
            
            int idx = -1;
            int[] resArr = new int[N];
            int seqIdx = 0;
            for(int i=0; i<M; i++) {
                idx += indexArr[i];
                for(int j=idx; j>idx - indexArr[i]; j--) {
                    resArr[j] = seqArr[seqIdx++];
                }
            }

            for(int i=0; i<N; i++) {
                System.out.print(resArr[i] + " ");
            }
        }
    }
}