// 파도반 수열
import java.io.*;
import java.util.*;

public class S3376 {
    static int N;
    static long list[];
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            list = new long[101];
            list[0] = 1;
            list[1] = 1;
            list[2] = 1;
            list[3] = 2;
            list[4] = 2;
            list[5] = 3;
            int index = 6;
            while(index <= N) {
                long temp = list[index-1] + list[index-5];
                list[index] = temp;
                index += 1;
            }
            System.out.println("#" + tc + " " + list[N-1]);
        }
    }
}