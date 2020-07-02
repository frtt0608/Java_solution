// NMK review

import java.io.*;

public class Main {
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        if(N < M+K-1 || N > M*K) {
            System.out.println(-1);

        } else {
            StringBuilder sb = new StringBuilder();
            int[] seqArr = new int[N];

            for(int i=0; i<N; i++) {
                seqArr[i] = i+1;
            }

            int[] idxArr = new int[M];
            N -= K;
            int Msize = 0;

            for(int i=0; i<M; i++) {
                if(i==0) {
                    idxArr[0] = K;
                    Msize = M-1 == 0 ? 1:M-1;
                }
                else {
                    if(N%Msize == 0) {
                        idxArr[i] = (int)N/Msize;
                    } else {
                        idxArr[i] = (int)N/Msize+1;
                        N--;
                    }
                }
            }

            int idx = -1;
            for(int i=0; i<idxArr.length; i++) {
                idx += idxArr[i];

                for(int j=idx; j>idx - idxArr[i]; j--) {
                    sb.append(seqArr[j] + " ");
                }
            }

            System.out.println(sb.toString());
        }
    }
}