import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, maxRes;
    static char input[];
    // static int numArr[];
    // static char operArr[];
    // static boolean operVisited[];
    
    static public void checkOper(int idx, int val, int pre_val, boolean flag) {
        if(idx >= N) {
            maxRes = Math.max(maxRes, val);
            return;
        }
        
        checkOper(idx+2, 
                operator(val, input[idx+1]-'0', input[idx]),
                val, 
                false);
        
        if(!flag && idx > 1) {
            checkOper(idx+2, 
                operator(pre_val, 
                        operator(input[idx-1]-'0', input[idx+1]-'0', input[idx]), 
                        input[idx-2]),
                0,
                true);
        }
    }

    // static public int calcArr() {
    //     int calcRes = 0;
    //     int calcTemp = 0;
    //     int calcNum[] = numArr.clone();
    //     boolean numVisited[] = new boolean[N/2+1];

    //     for(int i=0; i<operVisited.length; i++) {
    //         if(operVisited[i]) {
    //             numVisited[i+1] = true;
    //             operator()
    //         }
    //     }

    //     for(int i=0; i<operVisited.length; i++) {
    //         if(operVisited[i]) continue;

    //     }

    //     return calcRes;
    // }

    static public int operator(int num1, int num2, char oper) {
        if(oper == '+') return num1+num2;
        else if(oper == '-') return num1-num2;
        else return num1*num2;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // numArr = new int[N/2+1];
        // operArr = new char[N/2];
        // operVisited = new boolean[N/2];
        maxRes = Integer.MIN_VALUE;
        input = br.readLine().toCharArray();
        checkOper(1, input[0]-'0', 0, false);

        // String input = br.readLine();
        // for(int i=0; i<N; i++) {
        //     char chr = input.charAt(i);
        //     if(chr >= 48 && chr <= 57) numArr[i/2] = Integer.valueOf(chr - '0');
        //     else operArr[i/2] = chr;
        // }
        System.out.println(maxRes);
    }
}