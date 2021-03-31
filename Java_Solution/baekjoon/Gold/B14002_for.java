import java.io.*;
import java.util.*;

public class B14002_for {
    static int N, LIS, maxIndex;
    static int[] seq, maxLen, preIndex;
    static StringBuilder sb;

    public static void getLongestSequence() {
        
        for(int i=0; i<N; i++) {
            maxLen[i] = 1;
            preIndex[i] = -1;

            for(int j=0; j<i; j++) {
                if(seq[j] < seq[i] && maxLen[i] <= maxLen[j]) {
                    maxLen[i] = maxLen[j] + 1;
                    preIndex[i] = j;

                    if(maxLen[i] > LIS) {
                        LIS = maxLen[i];
                        maxIndex = i;
                    }
                }
            }
        }
    }

    public static String printLIS(int index) {
        if(index == -1)
            return "";

        return printLIS(preIndex[index]) + " " + seq[index];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        seq = new int[N];
        maxLen = new int[N];
        preIndex = new int[N];
        LIS = 1;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        getLongestSequence();
        System.out.println(LIS);
        System.out.println(printLIS(maxIndex).trim());

        System.out.println(Arrays.toString(preIndex));
    }
}
