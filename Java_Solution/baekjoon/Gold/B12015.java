import java.io.*;
import java.util.*;

public class B12015 {
    static int N;
    static int[] seq;
    static ArrayList<Integer> LIS;

    public static int searchPosition(int min, int max, int value) {
        int mid = 0;

        while(min < max) {
            mid = (min+max)/2;

            if(LIS.get(mid) < value) {
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
            if(LIS.get(LIS.size()-1) < seq[i]) {
                LIS.add(seq[i]);
            } else {
                int index = searchPosition(0, LIS.size(), seq[i]);
                LIS.set(index, seq[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        seq = new int[N];
        LIS = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        getLongestSequence();
        System.out.println(LIS.size());
    }
}
