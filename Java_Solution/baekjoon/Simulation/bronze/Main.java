import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            if(a == 0 && b == 0 && c == 0) break;
            int[] num = {a, b, c};
            Arrays.sort(num);

            if(num[0]*num[0] + num[1]*num[1] == num[2]*num[2]) System.out.println("right");
            else System.out.println("wrong");
        }
    }
}