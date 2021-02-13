// 캡틴 이다솜

import java.util.*;
import java.io.*;

public class B1660 {

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] tetraArr = new int[122];
        int bomb = 0;
        int target = 0;
        int tetraLen = 0;
        
        for(int i=1; i<122; i++) {
            bomb += i;
            target += bomb;

            if(target > N) {
                tetraLen = i;
                break;
            }

            tetraArr[i] = target;
        }

        int[] dpArr = new int[N+1];
        Arrays.fill(dpArr, 300001);
        dpArr[0] = 0;

        for(int i=1; i<tetraLen; i++) {
            dpArr[tetraArr[i]] = 1;
        
            for(int j=tetraArr[i]; j<N+1; j++) {
                dpArr[j] = Math.min(dpArr[j], dpArr[j - tetraArr[i]] + 1);
            }
        }

        System.out.println(dpArr[N]);
    }
}