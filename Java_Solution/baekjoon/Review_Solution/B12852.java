import java.util.*;
import java.io.*;

public class B12852 {
    static final int MAX = 10000001;
    static int N;
    static int[] minCount, preNum;
    static StringBuilder sb;

    public static void bottom_up() {
        int cnt = 0, beforeNum = 0;

        for(int num=2; num<N+1; num++) {
            cnt = minCount[num-1];
            beforeNum = num-1;

            if(num%3 == 0 && cnt > minCount[num/3]) {
                cnt = minCount[num/3];
                beforeNum = num/3;
            }

            if(num%2 == 0 && cnt > minCount[num/2]) {
                cnt = minCount[num/2];
                beforeNum = num/2;
            }

            minCount[num] = cnt+1;
            preNum[num] = beforeNum;
        }
    }

    public static void appendIncludedNumber(int num) {
        sb.append(num+" ");
        if(num == 1) 
            return;

        appendIncludedNumber(preNum[num]);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        minCount = new int[N+1];
        preNum = new int[N+1];

        bottom_up();
        sb.append(minCount[N]+"\n");
        appendIncludedNumber(N);
        System.out.println(sb.toString());
    }
}   
