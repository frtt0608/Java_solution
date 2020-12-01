import java.util.*;
import java.io.*;

public class Solution {
    static int day, month, three, year, minPrice;
    static int[] plan = new int[12];

    public static void calculatePrice(int price, int idx) {
        if(idx >= 12) {
            minPrice = Math.min(minPrice, price);
            return;
        }

        if(plan[idx] == 0)
            calculatePrice(price, idx+1);
        else {
            if(plan[idx]*day > month) {
                calculatePrice(price+month, idx+1);
            } else {
                calculatePrice(price+plan[idx]*day, idx+1);
            }
            calculatePrice(price+three, idx+3);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());

            day = Integer.parseInt(st.nextToken());
            month = Integer.parseInt(st.nextToken());
            three = Integer.parseInt(st.nextToken());
            year = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            minPrice = year;

            calculatePrice(0, 0);

            System.out.println("#"+tc+" "+minPrice);
        }
    }
}