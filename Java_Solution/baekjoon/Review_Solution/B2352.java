import java.io.*;
import java.util.*;

/**
 * Main
 */
public class B2352 {
    static int N, port[];

    static int LIS() {
        int LIS_len = 1;
        int connect[] = new int[N];
        int idx = 0;
        connect[0] = port[0];

        for(int i=0; i<N; i++) {
            if(connect[0] > port[0]) {
                connect[0] = port[0];
            } else if(connect[LIS_len-1] < port[i]) {
                connect[LIS_len] = port[i];
                LIS_len += 1;
            } else {
                idx = BinarySearch(LIS_len, connect, port[i]);
                connect[idx] = port[i];
            }
        }

        return LIS_len;
    }

    static int BinarySearch(int max, int connect[], int val) {
        int min = 0;
        int mid = 0;

        while(max >= min) {
            mid = (max+min)/2;

            if(connect[mid] < val) {
                min = mid+1;
            } else if(connect[mid] > val) {
                max = mid-1;
            } else {
                return mid;
            }
        }

        return min;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        port = new int[N];
        String input[] = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            port[i] = Integer.parseInt(input[i]);
        }

        System.out.println(LIS());
    }
}