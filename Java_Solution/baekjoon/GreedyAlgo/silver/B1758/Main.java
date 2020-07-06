// 알바생 강호

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long totalTip = 0;
        int[] inputTip = new int[N];

        for(int i=0; i<N; i++) {
            inputTip[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(inputTip);

        for(int i=1; i<=N; i++) {
            int tip = inputTip[N-i]-(i-1);
            if(tip > 0) {
                totalTip += tip;
            }
        }

        System.out.println(totalTip);
    }
}