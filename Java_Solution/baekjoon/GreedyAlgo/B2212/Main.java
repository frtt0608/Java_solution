// 센서

import java.util.*;
import java.io.*;

public class Main {
    static int N, K, minRes;
    static int[] sensors, diff_sensors;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        String input[] = br.readLine().split(" ");
        sensors = new int[N];
        diff_sensors = new int[N-1];

        for(int i=0; i<N; i++) {
            sensors[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(sensors);

        for(int i=0; i<N-1; i++) {
            diff_sensors[i] = sensors[i+1] - sensors[i];
        }
        Arrays.sort(diff_sensors);

        if(K < N) {
            for(int i=0; i<N-K; i++) {
                minRes += diff_sensors[i];
            }
        }

        System.out.println(minRes);
    }
}