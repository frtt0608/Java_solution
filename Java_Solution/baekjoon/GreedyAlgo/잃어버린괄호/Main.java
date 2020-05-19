import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static String inputArr[];
    static int minRes;

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strArr[] = br.readLine().split("-"); // [55, 50+40];
        int temp = 0;
        int minusArr[] = new int[strArr.length];

        for(int i=0; i<strArr.length; i++) {
            if(strArr[i].contains("+")) {
                temp = 0;
                String plusArr[] = strArr[i].split("\\+");
                for(int j=0; j<plusArr.length; j++) {
                    temp += Integer.parseInt(plusArr[j]);
                }
                minusArr[i] = temp;
            } else {
                minusArr[i] = Integer.parseInt(strArr[i]);
            }
        }

        minRes = minusArr[0];
        for(int i=1; i<minusArr.length; i++) {
            minRes -= minusArr[i];
        }

        System.out.println(minRes);
    }
}