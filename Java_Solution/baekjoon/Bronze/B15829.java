import java.util.*;
import java.io.*;

public class B15829 {
    static final long M = 1234567891;
    static long r = 1;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        long res = 0;

        for(char chr: input) {
            res += ( (chr - 'a' + 1)*r )%M;
            r = (r * 31)%M;
        }

        System.out.println(res%M);
    }
}