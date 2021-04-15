// N개로 이루어진 수
// + - * /
// 숫자 사이 연산자
// 연산자 우선순위 고려x
// 나눗셈은 몫만
// 최대, 최소구하기
// 입력 셋째줄, 차례대로 + - x / 의 개수

import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, input_N[], operator[];
    static int minRes, maxRes;

    static void DFS(int r, int sum, int plus_cnt, int minus_cnt, int mul_cnt, int div_cnt) {
        if(N==r) {
            //System.out.println(sum);
            minRes = Math.min(minRes, sum);
            maxRes = Math.max(maxRes, sum);
            return;
        }

        if(plus_cnt < operator[0]) {
            DFS(r+1, sum+input_N[r], plus_cnt+1, minus_cnt, mul_cnt, div_cnt);
        }
        if(minus_cnt < operator[1]) {
            DFS(r+1, sum-input_N[r], plus_cnt, minus_cnt+1, mul_cnt, div_cnt);
        }
        if(mul_cnt < operator[2]) {
            DFS(r+1, sum*input_N[r], plus_cnt, minus_cnt, mul_cnt+1, div_cnt);
        }
        if(div_cnt < operator[3]) {
            DFS(r+1, sum/input_N[r], plus_cnt, minus_cnt, mul_cnt, div_cnt+1);
        }
        
    }
    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        input_N = new int[N];
        for(int i=0; i<N; i++) input_N[i] = sc.nextInt();
        operator = new int[4];
        for(int i=0; i<4; i++) operator[i] = sc.nextInt();
        minRes=100000000; maxRes=-100000000;

        DFS(1,input_N[0],0,0,0,0);
        System.out.println(maxRes);
        System.out.println(minRes);
    }
    sc.close();
}