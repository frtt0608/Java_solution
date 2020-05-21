import java.io.*;
import java.util.*;

// LIS를 이용.. 알고리즘은 이해했으나 
// else문에서 이진탐색으로 교체할 idx를 찾는 부분은 이해못함
/**
 * Main
 */
public class Main {
    static int N, target[], maxCnt;

    static int LISconnectPort() {
        int LIS_length = 1;
        int connect[] = new int[N];

        connect[0] = target[0];

        for(int port=1; port<N; port++) {
            if(target[port] < connect[0]) {
                connect[0] = target[port];
            } else if(target[port] > connect[LIS_length-1]) {
                connect[LIS_length] = target[port];
                LIS_length += 1;
            } else {
                // int idx = Arrays.binarySearch(connect, 0, LIS_length, target[port]);
                int idx = binarySearch(LIS_length-1, target[port], connect);
                // idx = idx<0? -idx-1:idx;
                
                connect[idx] = target[port];
            }
            // System.out.println(Arrays.toString(connect));
        }

        return LIS_length;
    }

    static int binarySearch(int max, int val, int connect[]) {
        int min = 0;
        int mid = 0;

        while(max>=min) {
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
        target = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        maxCnt = LISconnectPort();

        System.out.println(maxCnt);
    }
}