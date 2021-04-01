import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr, bsIndex;
    static ArrayList<Integer> LIS;
    
    public static int binarySearch(int min, int max, int val) {
        int mid;

        while(min < max) {
            mid = (min + max)/2;

            if(LIS.get(mid) < val) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return max;
    }

    public static void findLIS() {
        LIS.add(arr[0]);
        bsIndex[0] = 0;
        int size, index;
        
        for(int i=1; i<N; i++) {
            size = LIS.size();

            if(LIS.get(size-1) < arr[i]) {
                LIS.add(arr[i]);
                bsIndex[i] = size;
            } else {
                index = binarySearch(0, size, arr[i]);
                LIS.set(index, arr[i]);
                bsIndex[i] = index;
            }
        }
    }
    
    public static String appendIncludedLIS(int target, int index) {
        if(index == -1) {
            return "";
        }
        
        if(bsIndex[index] == target) {
            return appendIncludedLIS(target-1, index-1) + " " + arr[index];
        } else {
            return appendIncludedLIS(target, index-1);
        }
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        bsIndex = new int[N];
        Arrays.fill(bsIndex, -1);
        LIS = new ArrayList<>();

        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        findLIS();
        System.out.println(LIS.size());
        System.out.println(appendIncludedLIS(LIS.size()-1, N-1).trim());

    }
}
