import java.util.*;
import java.io.*;

/**
 * Main
 */
public class B16637 {
    static int N, maxRes = Integer.MIN_VALUE;
    static char input[];

    static void calcDFS(int idx, int val, int pre_val, boolean flag) {
        if(idx >= N) {
            maxRes = Math.max(maxRes, val);
            return;
        }

        calcDFS(idx+2, calculator(input[idx], val, input[idx+1]-'0'), val, false);

        if(!flag && idx > 1) {
            calcDFS(idx+2,
                    calculator(input[idx-2], 
                            pre_val, 
                            calculator(input[idx], input[idx-1]-'0', input[idx+1]-'0')),
                    0,
                    true);
        }
    }
    
    static int calculator(char oper, int num1, int num2) {
        if(oper == '+') return num1+num2;
        else if(oper == '-') return num1-num2;
        else return num1*num2;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();

        calcDFS(1, input[0]-'0', 0, false);

        System.out.println(maxRes);
    }
}