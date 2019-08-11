// 시간개념

import java.io.*;
import java.util.*;

public class S7732 {

    static String current_time;
    static String promise_time;
    static int[] current;
    static int[] promise;
    static int[] res;
    static String[] res_time;

    // String형 배열을 int형 배열로 변환
    static void StringtoInt(int[] res, String[] temp) {
        for(int i=0; i<3; i++) {
            res[i] = Integer.parseInt(temp[i]);
        }
    }

    // 시간 계산하기
    static void time(int i) {
        if(promise[i] - current[i] < 0) {
            promise[i-1] -= 1;
            promise[i] += 60;
        }
        res[i] = promise[i] - current[i];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            current_time = in.next();
            promise_time = in.next();
            current = new int[3];
            promise = new int[3];
            res_time = new String[3];
            res = new int[3];
            
            String[] temp1 = current_time.split(":");
            String[] temp2 = promise_time.split(":");

            StringtoInt(current, temp1);
            StringtoInt(promise, temp2);
            
            time(2);
            time(1);
            
            res[0] = promise[0] - current[0];

            if(res[0] < 0) {
                res[0] += 24;
            }

            for(int j=0; j<3; j++) {
                if(res[j]/10 >= 1) {
                    res_time[j] = Integer.toString(res[j]);
                } else if (res[j] == 0) {
                    res_time[j] = "00";
                } else {
                    res_time[j] = "0" + Integer.toString(res[j]);
                }
            }

            System.out.println("#"+ tc + " " + res_time[0] + ":" + res_time[1] + ":" + res_time[2]);
        }
    }
}