import java.io.*;
import java.util.*;

public class B1644 {
    static int N, idx, resCount;
    static boolean[] prime;
    static int[] nums;

    public static void setPrimeNum() {
        prime[0] = true;
        prime[1] = true;

        for(int i=2; i<N+1; i++) {
            if(!prime[i]) {
                nums[idx] = i;
                idx += 1;
                for(int j=i*2; j<N+1; j+=i) {
                    prime[j] = true;
                }
            }
        }
    }

    public static void sumPartitionNumbers() {
        int s = 0, e = 0;
        int sumResult = 0;

        while(true) {

            if(sumResult >= N) {
                if(sumResult == N) resCount += 1;

                sumResult -= nums[s];
                s += 1;
            } else {
                if(e == idx) 
                    return;
                sumResult += nums[e];
                e += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        prime = new boolean[N+1];
        nums = new int[N+1];

        setPrimeNum();
        sumPartitionNumbers();

        System.out.println(resCount);
    }
}
