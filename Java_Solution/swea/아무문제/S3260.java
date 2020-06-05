// 두 수의 덧셈
import java.io.*;
import java.util.*;

public class S3260 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            String[] A = in.next().split("");
            String[] B = in.next().split("");
            int AL = A.length;
            int BL = B.length;
            int L = 0;
            if(AL<BL) L = BL;
            else L = AL;

            int[] N = new int[L+1];
            
            for(int i=0; i<L; i++) {
                if(AL!=BL && i>=AL) N[L-i] += Integer.parseInt(B[BL-i-1]);
                else if(AL!=BL && i>=BL) N[L-i] += Integer.parseInt(A[AL-i-1]);
                else N[L-i] += Integer.parseInt(A[AL-i-1]) + Integer.parseInt(B[BL-i-1]);
                if(N[L-i]>=10) {
                    N[L-i] -=10;
                    N[L-i-1] += 1;
                }
            }
            Boolean flag = true;
            System.out.print("#"+tc+" ");
            for(int i:N) {
                if(i==0 && flag==true) continue;
                else {
                    System.out.print(i);
                    flag = false;
                }
            }
            System.out.println();
        }
    }
}