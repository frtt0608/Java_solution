// 정삼각형 분할 놀이
import java.io.*;
import java.util.*;

public class S3233 {
    static int A, B;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            A = sc.nextInt();
            B = sc.nextInt();
            long temp = A/B;
            long res = temp*temp;
            System.out.println("#" + tc + " " + res);
        }
    }
}