import java.io.*;
import java.util.*;

public class B1450 {
    static int N, C, resCount;
    static int idxL, idxR;
    static int[] weight;
    static ArrayList<Integer> weightLeft, weightRight;

    public static void putTheItemInBag(boolean isLeft, int s, int e, 
                                int totalWeight) {
        if(totalWeight > C) return;

        if(s == e) {
            if(isLeft) {
                weightLeft.add(totalWeight);
            } else {
                weightRight.add(totalWeight);
            }
            return;
        }

        putTheItemInBag(isLeft, s+1, e, totalWeight + weight[s]);
        putTheItemInBag(isLeft, s+1, e, totalWeight);
    }

    public static int getRightValueLowerBound(int min, int max, int left) {
        int mid = 0;

        while(min < max) {
            mid = (min+max)/2;

            if(weightRight.get(mid) + left <= C) {
                min = mid+1;
            } else {
                max = mid;
            }
        }

        return max;
    }

    public static void getTotalCaseWithKnapsack() {
        Collections.sort(weightRight);

        for(int left: weightLeft) {
            if(C < left) continue;

            resCount += getRightValueLowerBound(0, weightRight.size(), left);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        weight = new int[N];
        weightLeft = new ArrayList<>();
        weightRight = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        putTheItemInBag(true, 0, N/2, 0);
        putTheItemInBag(false, N/2, N, 0);

        getTotalCaseWithKnapsack();
        System.out.println(resCount);
    }
}
