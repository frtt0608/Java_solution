import java.io.*;
import java.util.*;

// Two pointer
// or index array
public class B3273 {

    static int N, target, pairCount;
    static int[] nums;

    public static void findNumberPair() {
        int s = 0;
        int e = N-1;

        while(s < e) {
            if(nums[s] + nums[e] == target) {
                pairCount += 1;
                s += 1;
            } else if(nums[s] + nums[e] < target){
                s += 1;
            } else {
                e -= 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        target = Integer.parseInt(br.readLine());

        findNumberPair();
        System.out.println(pairCount);
    }
}
