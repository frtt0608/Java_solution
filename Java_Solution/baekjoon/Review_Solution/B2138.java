// 전구와 스위치 review

import java.util.*;
import java.io.*;

public class B2138 {
    static int N, minCnt;
    static char[] input, target, copy;

    static boolean switchTolamp() {
        copy = input.clone();

        for(int i=1; i<N; i++) {
            if(copy[i-1] != target[i-1]) {
                change(i);
                minCnt += 1;
            }
        }

        for(int i=0; i<N; i++) {
            if(copy[i] != target[i]) {
                minCnt = -1;
                return false;
            }
        }

        return true;
    }

    static void change(int idx) {

        for(int i=-1; i<2; i++) {
            if(idx+i >= 0 && idx+i <= N-1) {
                if(copy[idx+i] == '0') copy[idx+i] = '1';
                else copy[idx+i] = '0';
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        if(!switchTolamp()) {
            for(int i=0; i<2; i++) {
                if(input[i] == '0') input[i] = '1';
                else input[i] = '0';
            }

            minCnt = 1;

            switchTolamp();
        }

        System.out.println(minCnt);
    }
}