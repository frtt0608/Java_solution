import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strArr[] = br.readLine().split("");
        int numArr[] = new int[strArr.length];
        boolean flag = false; // 0이 있는지 확인
        long input_sum = 0;

        for(int i=0; i<strArr.length; i++) {
            numArr[i] = Integer.parseInt(strArr[i]);
            input_sum += numArr[i];
            if(numArr[i]==0) {
                flag = true;
            }
        }

        if(input_sum%3==0 && flag == true) {
            Arrays.sort(numArr);
            StringBuilder num = new StringBuilder(Arrays.toString(numArr).replaceAll("[^0-9]", ""));
            System.out.println(num.reverse().toString());
        } else {
            System.out.println(-1);
        }
    }
}