// 희성이의 원근법
// 0.1km미만 0
// 0.1 ~ 1 : 1
// 1 ~ 10 : 2

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
    static int d;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            d = sc.nextInt();

            if(d<100) {
                System.out.println("#"+tc+" "+0);
            } else if(d>=100 && d<1000) {
                System.out.println("#"+tc+" "+1);
            } else {
                double d_mok = Math.floor(Math.log10(d/1000));
                if(d_mok > 3.0) {
                    System.out.println("#"+tc+" "+5);
                } else {
                    int result = (int)d_mok + 2;
                    System.out.println("#"+tc+" "+result);
                }
            }
        }
    }
}