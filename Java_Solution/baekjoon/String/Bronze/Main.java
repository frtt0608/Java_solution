import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        int[] num = new int[7];
        for(int i=0; i<7; i++) {
            if(i==3) continue;
            num[i] = input[i] - '0';
        }

        int num1 = num[0] + num[1]*10 + num[2]*100;
        int num2 = num[4] + num[5]*10 + num[6]*100;

        if(num1 > num2) {
            System.out.println(num1);
        } else {
            System.out.println(num2);
        }
    }
}