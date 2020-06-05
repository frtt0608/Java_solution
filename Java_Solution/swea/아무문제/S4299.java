
// 태혁이의 사랑은 타이밍
import java.io.*;
import java.util.*;

// D일 H시 M분

public class S4299 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int d = 11;
        int h = 11;
        int m = 11;

        int T = in.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int D = in.nextInt();
            int H = in.nextInt();
            int M = in.nextInt();
            int res = 0;

            if (M - m < 0) {
                res += M - m + 60;
                H -= 1;
            } else {
                res += M - m;
            }

            if (H - h < 0) {
                res += 60 * (H - h + 24);
                D -= 1;
            } else {
                res += 60 * (H - h);
            }
            res += 24 * 60 * (D - d);

            if(res < 0)
                res = -1;
                
            System.out.println("#" + tc + " " + res);
        }
    }
}