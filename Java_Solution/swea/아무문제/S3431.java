import java.io.*;
import java.util.*;

public class S3431 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int t = Integer.parseInt(in.readLine());
        for(int i=0; i<t; i++) {
            int res = 0;
            String[] data = in.readLine().split(" ");
            int start = Integer.parseInt(data[0]);
            int end = Integer.parseInt(data[1]);
            int x = Integer.parseInt(data[2]);
            if(x < start) {
                res = start - x;
            }
            else if(x > end) {
                res = -1;
            }
            System.out.println("#" + (i+1) + " " + (res));
        }
    }
}