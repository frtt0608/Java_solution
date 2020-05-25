import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int res = 0;
        int minPrice = 2001;
        int price = 0;

        for(int i=0; i<5; i++ ) {
            price = Integer.parseInt(br.readLine());
            minPrice = Math.min(minPrice, price);

            if(i==2) {
                res += minPrice;
                minPrice = 2001;
            }
        }
        res += minPrice - 50;
        System.out.println(res);
    }
}