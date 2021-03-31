import java.io.*;
import java.util.*;

public class B14002_bs {
    static int N;
    static int[] seq, bsIndex;
    static ArrayList<Integer> LIS;

    public static int lower_bound(int min, int max, int val) {
        int mid;

        while(min < max) {
            mid = (min + max)/2;

            if(LIS.get(mid) < val) {
                min = mid+1;
            } else {
                max = mid;
            }
        }

        return max;
    }

    public static void getLongestSequence() {
        LIS.add(seq[0]);

        for(int i=1; i<N; i++) {
            int size = LIS.size();

            if(LIS.get(size-1) < seq[i]) {
                LIS.add(seq[i]);
                bsIndex[i] = LIS.size()-1;
            } else {
                int index = lower_bound(0, size, seq[i]);
                LIS.set(index, seq[i]);
                bsIndex[i] = index;
            }
        }
    }

    public static String getLIS(int target, int index) {
        if(target == -1) {
            return "";
        }

        if(bsIndex[index] == target) {
            return getLIS(target-1, index-1) + " " + seq[index];
        } else {
            return getLIS(target, index-1);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        seq = new int[N];
        bsIndex = new int[N];
        LIS = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        getLongestSequence();
        System.out.println(LIS.size());
        System.out.println(getLIS(LIS.size()-1, N-1).trim());
    }
}
