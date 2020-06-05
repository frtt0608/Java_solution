// 성공적인 공연기획
import java.io.*;
import java.util.*;

public class S4789 {
    static int cnt,res;
    static String data[] = new String[1001];
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            String[] data = sc.next().split("");
            cnt = Integer.parseInt(data[0]);
            res = 0;
            for(int i=1; i<data.length; i++) {
                if(!data[i].equals("0")) {
                    if(cnt<i) {
                        res += i-cnt;
                        cnt = i+Integer.parseInt(data[i]);
                    } else {
                        cnt += Integer.parseInt(data[i]);
                    }
                }
            }
            System.out.println("#" + tc + " " + res);
        }
    }
}