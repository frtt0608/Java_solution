import java.util.*;
import java.io.*;

public class B14501 {
    static int N, maxPrice;
    static int[] time, price;

    public static void makeConsultantScheduling(int day, int curPrice) {
        if(day > N) {
            maxPrice = Math.max(maxPrice, curPrice);
            return;
        }

        makeConsultantScheduling(day+1, curPrice);

        if(day+time[day]-1 <= N) {
            makeConsultantScheduling(day+time[day], curPrice + price[day]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        time = new int[N+1];
        price = new int[N+1];
        
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }

        makeConsultantScheduling(1, 0);

        System.out.println(maxPrice);
    }
}