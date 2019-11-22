// 보물상자 비밀번호

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class S5658 {
    static int N, K;
    static String[] data;

    static void parse_data(String[] data) {
        int temp = N/4;
        String parse_data[][] = new String[3][1];
        for(String d:data) {

        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            K = sc.nextInt();
            data = new String[N];
            data = sc.next().split("");

        }
        sc.close();
    }
}