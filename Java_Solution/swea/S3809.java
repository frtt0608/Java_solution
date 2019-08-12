// 화섭이의 정수 나열
import java.io.*;
import java.util.*;

public class S3809 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            int N =in.nextInt();
            String temp = "";
            for(int i=0; i<N; i++)
                temp += in.next();

            for(int i=0; i<1000; i++) {
                String str = Integer.toString(i);
                if(!temp.contains(str)) {
                    System.out.println("#" + tc + " " + str);
                    break;
                }
            }
        }
    }
}