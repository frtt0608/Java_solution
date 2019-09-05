// 시험 감독
import java.io.*;
import java.util.*;

public class Main {
    static int N, B, C, res=0;
    static long cnt;
    static long[] student;
    static long[] arr;
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        student = new long[N];
        arr = new long[N]; 

        for(int i=0; i<N; i++) {
            student[i] = in.nextInt();
        }
        B = in.nextInt();
        C = in.nextInt();
        for(int i=0; i<N; i++) {
            arr[i] += B;
            cnt += 1;
        }
        for(int i=0; i<N; i++) {
            if(arr[i]<student[i]) {
                student[i] -= B;
                if(student[i]/C==0) {
                    cnt += 1;
                } else {
                    if(student[i]%C==0) {
                        cnt += student[i]/C;
                    } else {
                        cnt += student[i]/C + 1;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}