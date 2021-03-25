import java.util.*;
import java.io.*;

public class B1450 {
    static final int MAX = 100000000;
    static int N, C, totalCount;
    static int[] weight;
    static ArrayList<Integer> weightLeft, weightRight;

    public static void getTotalCase(boolean isLeft, int s, int e, int sum) {
        if(sum > C) return;

        if(s == e) {
            if(isLeft) { weightLeft.add(sum); }
            else { weightRight.add(sum); }
            
            return;
        }

        getTotalCase(isLeft, s+1, e, sum + weight[s]);
        getTotalCase(isLeft, s+1, e, sum);
    }

    public static int searchPossibleWeight(int min, int max, int left) {
        int mid = 0;

        while(min < max) {
            mid = (min + max)/2;

            if(weightRight.get(mid) + left <= C) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return max;
    }

    public static void putInTheBag() {
        Collections.sort(weightRight);

        for(int left: weightLeft) {
            totalCount += searchPossibleWeight(0, weightRight.size(), left);
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

        getTotalCase(true, 0, N/2, 0);
        getTotalCase(false, N/2, N, 0);

        putInTheBag();
        System.out.println(totalCount);
    }
}   
