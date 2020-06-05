import java.io.*;
import java.util.*;

public class S1945 {

    public int tool(int num, int n, int cnt) {
        while(num%n==0) {
            num /= n;
            cnt += 1;
        };
        return cnt;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int t = Integer.parseInt(in.readLine());
        S1945 s1945 = new S1945();
        int[] arr = {2,3,5,7,11};
        for(int i=0; i<t; i++) {
            int num = Integer.parseInt(in.readLine());
            int[] data = new int[5];
            for(int n=0; n<5; n++) {
                data[n] = s1945.tool(num, arr[n], 0);
            }

            System.out.print("#" + (i+1) + " ");
            for(int j=0; j<5; j++) {
                System.out.print((data[j]) + " ");
            };
            System.out.println();
        }
    }
}