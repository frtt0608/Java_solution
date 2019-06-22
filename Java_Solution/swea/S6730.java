import java.io.*;
import java.util.*;

public class S6730 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int T = Integer.parseInt(in.readLine());
        for(int tc=0; tc<T; tc++) {
            int up=0;
            int down=0;
            int n = Integer.parseInt(in.readLine());
            String[] data = in.readLine().split(" ");
            for(int i=0; i<n-1; i++) {
                int temp = Integer.parseInt(data[i+1]) - Integer.parseInt(data[i]);
                if(temp > 0) {
                    if(up < temp) {
                        up = temp;
                    }
                }
                else if(temp < 0) {
                    temp *= -1;
                    if(down < temp) {
                        down = temp;
                    }
                }
            }
            System.out.println("#" + (tc+1) + " " + (up) + " " + (down));
        }
    }
}