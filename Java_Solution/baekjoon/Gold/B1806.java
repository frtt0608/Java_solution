import java.io.*;
import java.util.*;

public class B1806 {
    static final int MIN = 1000000000;
    static int N, S, minLength;
    static int[] nums;

    public static void sumPartitionNumbers() {
        int s = 0;
        int e = 0;
        int sumResult = 0;

        while(true) {  

            if(sumResult >= S) {
                minLength = Math.min(minLength, (e-s));
                sumResult -= nums[s];
                s += 1;

            } else {
                if(e == N) break;
                
                sumResult += nums[e];
                e += 1;
            }
        }

        if(minLength == MIN) {
            minLength = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        minLength = MIN;
        sumPartitionNumbers();
        System.out.println(minLength);
    }
}
