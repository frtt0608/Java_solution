import java.io.*;
import java.util.*;

public class B1676 {
    static int N, count;
   
    public static void getZeroCount(int num) {
        int mock = num/5;

        if(mock > 0) {
            count += mock;
            getZeroCount(mock);
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        getZeroCount(N);
        System.out.println(count);
    }
}
