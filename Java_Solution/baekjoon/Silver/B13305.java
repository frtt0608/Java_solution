import java.io.*;
import java.util.*;

public class B13305 {

    static int N, currentOil;
    static int[] distance, price;
    static long totalPrice;

    public static void moveToRightCity() {
        long minPrice = 0;
        totalPrice = 0;

        // i = 현재 도시위치 및 가격, 가야할 거리
        for(int i=0; i<N; i++) {
            if(i == N-1) break;

            if(i == 0) {
                minPrice = price[i];
                totalPrice = minPrice * distance[i];
            } else {
                if(minPrice > price[i]) {
                    minPrice = price[i];
                }
                totalPrice += minPrice * distance[i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        distance = new int[N-1];
        price = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }  
        
        moveToRightCity();
        System.out.println(totalPrice);
    }
}
