import java.io.*;
import java.util.*;

public class Main {

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int bomb = 0;
        int target = 0;
        int tetraArr_len = 0;
        int[] tetraArr = new int[121];
        int[] dpArr = new int[N+1];
        Arrays.fill(dpArr, 300001);
        dpArr[0] = 0;
        tetraArr[0] = 0;

        for(int i=1; i<122; i++) {
            bomb += i;
            target += bomb;

            if(target > N) {
                tetraArr_len = i;
                break;
            }

            tetraArr[i] = target;
        }
        
        System.out.println(tetraArr_len);


        for(int i=1; i<tetraArr_len; i++) {
            dpArr[tetraArr[i]] = 1;

            for(int j=tetraArr[i]; j<N+1; j++) {
                dpArr[j] = Math.min(dpArr[j], dpArr[j - tetraArr[i]]+1);
            }
        }
        System.out.println(Arrays.toString(tetraArr));
        // System.out.println(Arrays.toString(dpArr));
        System.out.println(dpArr[N]);
    }
}