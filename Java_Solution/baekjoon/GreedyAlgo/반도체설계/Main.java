import java.io.*;
import java.util.*;

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
                int idx = Arrays.binarySearch(connect, 0, LIS_length, target[port]);
                idx = idx<0? -idx-1:idx;

                connect[idx] = target[port];
            }
        }

        return LIS_length;
    }

    // static void binarySearch(int max, int val) {
    //     int min = 0;
    //     int mid;

    //     while(max>=min) {
    //         mid = (max+min)/2;
            
            
    //     }
    // }

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