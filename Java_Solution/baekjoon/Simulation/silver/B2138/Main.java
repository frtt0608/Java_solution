// 전구와 스위치

import java.io.*;
import java.util.*;

public class Main {
    static int N, minCnt;
    static char[] input, target, copy;
    
    static boolean switchLamp() {
        copy = input.clone();
        
        for(int i=1; i<N; i++) {
            if(copy[i-1] != target[i-1]) {
                change(i);
                minCnt += 1;
                // System.out.println(Arrays.toString(copy) + " " + minCnt);
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
        idx -= 1;

        for(int i=0; i<3; i++) {
            if(idx>=0 && idx<=N-1) {
                if(copy[idx] == '0') {
                    copy[idx] = '1';
                } else {
                    copy[idx] = '0';
                }
            }
            idx++;
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        input = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        if(!switchLamp()) {
            for(int i=0; i<2; i++) {
                if(input[i] == '1') input[i] = '0';
                else input[i] = '1';
            }
            // System.out.println(Arrays.toString(input));
            minCnt = 1;
            switchLamp();
        }

        System.out.println(minCnt);
    }
}